<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.05.2021
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/templates/fragment/import.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/templates/fragment/admin_navbar.jsp"/>
<div class="text-center mt-3">
    <a class="btn btn-primary" href="<c:url value="/controller?command=SubscriberCreate"/>">
        <fmt:message key="admin_subscriber_list.btn.register"/>
    </a>
</div>
<div class="row justify-content-center mt-4">
    <div class="col-md-6">
        <table class="table table-responsive">
            <thead>
            <tr>
                <th scope="col">
                    <fmt:message key="admin_subscriber_list.label.name"/>
                </th>
                <th scope="col">
                    <fmt:message key="admin_subscriber_list.label.surname"/>
                </th>
                <th scope="col">
                    <fmt:message key="admin_subscriber_list.label.status"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="subscriber" items="${subscribers}">
                <jsp:useBean id="subscriberStatus" class="java.lang.String"/>
                <tr>
                    <td>${subscriber.name}</td>
                    <td>${subscriber.surname}</td>
                    <td>${subscriber.status}</td>
                    <td>
                        <form class="form-group" method="post" action="subscriber/changeStatus">
                            <button class="${subscriber.status == 'ACTIVE' ? 'btn btn-danger' : 'btn btn-secondary'}">
                                    ${subscriber.status == 'ACTIVE' ? 'BLOCK' : 'UNBLOCK'}
                            </button>
                        </form>
                            <%-- Add Localization to button ^ --%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</body>
</html>
