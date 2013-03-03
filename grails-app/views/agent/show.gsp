
<%@ page import="monitors.Agent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'agent.label', default: 'Agent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-agent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-agent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list agent">
			
				<g:if test="${agentInstance?.agentName}">
				<li class="fieldcontain">
					<span id="agentName-label" class="property-label"><g:message code="agent.agentName.label" default="Agent Name" /></span>
					
						<span class="property-value" aria-labelledby="agentName-label"><g:fieldValue bean="${agentInstance}" field="agentName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${agentInstance?.currentStatus}">
				<li class="fieldcontain">
					<span id="currentStatus-label" class="property-label"><g:message code="agent.currentStatus.label" default="Current Status" /></span>
					
						<span class="property-value" aria-labelledby="currentStatus-label"><g:link controller="status" action="show" id="${agentInstance?.currentStatus?.id}">${agentInstance?.currentStatus?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${agentInstance?.monitors}">
				<li class="fieldcontain">
					<span id="monitors-label" class="property-label"><g:message code="agent.monitors.label" default="Monitors" /></span>
					
						<g:each in="${agentInstance.monitors}" var="m">
						<span class="property-value" aria-labelledby="monitors-label"><g:link controller="monitor" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${agentInstance?.id}" />
					<g:link class="edit" action="edit" id="${agentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
