<%@page language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	

<acme:form readonly="${!ItsMine}">
    <acme:form-hidden path="id"/>
    <acme:form-textbox code="manager.workplan.form.label.title" path="title"/>
    <jstl:if test="${command!='create'}">    
   		<acme:form-textbox readonly="true" code="manager.workplan.form.label.workload" path="workload"/> 	
   		<acme:form-textbox readonly="true" code="manager.workplan.form.label.executionPeriod" path="executionPeriod"/> 	   		
    </jstl:if>   
    
    <acme:form-moment code="manager.workplan.form.label.begin" path="begin"/>
     <jstl:if test="${recommendedInitialDate != null && ItsMine}">	    
	    <p class="text-success"><acme:message code="manager.workplan.form.label.begin.recommend"/><acme:message code="${recommendedInitialDate}"/></p>
    </jstl:if>
    <acme:form-moment code="manager.workplan.form.label.end" path="end"/>   
    
    <jstl:if test="${recommendedEndDate != null && ItsMine}">
	   	<p class="text-success"><acme:message code="manager.workplan.form.label.end.recommend"/><acme:message code="${recommendedEndDate}"/></p>
    </jstl:if>
    
    <jstl:if test="${command=='create'}">    
    	<acme:form-checkbox code="manager.workplan.form.label.isPublic" path="isPublic"/>
    </jstl:if>

    <jstl:if test="${command=='create'}">  		   		   	   		    		
	    <acme:form-submit code="manager.workplan.form.button.create" action="/manager/work-plan/create"/>    
    </jstl:if>  
    <acme:form-submit test="${canPublish && ItsMine && (command=='show'|| command =='update' || errorsAdd)}" code="manager.workplan.form.button.publish" action="/manager/work-plan/publish"/>
    <acme:form-submit test="${ItsMine && (command=='show' || command =='update' || errorsAdd)}" code="manager.workplan.form.button.delete" action="/manager/work-plan/delete"/>
    <acme:form-submit test="${ItsMine && (command=='show'|| command =='update' || errorsAdd)}" code="manager.workplan.form.button.update" action="/manager/work-plan/update"/>
    <acme:form-return code="manager.workplan.form.button.return"/>
</acme:form>
<br><br>

<!-- TASK TABLE -->
<jstl:if test="${command=='show'|| command =='update' || errorsAdd}">    
		   <div class="table-responsive">
		   <table class="table table-striped table-condensed table-hover nowrap w-100">
		   	  <caption><acme:message code="manager.workplan.form.label.tasks"/></caption>
			  <thead>
				    <tr>
				      <th scope="col"><acme:message code="manager.workplan.form.label.tasks.id"/></th>
				      <th scope="col"><acme:message code="manager.workplan.form.label.tasks.title"/></th>
				      <th scope="col"><acme:message code="manager.workplan.form.label.tasks.public"/></th>
				      <th scope="col"></th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach items="${tasks}" var="task">
					  	<tr class="table-light">
					      <td>${task.id}</td>
					      <td>${task.title}</td>
					      <td><acme:message code="manager.workplan.form.label.tasks.public.${task.isPublic}"/></td>
					      <td>
					      <jstl:if test="${ItsMine}">
					      	<acme:form>
					      		<input type="hidden" name="taskId" value="${task.id}">
					      		<input type="hidden" name="workplanId" value="${id}">
					      		<acme:form-submit code="manager.workplan.form.button.removeTask" action="/manager/work-plan/remove_task"/>   
					      	</acme:form>
					      </jstl:if>
					      </td>
					    </tr>
					</c:forEach>
				    </tbody>
			   </table>
		    </div>
    </jstl:if>

<!-- ADD TASK  -->
<jstl:if test="${(ItsMine && (command=='show'|| command =='update')) || errorsAdd}">    
	<center>
	<acme:form>
		<acme:form-select code="manager.workplan.form.select.addTask" path="taskSelected">
			<c:forEach items="${tasksEneabled}" var="task">
				<acme:form-option code="${task.title} - ${task.description}" value="${task.id}"/>	
			</c:forEach>
		</acme:form-select>
		<acme:form-submit code="manager.workplan.form.button.addTask" action="/manager/work-plan/add_task"/>
	</acme:form>
	</center>
</jstl:if>



