package monitors

import org.springframework.dao.DataIntegrityViolationException

class MetricController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [metricInstanceList: Metric.list(params), metricInstanceTotal: Metric.count()]
    }

    def create() {
        [metricInstance: new Metric(params)]
    }

    def save() {
        def metricInstance = new Metric(params)
        if (!metricInstance.save(flush: true)) {
            render(view: "create", model: [metricInstance: metricInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'metric.label', default: 'Metric'), metricInstance.id])
        redirect(action: "show", id: metricInstance.id)
    }

    def show(Long id) {
        def metricInstance = Metric.get(id)
        if (!metricInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'metric.label', default: 'Metric'), id])
            redirect(action: "list")
            return
        }

        [metricInstance: metricInstance]
    }

    def edit(Long id) {
        def metricInstance = Metric.get(id)
        if (!metricInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'metric.label', default: 'Metric'), id])
            redirect(action: "list")
            return
        }

        [metricInstance: metricInstance]
    }

    def update(Long id, Long version) {
        def metricInstance = Metric.get(id)
        if (!metricInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'metric.label', default: 'Metric'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (metricInstance.version > version) {
                metricInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'metric.label', default: 'Metric')] as Object[],
                          "Another user has updated this Metric while you were editing")
                render(view: "edit", model: [metricInstance: metricInstance])
                return
            }
        }

        metricInstance.properties = params

        if (!metricInstance.save(flush: true)) {
            render(view: "edit", model: [metricInstance: metricInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'metric.label', default: 'Metric'), metricInstance.id])
        redirect(action: "show", id: metricInstance.id)
    }

    def delete(Long id) {
        def metricInstance = Metric.get(id)
        if (!metricInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'metric.label', default: 'Metric'), id])
            redirect(action: "list")
            return
        }

        try {
            metricInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'metric.label', default: 'Metric'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'metric.label', default: 'Metric'), id])
            redirect(action: "show", id: id)
        }
    }
}
