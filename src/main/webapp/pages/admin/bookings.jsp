<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bookings</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Main.css">
</head>
<body>
<div class="admin-page-container">
    <div class="contents">
        <jsp:include page="left_content.jsp"/>

        <div class="contents__right">
            <p class="title">CURRENT BOOKINGS IN SYSTEM</p>
            <div class="my-rents-container">
                <c:if test="${bookings != null}">
                    <table class="items-table">
                        <thead>
                        <tr>
                            <th>ID Booking</th>
                            <th>Customer</th>
                            <th>Item</th>
                            <th>Price 1/day</th>
                            <th>Quantity</th>
                            <th>Starting Date</th>
                            <th>End Date</th>
                            <th>Subtotal</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="book" items="${bookings}">
                            <tr>
                                <td>${book.idReservation}</td>
                                <td>${book.userName}</td>
                                <td>${book.equipmentName}</td>
                                <td>$ ${book.price}</td>
                                <td>${book.quantity}</td>
                                <td>${book.fromDate}</td>
                                <td>${book.toDate}</td>
                                <td>$ ${book.totalPrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>

                        <tfoot>
                        <tr>
                            <td colspan="7">Total Amount</td>
                            <th>$ ${totalPrice}</th>
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

