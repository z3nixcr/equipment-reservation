<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>My Rents</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Main.css">
</head>
<body>
<div class="parent-container">
  <jsp:include page="header.jsp"/>

  <main class="main-content">
    <h1>Current items in my reservations</h1>
    <div class="my-rents-container">
      <c:if test="${items != null}">
        <table class="items-table">
          <thead>
          <tr>
            <th>#</th>
            <th>Item name</th>
            <th>Price unit/day</th>
            <th>Start date</th>
            <th>End date</th>
            <th>Quantity</th>
            <th>Subtotal</th>
            <th colspan="2">Modify</th>
          </tr>
          </thead>

          <tbody>
          <c:forEach var="item" items="${items}" varStatus="i">
            <tr>
              <td>${i.count}</td>
              <td class="ta-l">${item.equipmentName}</td>
              <td>$ ${item.price}</td>
              <td>${item.fromDate}</td>
              <td>${item.toDate}</td>
              <td>${item.quantity}</td>
              <td>$ ${item.totalPrice}</td>
              <td>
                <a href="${pageContext.request.contextPath}/ServletReservation?action=edit&id=${item.idReservation}">
                  <svg class="edit" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--! Font Awesome Pro 6.2.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                    <path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.8 15.7-7.4 21.9-13.5L437.7 172.3 339.7 74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z"/>
                  </svg>
                </a>
              </td>
              <td>
                <a href="${pageContext.request.contextPath}/ServletReservation?action=delete&id=${item.idReservation}">
                  <svg class="delete" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.2.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                    <path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/>
                  </svg>
                </a>
              </td>
            </tr>
          </c:forEach>
          </tbody>

          <tfoot>
            <tr>
              <td colspan="3">Total Price</td>
              <th>$ ${total}</th>
            </tr>
          </tfoot>
        </table>
      </c:if>
    </div>
  </main>

  <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
