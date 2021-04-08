<%--
  Created by IntelliJ IDEA.
  User: nukeagony
  Date: 8/4/21
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
    <acme:list-column path="moment" code="authenticated.announcement.list.label.moment" width="20%"/>
    <acme:list-column path="status" code="authenticated.announcement.list.label.status" width="10%"/>
    <acme:list-column path="title" code="authenticated.announcement.list.label.title" width="70%"/>
</acme:list>


