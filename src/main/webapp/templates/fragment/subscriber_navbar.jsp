<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.05.2021
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="import_taglib.jsp" %>
<%--<fmt:setBundle basename="resources"/>--%>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <%@ include file="bootsrap_import.jsp" %>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">EPAMNET</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                <c:forEach var="service" items="${services}" varStatus="idx">
                    <li class="nav-item">
                        <a class="${serviceId == service.id ? "nav-link active fw-bold" : "nav-link"}"
                           href="<c:url value="/controller?command=TariffList&serviceId=${service.id}"/>">${service.title}</a>
                    </li>
                </c:forEach>
            </ul>
            <div class="d-flex h-100 align-items-center justify-content-between">
                <jsp:include page="language_change.jsp"/>
                <a class="ms-3 btn btn-primary"
                   href="<c:url value="/controller?command=AccountDetails"/>">
                    <fmt:message key="subscriber_navbar.account_details"/>
                </a>
            </div>
        </div>
    </div>
</nav>
</body>

</html>
