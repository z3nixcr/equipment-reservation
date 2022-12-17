<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Main.css">
</head>
<body>
<div class="main-container">
    <div class="container dis-flex-col">
        <header class="header">
            <h1><span>Zenedict</span> Res-Reservation <span>&circledR;</span></h1>
            <p class="error1">${register_error}</p>
        </header>
        <main class="content">
            <form class="register-form" action="${pageContext.request.contextPath}/UserController?action=register" method="post">
                <label>
                    <input type="text" name="name" placeholder="YOUR FULL NAME" required>
                </label>
                <label>
                    <input type="email" name="email" placeholder="YOUR EMAIL" required>
                </label>
                <label>
                    <input type="text" name="phone" placeholder="YOUR PHONE NUMBER" required>
                </label>
                <label>
                    <input type="text" name="username" placeholder="CREATE USERNAME" required>
                </label>
                <label>
                    <input type="password" name="password" placeholder="CREATE PASSWORD" required>
                </label>
                <label>
                    <input type="submit" value="REGISTER">
                </label>
            </form>
            <div class="log-link">
                <a href="${pageContext.request.contextPath}/UserController?action=toLogin">&DoubleLeftArrow; Back To Login</a>
            </div>
        </main>
    </div>
</div>
</body>
</html>
