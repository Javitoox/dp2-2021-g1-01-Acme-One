<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message
		code="administrator.workplan.dashboard.form.title.general-indicators" />
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="administrator.workplan.dashboard.form.label.total-number-public-workplan" />
		</th>
		<td><acme:print value="${totalNumberOfPublicWorkplans}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.workplan.dashboard.form.label.total-number-private-workplan" />
		</th>
		<td><acme:print value="${totalNumberOfPrivateWorkplans}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.workplan.dashboard.form.label.total-number-finished-workplan" />
		</th>
		<td><acme:print value="${totalNumberOfFinishedWorkplans}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.workplan.dashboard.form.label.total-number-non-finished-workplan" />
		</th>
		<td><acme:print value="${totalNumberOfNonFinishedWorkplans}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.workplan.dashboard.form.label.average-periods" />
		</th>
		<td><acme:print value="${averageNumberOfPeriods}" /> <acme:message
				code="administrator.workplan.dashboard.form.label.days" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.workplan.dashboard.form.label.minimum-periods" />
		</th>
		<td><acme:print value="${minimumNumberOfPeriods}" /> <acme:message
				code="administrator.workplan.dashboard.form.label.days" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.workplan.dashboard.form.label.maximum-periods" />
		</th>
		<td><acme:print value="${maximumNumberOfPeriods}" /> <acme:message
				code="administrator.workplan.dashboard.form.label.days" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.workplan.dashboard.form.label.average-workloads" />
		</th>
		<td><acme:print value="${averageNumberOfWorkloads}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.workplan.dashboard.form.label.minimum-number-workloads" />
		</th>
		<td><acme:print value="${minimumNumberOfWorkloads}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.workplan.dashboard.form.label.maximum-number-workloads" />
		</th>
		<td><acme:print value="${maximumNumberOfWorkloads}" /></td>
	</tr>
</table>

<h2>
	<acme:message
		code="administrator.workplan.dashboard.form.title.application-statuses" />
</h2>
<div>
	<canvas id="canvas"></canvas>
</div>
<script type="text/javascript">
	$(document).ready(
			function() {

				var data = {
					labels : [
							"TOTAL", "PRIVATE", "PUBLIC"
					],

					datasets : [
						{
							data : [
									<jstl:out value="${totalNumberOfPublicWorkplans} + ${totalNumberOfPrivateWorkplans}" />,									
									<jstl:out value="${totalNumberOfPrivateWorkplans}" />,
									<jstl:out value="${totalNumberOfPublicWorkplans}" />
							],
							backgroundColor : [
									'rgba(45, 144, 221, 1)', 'rgba(250, 10, 10, 1)', 'rgba(205, 205, 3, 1)'
							]

						}
					]
				};

				var options = {
					scales : {
						yAxes : [
							{
								ticks : {
									suggestedMin : 0.0,
									suggestedMax : <jstl:out value="${totalNumberOfPublicWorkplans} + ${totalNumberOfPrivateWorkplans}" />
								}
							}
						]
					},
					legend : {
						display : true
					},
					title : {
						display : true,
						text : "WorkPlans"
					}
				};

				var canvas, context;
				canvas = document.getElementById("canvas");
				context = canvas.getContext("2d");
				new Chart(context, {
					type : "pie",
					data : data,
					options : options
				});

			});
</script>