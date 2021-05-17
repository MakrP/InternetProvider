<%@ include file="/templates/fragment/import.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/templates/fragment/admin_navbar.jsp"/>
<div class="text-center mt-3">
    <a class="btn btn-primary" href="<c:url value="/tariff/create"/>">
        <fmt:message key="admin_tariff_create.btn.create"/>
    </a>
</div>
<div class="row justify-content-center mt-4">
    <div class="col-md-6">
        <table class="table table-responsive">
            <thead>
            <tr>
                <th scope="col">
                    <fmt:message key="admin_tariff_list.theader.title"/>
                </th>
                <th scope="col">
                    <fmt:message key="admin_tariff_list.theader.price"/>
                </th>
                <th scope="col">
                    <fmt:message key="admin_tariff_list.theader.service"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="serviceService" class="ua.epam.internetprovider.service.TariffService"/>
            <c:forEach var="tariff" items="${tariffs}">
                <tr>
                    <td>${tariff.title}</td>
                    <td>${tariff.price}</td>
                    <td>${serviceService.getTariffService(tariff).title}</td>
                    <td>
                        <a class="btn btn-success" href="<c:url value="/tariffs/update?id=${tariff.id}"/>">
                            <fmt:message key="admin_tariff_list.btn.update"/>
                        </a>
                        <a class="btn btn-danger ml-3" href="<c:url value="/tariff/delete?id=${tariff.id}"/>">
                            <fmt:message key="admin_tariff_list.btn.delete"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
