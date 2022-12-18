<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Main.css">
</head>
<body>
    <div class="admin-page-container">
        <div class="contents">
            <jsp:include page="left_content.jsp"/>

            <div class="contents__right">
                <p class="title">CURRENT EQUIPMENTS IN SYSTEM</p>
                <main class="main">
                    <c:forEach var="item" items="${equipmentList}">
                        <div class="product">
                            <div class="product__img">
                                <img src="${pageContext.request.contextPath}/img/${item.idEquipment}.png" alt="Image item ${item.idEquipment}">
                            </div>
                            <div class="product__desc">
                                <p>${item.name}</p>
                            </div>
                        </div>
                    </c:forEach>
                </main>
            </div>
        </div>
    </div>
</body>
</html>
