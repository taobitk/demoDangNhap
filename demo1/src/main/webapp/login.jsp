<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title>
</head>
<body>
<div>
    <h2>Đăng Nhập</h2>
    <form action="LoginServlet" method="post">
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">Đăng Nhập</button>
    </form>
    <p style="color:red;">
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <%= errorMessage != null ? errorMessage : "" %>
    </p>
</div>
</body>
</html>
