<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Main.css">
</head>
<body>
<div class="parent-container">
    <jsp:include page="header.jsp"/>

    <main class="main-content container">
        <p class="error1">${error_reserve}</p>
        <div class="item-info">
            <div class="item-img">
                <img src="${pageContext.request.contextPath}/img/${item.idEquipment}.png" alt="image item ${item.idEquipment}">
            </div>
            <div class="item-desc center">
                <form action="${pageContext.request.contextPath}/ServletReservation?action=reserve" method="post">
                    <div class="field">
                        ${item.name}
                    </div>
                    <div class="field">
                        ${item.inStock} items in stock
                    </div>
                    <div class="field">
                        <label for="quantity">Select quantity to reserve:</label>
                        <input type="number" name="quantity" id="quantity" min="1" max="${item.inStock}">
                    </div>
                    <div class="field">
                        <label for="fromDate">Select the starting date:</label>
                        <input type="date" name="fromDate" id="fromDate" required>
                    </div>
                    <div class="field">
                        <label for="toDate">Select the end date:</label>
                        <input type="date" name="toDate" id="toDate" required>
                    </div>
                    <input class="btn btn-1" type="submit" value="Reserve">
                </form>
            </div>
        </div>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>

