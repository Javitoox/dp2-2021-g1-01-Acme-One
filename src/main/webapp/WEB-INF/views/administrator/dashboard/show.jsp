<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.dashboard.title" path="title" width="20%"/>
	<acme:form-submit code="administrator.dashboard.title" action="/administrator/dashboard/show"/>
</acme:list>