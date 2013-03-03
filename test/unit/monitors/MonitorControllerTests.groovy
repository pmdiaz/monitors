package monitors



import org.junit.*
import grails.test.mixin.*

@TestFor(MonitorController)
@Mock(Monitor)
class MonitorControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/monitor/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.monitorInstanceList.size() == 0
        assert model.monitorInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.monitorInstance != null
    }

    void testSave() {
        controller.save()

        assert model.monitorInstance != null
        assert view == '/monitor/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/monitor/show/1'
        assert controller.flash.message != null
        assert Monitor.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/monitor/list'

        populateValidParams(params)
        def monitor = new Monitor(params)

        assert monitor.save() != null

        params.id = monitor.id

        def model = controller.show()

        assert model.monitorInstance == monitor
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/monitor/list'

        populateValidParams(params)
        def monitor = new Monitor(params)

        assert monitor.save() != null

        params.id = monitor.id

        def model = controller.edit()

        assert model.monitorInstance == monitor
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/monitor/list'

        response.reset()

        populateValidParams(params)
        def monitor = new Monitor(params)

        assert monitor.save() != null

        // test invalid parameters in update
        params.id = monitor.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/monitor/edit"
        assert model.monitorInstance != null

        monitor.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/monitor/show/$monitor.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        monitor.clearErrors()

        populateValidParams(params)
        params.id = monitor.id
        params.version = -1
        controller.update()

        assert view == "/monitor/edit"
        assert model.monitorInstance != null
        assert model.monitorInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/monitor/list'

        response.reset()

        populateValidParams(params)
        def monitor = new Monitor(params)

        assert monitor.save() != null
        assert Monitor.count() == 1

        params.id = monitor.id

        controller.delete()

        assert Monitor.count() == 0
        assert Monitor.get(monitor.id) == null
        assert response.redirectedUrl == '/monitor/list'
    }
}
