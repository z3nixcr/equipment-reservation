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
            <div class="contents__left">
                <div class="contents__left-top">
                    <div class="title">
                        <p><span>Zenedict</span> Res-Reservation <span>&circledR;</span></p>
                        <p>System Dashboard</p>
                    </div>
                    <div class="menu">
                        <div><a class="link" href="${pageContext.request.contextPath}/ServletAdmin?option=equipments">* EQUIPMENTS *</a></div>
                        <div><a class="link" href="${pageContext.request.contextPath}/ServletAdmin?option=customers">* CUSTOMERS *</a></div>
                        <div><a class="link" href="${pageContext.request.contextPath}/ServletAdmin?option=reservations">* RESERVATIONS *</a></div>
                    </div>
                </div>

                <div class="contents__left-bottom">
                    <a class="link" href="${pageContext.request.contextPath}/UserController?action=logout">Logout (${user.fullName})</a>
                </div>
            </div>

            <div class="contents__right">
                <h2>CURRENT EQUIPMENTS IN SYSTEM</h2>
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
