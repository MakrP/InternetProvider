<%@ taglib prefix="fmtL" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 16.05.2021
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/templates/fragment/import_taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/templates/fragment/admin_navbar.jsp"/>
<div class="text-center mt-2">
    <h3 class="display">
        <fmt:message key="admin_tariff_list.label.create_tariff"/>
    </h3>
</div>
<div class="mt-2 p-4 w-100">
    <form action="<c:url value="/controller?command=TariffCreate"/>" method="post">
        <div class="form-group mt-2">
            <label for="titleInput">Title</label>
            <input type="text" class="form-control" id="titleInput" name="title" placeholder="Title" required=""
                   minlength="1"
                   maxlength="45">
        </div>
        <div class="form-group mt-2">
            <label for="priceInput">Price</label>
            <input type="number" class="form-control" id="priceInput" name="price" placeholder="Price"
                   required=""
                   min="1" max="1000">
        </div>
        <div class="form-group mt-2">
            <label for="serviceSelect">Service</label>
            <select class="form-select" id="serviceSelect" name="service">
                <c:forEach var="service" items="${services}">
                    <option value="${service.id}">${service.title}</option>
                </c:forEach>
            </select>
        </div>
        <div class="w-100 text-center mt-2">
            <button type="submit" class="btn btn-primary">
                <fmt:message key="admin_tariff_create.btn.create"/>
            </button>
        </div>
    </form>
</div>
</body>
</html>
