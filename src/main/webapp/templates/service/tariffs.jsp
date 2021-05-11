<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.05.2021
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../fragment/navbar.jsp"/>
<div class="row row-cols-1 row-cols-md-3 g-4 p-5">
    <c:forEach var="tariff" items="${tariffs}">
        <div class="col">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">${tariff.title}</h5>
                    <p class="card-text text-danger font-weight-bold">${tariff.price}</p>
                    <a href="#" class="btn btn-primary">Details</a>
                    <a href="#" class="btn btn-primary">Get Now</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
