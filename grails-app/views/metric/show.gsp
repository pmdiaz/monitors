
<%@ page import="monitors.Metric" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'metric.label', default: 'Metric')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-metric" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-metric" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list metric">
			
				<g:if test="${metricInstance?.metricCode}">
				<li class="fieldcontain">
					<span id="metricCode-label" class="property-label"><g:message code="metric.metricCode.label" default="Metric Code" /></span>
					
						<span class="property-value" aria-labelledby="metricCode-label"><g:fieldValue bean="${metricInstance}" field="metricCode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${metricInstance?.metricValue}">
				<li class="fieldcontain">
					<span id="metricValue-label" class="property-label"><g:message code="metric.metricValue.label" default="Metric Value" /></span>
					
						<span class="property-value" aria-labelledby="metricValue-label"><g:fieldValue bean="${metricInstance}" field="metricValue"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${metricInstance?.id}" />
					<g:link class="edit" action="edit" id="${metricInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
