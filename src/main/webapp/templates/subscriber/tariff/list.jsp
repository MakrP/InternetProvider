<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.05.2021
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/templates/fragment/import_taglib.jsp" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <%@ include file="/templates/fragment/bootsrap_import.jsp" %>
    <script>
        function showToast() {
            $('.toast').toast('show');
        }

        function showModal() {
            $('#modal').modal('show');
        }
    </script>
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
                    <p class="card-text text-danger fw-bold">
                        <fmt:message key="tariff_list.label.price"/>: ${tariff.price}
                    </p>
                    <a href="<c:url value="/controller?command=PdfGenerate&tariffId=${tariff.id}"/>"
                       target="_blank"
                       class="btn btn-primary">
                        <i class="fa fa-download" aria-hidden="true"></i>
                        <fmt:message key="subscriber_tariff_list.btn.pdf_download"/>
                    </a>
                    <div class="mt-3">
                        <form method="post"
                              action="<c:url value="/controller?command=GetTariff&tariffId=${tariff.id}"/>">
                            <button type="submit" class="btn btn-primary">
                                <i class="fa fa-btc" aria-hidden="true"></i>
                                <fmt:message key="subscriber_tariff_list.btn.get_now"/>
                            </button>
                        </form>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div>
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

<div class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Warning</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>${modalMessage}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                <form method="post"
                      action="<c:url value="/controller?command=GetCommand&tariffId=${wantedTariff.id}&confirmation=true"/>">
                    <input type="hidden" name="tariffId" value="${wantedTariff.id}">
                    <button type="submit" class="btn btn-primary">Get</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div role="alert" aria-live="assertive" aria-atomic="true" class="toast" data-autohide="false">
    <div class="toast-header">
        <c:choose>
            <c:when test="${success eq false}">
                <span style="font-size: 3em; color: Tomato;">
                    <i class="fas fa-exclamation-circle"></i>
                </span>
            </c:when>
            <c:otherwise>
                <span style="font-size: 3em; color: Green;">
                  <i class="fas fa-check-circle" aria-hidden="true"></i>
                </span>
            </c:otherwise>
        </c:choose>
        <strong class="me-auto">Message</strong>
        <button type="button" class="btn-close" data-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body ${success == true ? 'text-success' : 'text-danger'}">
        ${toastMessage}
    </div>
</div>
<c:if test="${not empty toastMessage}">
    <script>showToast()</script>
</c:if>
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
