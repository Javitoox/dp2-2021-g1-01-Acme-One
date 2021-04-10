<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="administrator.announcement.form.label.title" path="title"/>	
	<acme:form-select code="administrator.announcement.form.label.status" path="status">
		<acme:form-option code="INFO" value="INFO" selected="${status == 'INFO'}"/>
		<acme:form-option code="WARNING" value="WARNING" selected="${status == 'WARNING'}"/>
		<acme:form-option code="IMPORTANT" value="IMPORTANT" selected="${status == 'IMPORTANT'}"/>
	</acme:form-select>
	<acme:form-textarea code="administrator.announcement.form.label.text" path="text"/>
	<acme:form-textbox code="administrator.announcement.form.label.info" path="info"/>
	<acme:form-return code="administrator.announcement.form.button.return"/>
</acme:form>
