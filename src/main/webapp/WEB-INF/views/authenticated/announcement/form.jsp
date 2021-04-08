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

<acme:form readonly="true">
    <acme:form-textbox path="title" code="administrator.announcement.form.label.title"/>
    <acme:form-select path="status" code="administrator.announcement.form.label.status">
        <acme:form-option value="INFO" code="INFO" selected="${status == 'INFO'}"/>
        <acme:form-option value="INFO" code="WARNING" selected="${status == 'WARNING'}"/>
        <acme:form-option value="INFO" code="IMPORTANT" selected="${status == 'IMPORTANT'}"/>
    </acme:form-select>
    <acme:form-textarea path="text" code="administrator.announcement.form.label.text"/>
    <acme:form-textbox path="info" code="administrator.announcement.form.label.info"/>

    <acme:form-return code="administrator.announcement.form.button.return"/>
</acme:form>