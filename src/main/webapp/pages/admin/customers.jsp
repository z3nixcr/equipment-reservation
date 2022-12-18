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
            <p class="title">CURRENT CUSTOMERS IN SYSTEM</p>
            <div class="my-rents-container">
                <c:if test="${userList != null}">
                    <table class="items-table">
                        <thead>
                        <tr>
                            <th>User ID</th>
                            <th>Full Name</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Contact</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="user" items="${userList}">
                            <tr>
                                <td>${user.idUser}</td>
                                <td>${user.fullName}</td>
                                <td>${user.username}</td>
                                <td>${user.email}</td>
                                <td>+265 ${user.phoneNumber}</td>
                            </tr>
                        </c:forEach>
                        </tbody>

                        <tfoot>
                        <tr>
                            <td colspan="3">Total Users</td>
                            <th>${totalUsers} users</th>
                        </tr>
                        </tfoot>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>

