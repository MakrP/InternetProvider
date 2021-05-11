<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.05.2021
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Service</title>
</head>
<body>
<jsp:include page="../fragment/navbar.jsp"/>
<jsp:useBean id="services" scope="request" type="java.util.List"/>
     <c:forEach var="service" items="${services}" >
        <li>${service.title}</li>
     </c:forEach>
     <ul></ul>
</body>
</html>
