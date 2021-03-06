<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.05.2021
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

</head>
<body>

    <div class="row mt-5 justify-content-center align-items-center">
        <div class="col-10 col-md-8 col-lg-6 d-flex justify-content-center">
            <form class="form" method="post" action="<c:url value="/controller?command=Login"/>">
                <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
                <label for="inputLogin" class="sr-only">Email address</label>
                <input type="text" id="inputLogin" name="login" class="form-control" placeholder="Login"
                       required="" autofocus="" minlength="1" maxlength="45">
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password"
                       required="" min="1" maxlength="45">
                <button class="btn btn-lg btn-primary btn-block mt-3" type="submit">Sign in</button>
            </form>
        </div>
        <div class="row justify-content-center align-items-center"></div>
        <c:if test="${not empty errorMessage}">
            <div class="col-2 alert alert-danger text-center" role="alert">
                    ${errorMessage}
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
