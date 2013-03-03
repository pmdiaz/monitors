package monitors

import org.springframework.dao.DataIntegrityViolationException

class MonitorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [monitorInstanceList: Monitor.list(params), monitorInstanceTotal: Monitor.count()]
    }

    def create() {
        [monitorInstance: new Monitor(params)]
    }

    def save() {
        def monitorInstance = new Monitor(params)
        if (!monitorInstance.save(flush: true)) {
            render(view: "create", model: [monitorInstance: monitorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'monitor.label', default: 'Monitor'), monitorInstance.id])
        redirect(action: "show", id: monitorInstance.id)
    }

    def show(Long id) {
        def monitorInstance = Monitor.get(id)
        if (!monitorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monitor.label', default: 'Monitor'), id])
            redirect(action: "list")
            return
        }

        [monitorInstance: monitorInstance]
    }

    def edit(Long id) {
        def monitorInstance = Monitor.get(id)
        if (!monitorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monitor.label', default: 'Monitor'), id])
            redirect(action: "list")
            return
        }

        [monitorInstance: monitorInstance]
    }

    def update(Long id, Long version) {
        def monitorInstance = Monitor.get(id)
        if (!monitorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monitor.label', default: 'Monitor'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (monitorInstance.version > version) {
                monitorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'monitor.label', default: 'Monitor')] as Object[],
                          "Another user has updated this Monitor while you were editing")
                render(view: "edit", model: [monitorInstance: monitorInstance])
                return
            }
        }

        monitorInstance.properties = params

        if (!monitorInstance.save(flush: true)) {
            render(view: "edit", model: [monitorInstance: monitorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'monitor.label', default: 'Monitor'), monitorInstance.id])
        redirect(action: "show", id: monitorInstance.id)
    }

    def delete(Long id) {
        def monitorInstance = Monitor.get(id)
        if (!monitorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monitor.label', default: 'Monitor'), id])
            redirect(action: "list")
            return
        }

        try {
            monitorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'monitor.label', default: 'Monitor'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'monitor.label', default: 'Monitor'), id])
            redirect(action: "show", id: id)
        }
    }
}
