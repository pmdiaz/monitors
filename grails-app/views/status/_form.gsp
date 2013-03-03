<%@ page import="monitors.Status" %>



<div class="fieldcontain ${hasErrors(bean: statusInstance, field: 'agent', 'error')} required">
	<label for="agent">
		<g:message code="status.agent.label" default="Agent" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="agent" name="agent.id" from="${monitors.Agent.list()}" optionKey="id" required="" value="${statusInstance?.agent?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: statusInstance, field: 'metrics', 'error')} ">
	<label for="metrics">
		<g:message code="status.metrics.label" default="Metrics" />
		
	</label>
	<g:select name="metrics" from="${monitors.Metric.list()}" multiple="multiple" optionKey="id" size="5" value="${statusInstance?.metrics*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: statusInstance, field: 'statusTime', 'error')} required">
	<label for="statusTime">
		<g:message code="status.statusTime.label" default="Status Time" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

