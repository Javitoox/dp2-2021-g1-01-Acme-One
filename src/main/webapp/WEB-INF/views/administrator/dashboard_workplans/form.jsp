<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.workplan.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.total-number-public-workplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPublicWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.total-number-private-workplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrivateWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.total-number-finished-workplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfFinishedWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.total-number-non-finished-workplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfNonFinishedWorkplans}"/>
		</td>
	</tr>
</table>
