<div class="contents__left">
    <div class="contents__left-top">
        <div class="title">
            <p><span>Zenedict</span> Res-Reservation <span>&circledR;</span></p>
            <p>System Dashboard</p>
        </div>
        <div class="menu">
            <div><a class="link" href="${pageContext.request.contextPath}/ServletAdmin?option=equipments">* EQUIPMENTS *</a></div>
            <div><a class="link" href="${pageContext.request.contextPath}/ServletAdmin?option=customers">* CUSTOMERS *</a></div>
            <div><a class="link" href="${pageContext.request.contextPath}/ServletAdmin?option=bookings">* RESERVATIONS *</a></div>
        </div>
    </div>

    <div class="contents__left-bottom">
        <a class="link" href="${pageContext.request.contextPath}/UserController?action=logout">Logout (${user.fullName})</a>
    </div>
</div>