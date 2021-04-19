<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="manager.workplan.list.label.id" path="id" width="20%"/>
	<acme:list-column code="manager.workplan.list.label.begin" path="begin" width="30%"/>
	<acme:list-column code="manager.workplan.list.label.end" path="end" width="30%"/>
	<acme:list-column code="manager.workplan.list.label.isPublic" path="isPublic" width="10%"/>

</acme:list>