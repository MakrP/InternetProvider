<%@ taglib prefix="fmtL" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 16.05.2021
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/templates/fragment/import.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/templates/fragment/admin_navbar.jsp"/>
<div class="text-center mt-2">
    <h3 class="display">
        <fmtL:message key="admin_subscriber_list.btn.register"/>
    </h3>
</div>
<div class="mt-2 p-4 w-100">
    <form action="<c:url value="/subscriber/create"/>" method="post">
        <div class="form-group mt-2">
            <label for="firstNameInput">First Name</label>
            <input type="text" class="form-control" id="firstNameInput" name="name" placeholder="Name" required=""
                   minlength="2"
                   maxlength="45">
        </div>
        <div class="form-group mt-2">
            <label for="secondNameInput">Second Name</label>
            <input type="text" class="form-control" id="secondNameInput" name="surname" placeholder="Second Name"
                   required=""
                   minlength="2" maxlength="45">
        </div>
        <div class="form-group mt-2">
            <label for="loginInput">Login</label>
            <input type="text" class="form-control" id="loginInput" name="login" placeholder="Login" required=""
                   minlength="2"
                   maxlength="45">
        </div>
        <div class="form-group mt-2">
            <label for="passwordInput">Password</label>
            <input type="text" class="form-control" id="passwordInput" name="password" placeholder="Password"
                   required="" minlength="8"
                   maxlength="45">
        </div>
        <div class="w-100 text-center mt-2">
            <button type="submit" class="btn btn-primary">
                <fmt:message key="admin_subscriber_list.btn.register"/>
            </button>
        </div>
    </form>
</div>
</body>
</html>
