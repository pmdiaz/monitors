<%@ page import="monitors.Monitor" %>



<div class="fieldcontain ${hasErrors(bean: monitorInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="monitor.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${monitorInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: monitorInstance, field: 'agents', 'error')} ">
	<label for="agents">
		<g:message code="monitor.agents.label" default="Agents" />
		
	</label>
	<g:select name="agents" from="${monitors.Agent.list()}" multiple="multiple" optionKey="id" size="5" value="${monitorInstance?.agents*.id}" class="many-to-many"/>
</div>

