<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.05.2021
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="import.jsp" %>
<%--<fmt:setBundle basename="resources"/>--%>
<html>
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

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

            <jsp:useBean id="account" scope="session" class="ua.epam.internetprovider.entity.Account"/>
            <div class="d-flex">
                <form class="align-self-center" name="en_lang_form" action="<c:url value="/controller?command=Language"/>" method="post">
                    <a class="nav-link" href="javascript: changeToEngLanguageSubmit()">eng</a>
                    <input type="hidden" name="lang" value="en">
                    <input type="hidden" name="prevUrl" value="${requestScope['javax.servlet.forward.request_url']}">
                </form>
                |
                <form class="align-self-center" action="<c:url value="/controller?command=Language"/>" name="ua_lang_form" method="post">
                    <a class="nav-link" href="javascript: changeToUkrLanguageSubmit()">укр</a>
                    <input type="hidden" name="lang" value="ua">
                    <input type="hidden" name="prevUrl" value="${requestScope['javax.servlet.forward.request_url']}">
                </form>
                <a class="align-self-center btn btn-primary" href="<c:url value="/controller?command=AccountDetails"/>">
                    <fmt:message key="subscriber_navbar.account_details"/>
                </a>
            </div>
        </div>
    </div>
</nav>
</body>
<script type="text/javascript">
    function changeToEngLanguageSubmit() {
        document.en_lang_form.submit();
    }

    function changeToUkrLanguageSubmit() {
        document.ua_lang_form.submit();
    }
</script>

</html>
