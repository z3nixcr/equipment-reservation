<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Main.css">
</head>
<body>
    <div class="parent-container">
        <jsp:include page="header.jsp"/>

        <main class="main-content container">
            <div class="content-cards">
                <c:forEach var="equipment" items="${equipmentList}" varStatus="item">
                    <div class="card center">
                        <div class="card-img center">
                            <img src="${pageContext.request.contextPath}/img/${item.count}.png" alt="image item ${item.count}">
                        </div>
                        <div class="card-desc">
                            <div><p class="card-title">${equipment.name}</p></div>
                            <div class="card-link"><a class="link" href="${pageContext.request.contextPath}/ServletEquipment?action=add&itemId=${equipment.idEquipment}">Go Reserve >></a></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </main>

        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
