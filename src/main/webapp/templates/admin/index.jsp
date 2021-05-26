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
</head>
<body>
<jsp:include page="../fragment/admin_navbar.jsp"/>
<div class="row mt-4 p-4">
    <div class="col-md-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">
                    <a href="<c:url value="/controller?command=SubscriberList"/>" class="text-primary">Subscribers</a>
                </h5>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">
                    <a href="<c:url value="/controller?command=AdminTariffList"/>" class="text-primary">Tariffs</a>
                </h5>
            </div>
        </div>
    </div>
</div>
</body>
</html>
