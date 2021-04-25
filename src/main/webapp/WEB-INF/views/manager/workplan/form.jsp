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
    <acme:form-moment code="manager.workplan.form.label.end" path="end"/>   
    <acme:form-checkbox readonly="true" code="manager.workplan.form.label.isPublic" path="isPublic"/>
    
    <jstl:if test="${command=='show'}">    
		   <div class="table-responsive">
		   <table class="table table-striped table-condensed table-hover nowrap w-100">
		   	  <caption><acme:message code="manager.workplan.form.label.tasks"/></caption>
			  <thead>
				    <tr>
				      <th scope="col"><acme:message code="manager.workplan.form.label.tasks.id"/></th>
				      <th scope="col"><acme:message code="manager.workplan.form.label.tasks.title"/></th>
				      <th scope="col"><acme:message code="manager.workplan.form.label.tasks.public"/></th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach items="${tasks}" var="task">
					  	<tr class="table-light">
					      <td>${task.id}</td>
					      <td>${task.title}</td>
					      <td><acme:message code="manager.workplan.form.label.tasks.public.${task.isPublic}"/></td>
					    </tr>
					</c:forEach>
				    </tbody>
			   </table>
		    </div>
    </jstl:if>
    <acme:form-submit test="${canDelete && command=='show'}" code="manager.workplan.form.button.publish" action="/manager/work-plan/publish"/>
    
    <jstl:if test="${command=='create'}">  		   		   	   		    		
	    <acme:form-submit code="manager.workplan.form.button.create" action="/manager/work-plan/create"/>    
    </jstl:if>
    
    <acme:form-submit test="${canPublish && command=='show'}" code="manager.workplan.form.button.publish" action="/manager/work-plan/publish"/>
    <acme:form-submit test="${ItsMine && command=='show'}" code="manager.workplan.form.button.delete" action="/manager/work-plan/delete"/>
    <acme:form-submit test="${ItsMine && command=='show'}" code="manager.workplan.form.button.update" action="/manager/work-plan/update"/>
    <acme:form-return code="manager.workplan.form.button.return"/>
</acme:form>