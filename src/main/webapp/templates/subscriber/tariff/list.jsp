<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.05.2021
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/templates/fragment/import.jsp" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/templates/fragment/subscriber_navbar.jsp"/>
<div class="row justify-content-end mt-2 p-5">
    <div class="col-md-4">
        <select class="form-select" id="sortSelect">
            <option value="<c:url value="/controller?command=TariffList&serviceId=${serviceId}&page=1&order=asc&orderField=title"/>">
                By Title Asc
            </option>
            <option value="<c:url value="/controller?command=TariffList&serviceId=${serviceId}&page=1&order=desc&orderField=title"/>">
                By Title Desc
            </option>
            <option value="<c:url value="/controller?command=TariffList&serviceId=${serviceId}&page=1&order=asc&orderField=price"/>">
                By Price Asc
            </option>
            <option value="<c:url value="/controller?command=TariffList&serviceId=${serviceId}&page=1&order=desc&orderField=price"/>">
                By Price Desc
            </option>
        </select>
    </div>
</div>
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
        <c:if test="${page != 1}">
            <li class="page-item">
                <a class="page-link"
                   href="<c:url value="/controller?command=TariffList&serviceId=${serviceId}&orderField=${orderField}&order=${order}&page=${page - 1}"/>">
                    <fmt:message key="pagination.previous"/>
                </a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${totalPages}" var="i">
            <c:choose>
                <c:when test="${page == i}">
                    <li class=" page-item active">
                        <a class="page-link"
                           href="<c:url value="/controller?command=TariffList&serviceId=${serviceId}&orderField=${orderField}&order=${order}&page=${i}"/>">
                                ${i}
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="<c:url value="/controller?command=TariffList&serviceId=${serviceId}&orderField=${orderField}&order=${order}&page=${i}"/>">
                                ${i}
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${page < totalPages}">
            <li class="page-item">
                <a class="page-link"
                   href="<c:url value="/controller?command=TariffList&serviceId=${serviceId}&orderField=${orderField}&order=${order}&page=${page + 1}"/>">
                    <fmt:message key="pagination.next"/>
                </a>
            </li>
        </c:if>
    </ul>
</div>
</body>
<script>
    $(document).ready(function () {
        $('#sortSelect').change(function () {
            console.log("Listener work")
            window.location.href = $(this).val()
        });
    });
</script>
</html>
