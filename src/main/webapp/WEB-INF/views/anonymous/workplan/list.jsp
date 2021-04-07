<%--
  Created by IntelliJ IDEA.
  User: nukeagony
  Date: 8/4/21
  Time: 00:20
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="anonymous.workplan.list.label.isPublic" path="isPublic" width="40%"/>
    <acme:list-column code="anonymous.workplan.list.label.begin" path="begin" width="30%"/>
    <acme:list-column code="anonymous.workplan.list.label.end" path="end" width="30%"/>
    <acme:list-column code="anonymous.workplan.list.label.tasks" path="tasks" width="30%"/>
</acme:list>