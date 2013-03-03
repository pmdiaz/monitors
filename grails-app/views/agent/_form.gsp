<%@ page import="monitors.Agent" %>



<div class="fieldcontain ${hasErrors(bean: agentInstance, field: 'agentName', 'error')} ">
	<label for="agentName">
		<g:message code="agent.agentName.label" default="Agent Name" />
		
	</label>
	<g:textField name="agentName" value="${agentInstance?.agentName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: agentInstance, field: 'currentStatus', 'error')} required">
	<label for="currentStatus">
		<g:message code="agent.currentStatus.label" default="Current Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="currentStatus" name="currentStatus.id" from="${monitors.Status.list()}" optionKey="id" required="" value="${agentInstance?.currentStatus?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: agentInstance, field: 'monitors', 'error')} ">
	<label for="monitors">
		<g:message code="agent.monitors.label" default="Monitors" />
		
	</label>
	
</div>

