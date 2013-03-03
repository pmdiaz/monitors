
<%@ page import="monitors.Status" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'status.label', default: 'Status')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-status" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-status" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list status">
			
				<g:if test="${statusInstance?.statusTime}">
				<li class="fieldcontain">
					<span id="statusTime-label" class="property-label"><g:message code="status.statusTime.label" default="Status Time" /></span>
					
						<span class="property-value" aria-labelledby="statusTime-label"><g:fieldValue bean="${statusInstance}" field="statusTime"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${statusInstance?.agent}">
				<li class="fieldcontain">
					<span id="agent-label" class="property-label"><g:message code="status.agent.label" default="Agent" /></span>
					
						<span class="property-value" aria-labelledby="agent-label"><g:link controller="agent" action="show" id="${statusInstance?.agent?.id}">${statusInstance?.agent?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${statusInstance?.metrics}">
				<li class="fieldcontain">
					<span id="metrics-label" class="property-label"><g:message code="status.metrics.label" default="Metrics" /></span>
					
						<g:each in="${statusInstance.metrics}" var="m">
						<span class="property-value" aria-labelledby="metrics-label"><g:link controller="metric" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${statusInstance?.id}" />
					<g:link class="edit" action="edit" id="${statusInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
