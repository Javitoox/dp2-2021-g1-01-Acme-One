<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.task.list.label.title" path="title" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.begin" path="begin" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.link" path="link" width="20%"/>
</acme:list>