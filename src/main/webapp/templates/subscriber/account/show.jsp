<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.05.2021
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="../../fragment/import.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../fragment/subscriber_navbar.jsp"/>
<div class="container p-4">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="table-responsive">
                    <table class="table bio-table">
                        <tbody>
                        <tr>
                            <td><fmt:message key="account_details.label.name"/>:</td>
                            <td>${subscriber.name}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="account_details.label.surname"/>:</td>
                            <td>${subscriber.surname}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="account_details.label.balance"/>:</td>
                            <td>${subscriber.balance}</td>
                            <td>
                                <a class="btn btn-success" href="<c:url value="/balance"/>">
                                    <fmt:message key="account_details.btn.top_up_account"/>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message key="account_details.label.status"/>:</td>
                            <td>${subscriber.status}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="account_details.label.active_tariffs"/>:</td>
                            <td>
                                <c:forEach var="tariff" items="${activeTariffs}" varStatus="idx">
                                    ${tariff.title}
                                    ${idx.index != activeTariffs.size() - 1 ? ',' : ''}
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message key="account_details.label.login"/></td>
                            <td>${account.login}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="account_details.label.password"/></td>
                            <td>${account.password}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="text-center">
                    <form action="<c:url value="/logout"/>" method="post" class="align-center">
                        <button class="button btn-danger" type="submit">
                            <fmt:message key="account_details.btn.logout"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
