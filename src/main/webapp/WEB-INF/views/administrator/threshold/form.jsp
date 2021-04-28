<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
    <acme:form-textbox path="thresholdNumber" code="administrator.word.form.threshold" />

    <%-- <acme:form-submit action="/administrator/word/create" code="administrator.word.form.button.create" />
    <acme:form-return action="/administrator/word/list" code="administrator.word.form.button.return" /> --%>
</acme:form>