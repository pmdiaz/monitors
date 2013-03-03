<%@ page import="monitors.Metric" %>



<div class="fieldcontain ${hasErrors(bean: metricInstance, field: 'metricCode', 'error')} required">
	<label for="metricCode">
		<g:message code="metric.metricCode.label" default="Metric Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="metricCode" required="" value="${metricInstance?.metricCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: metricInstance, field: 'metricValue', 'error')} required">
	<label for="metricValue">
		<g:message code="metric.metricValue.label" default="Metric Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="metricValue" required="" value="${metricInstance?.metricValue}"/>
</div>

