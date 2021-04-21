<%@page language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<acme:form readonly="${readonly}">
    <acme:form-hidden path="id"/>
    <acme:form-double code="manager.workplan.form.label.workload" path="workload"/> 	
    <acme:form-moment code="manager.workplan.form.label.begin" path="begin"/>
    <acme:form-moment code="manager.workplan.form.label.end" path="end"/>   
    <acme:form-checkbox code="manager.workplan.form.label.isPublic" path="isPublic"/>
    
    <jstl:if test="${command=='show'}">    
 		<jstl:if test="${tasks.size()>0}">
		   <div class="table-responsive">
		   <table class="table table-striped table-condensed table-hover nowrap w-100">
		   	  <caption><acme:message code="manager.workplan.form.label.tasks"/></caption>
			  <thead>
				    <tr>
				      <th scope="col"><acme:message code="manager.workplan.form.label.tasks.id"/></th>
				      <th scope="col"><acme:message code="manager.workplan.form.label.tasks.title"/></th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach items="${tasks}" var="task">
					  	<tr class="table-light">
					      <td>${task.id}</td>
					      <td>${task.title}</td>
					    </tr>
					</c:forEach>
				    </tbody>
			   </table>
		    </div>
    	</jstl:if>
    </jstl:if>
           
    <jstl:if test="${command=='create'}">
    
   		<!--<acme:form-select code="manager.workplan.form.label.tasks" path="tasks" >
   			<c:forEach items="${tasksEneabled}" var="task" >
   				<acme:form-option code="${task.title}" value="${task.id}"/>   			
   			</c:forEach>	
   		</acme:form-select>  -->
   		   		   	   		    		
	    <acme:form-submit code="manager.workplan.form.button.create" action="/manager/work-plan/create"/>    
    </jstl:if>
    

    <acme:form-submit test="${canDelete}" code="manager.workplan.form.button.delete" action="/manager/work-plan/delete"/>
    <acme:form-return code="manager.workplan.form.button.return"/>
</acme:form>