<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.05.2021
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/templates/fragment/import_taglib.jsp" %>
<html>
<head>
    <title>404</title>
    <%@include file="/templates/fragment/bootsrap_import.jsp" %>
</head>
<body>
<div class="container d-flex align-items-center justify-content-center">
    <img class="image-responsive" src="/static/Effective_Programming_for_America_logo.svg.png"/>
    <h1>Ця сторінка недоступна.
        Спробуйте пошукати щось інше.</h1>
    <a class="mt-3"
       href="<c:url value="${account.role == 'SUBSCRIBER' ? '/controller?command=TariffList' : 'controller?command=AdminIndex'}"/>">
        Go to main page
    </a>
</div>
</body>
</html>
