<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.05.2021
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="../../fragment/subscriber_navbar.jsp"/>
<div class="row row-cols-1 row-cols-md-3 g-4 p-5">
    <c:forEach var="tariff" items="${tariffs}">
        <div class="col">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">${tariff.title}</h5>
                    <p class="card-text text-danger font-weight-bold">
                        <fmt:message key="tariff_list.label.price"/>: ${tariff.price}
                    </p>
                    <a href="#" class="btn btn-primary">
                        <fmt:message key="subscriber_tariff_list.btn.pdf_download"/>
                    </a>
                    <div class="mt-3">
                        <form class="form" action="<c:url value="/tariffs/${tariff.id}"/>" method="post">
                            <button class="btn btn-primary">
                                <fmt:message key="subscriber_tariff_list.btn.get_now"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div class="h-100">
    <ul class="pagination justify-content-center">
        <c:if test="${currentPage != 1}">
            <li class="page-item">
                <a class="page-link"
                   href="<c:url value="/tariffs/${currentServiceId}?currentPage=${currentPage - 1}"/>">
                    <fmt:message key="pagination.previous"/>
                </a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${totalPages}" var="i">
            <c:choose>
                <c:when test="${currentPage == i}">
                    <li class=" page-item active">
                        <a class="page-link" href="<c:url value="/tariffs/${currentServiceId}?currentPage=${i}"/>">
                                ${i}
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="<c:url value="/tariffs/${currentServiceId}?currentPage=${i}"/>">
                                ${i}
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <li class="page-item">
                <a class="page-link"
                   href="<c:url value="/tariffs/${currentServiceId}?currentPage=${currentPage + 1}"/>">
                    <fmt:message key="pagination.next"/>
                </a>
            </li>
        </c:if>
    </ul>
</div>
</body>
</html>
