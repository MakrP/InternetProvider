<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.05.2021
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/templates/fragment/import_taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/templates/fragment/subscriber_navbar.jsp"/>
<div class="container h-100 justify-content-center align-item-center">
    <div class="row ">
        <div class="col-md-6">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">
                        <fmt:message key="account_details.btn.top_up_account"/>
                    </h5>
                    <div class="mt-3">
                        <form class="form-group" action="<c:url value="/controller?command=Balance"/>" method="post">
                            <label for="total"><fmt:message key="account_details.label.balance"/></label>
                            <input id="total" name="increase_total" type="number" min="30" max="5000"/>
                            <button class="btn btn-success" type="submit">
                                <fmt:message key="increase_balance.btn.increase"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
