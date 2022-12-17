<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Main.css">
</head>
<body>
<div class="main-container">
    <div class="container dis-grid">
        <header class="header">
            <h1><span>Zenedict</span> Res-Reservation <span>&circledR;</span></h1>
            <p class="error1">${login_error}</p>
        </header>
        <main class="content">
            <form class="login-form" action="${pageContext.request.contextPath}/UserController?action=login" method="post">
                <label>
                    <input type="email" name="email" placeholder="your email" required>
                </label>
                <label>
                    <input type="password" name="password" placeholder="your password" required>
                </label>
                <label>
                    <input type="submit" value="login">
                </label>
            </form>
            <div class="reg-link">
                <a href="${pageContext.request.contextPath}/UserController?action=toRegister">Register &dbkarow;</a>
            </div>
        </main>
    </div>
</div>
</body>
</html>