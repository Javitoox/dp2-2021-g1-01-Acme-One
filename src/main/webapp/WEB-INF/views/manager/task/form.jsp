<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
    <acme:form-hidden path="id"/>
    <acme:form-textbox path="title" code="manager.task.form.label.title"/>
    <acme:form-moment path="begin" code="manager.task.form.label.begin"/>
    <acme:form-moment path="end" code="manager.task.form.label.end"/>
    <acme:form-double path="workload" code="manager.task.form.label.workload"/>
    <acme:form-url path="link" code="manager.task.form.label.link"/>
    <acme:form-textarea path="description" code="manager.task.form.label.description"/>
     <jstl:if test="${command=='show' && finalMode=='true'}">
     <acme:form-double  code="manager.task.form.label.executionPeriod" path="executionPeriod" readonly="true"/>
    </jstl:if>
    <jstl:if test="${command=='create'}">
    <acme:form-checkbox code="manager.task.form.label.isPublic" path="isPublic"/>
    </jstl:if>
    <acme:form-submit test="${command=='create'}" code="manager.task.form.button.create" 
    action="/manager/task/create"/>
    <acme:form-submit test= "${command=='show'}" code="manager.task.form.button.update" action="/manager/task/update"/>
    <acme:form-submit test= "${command=='show'}" code="manager.task.form.button.delete" action="/manager/task/delete"/>
    <acme:form-submit test= "${command=='show' && isPublic=='false'}" code="manager.task.form.button.publish" action="/manager/task/publish"/>
    <acme:form-submit test="${command == 'update'}" code="manager.task.form.button.update" action="/manager/task/update"/>
    <acme:form-submit test="${command == 'update'}" code="manager.task.form.button.delete" action="/manager/task/delete"/>
    <acme:form-submit test="${command == 'update'}" code="manager.task.form.button.publish" action="/manager/task/publish"/>
    <acme:form-submit test="${command=='publish'}" code="manager.task.form.button.update" action="/manager/task/update"/>
    <acme:form-submit test="${command=='publish'}" code="manager.task.form.button.delete" action="/manager/task/delete"/>
    <acme:form-submit test= "${command=='publish'}" code="manager.task.form.button.publish" action="/manager/task/publish"/>		
    <acme:form-return code="manager.task.form.button.return" />
    
</acme:form>
