package monitors



import org.junit.*
import grails.test.mixin.*

@TestFor(MetricController)
@Mock(Metric)
class MetricControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/metric/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.metricInstanceList.size() == 0
        assert model.metricInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.metricInstance != null
    }

    void testSave() {
        controller.save()

        assert model.metricInstance != null
        assert view == '/metric/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/metric/show/1'
        assert controller.flash.message != null
        assert Metric.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/metric/list'

        populateValidParams(params)
        def metric = new Metric(params)

        assert metric.save() != null

        params.id = metric.id

        def model = controller.show()

        assert model.metricInstance == metric
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/metric/list'

        populateValidParams(params)
        def metric = new Metric(params)

        assert metric.save() != null

        params.id = metric.id

        def model = controller.edit()

        assert model.metricInstance == metric
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/metric/list'

        response.reset()

        populateValidParams(params)
        def metric = new Metric(params)

        assert metric.save() != null

        // test invalid parameters in update
        params.id = metric.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/metric/edit"
        assert model.metricInstance != null

        metric.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/metric/show/$metric.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        metric.clearErrors()

        populateValidParams(params)
        params.id = metric.id
        params.version = -1
        controller.update()

        assert view == "/metric/edit"
        assert model.metricInstance != null
        assert model.metricInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/metric/list'

        response.reset()

        populateValidParams(params)
        def metric = new Metric(params)

        assert metric.save() != null
        assert Metric.count() == 1

        params.id = metric.id

        controller.delete()

        assert Metric.count() == 0
        assert Metric.get(metric.id) == null
        assert response.redirectedUrl == '/metric/list'
    }
}
