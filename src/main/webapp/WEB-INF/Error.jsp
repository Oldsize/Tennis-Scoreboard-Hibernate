<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffebee;
            color: #b71c1c;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .nav-links {
            margin-top: 10px;
            text-align: center;
        }
        .nav-links a {
            color: #b71c1c;
            margin: 0 10px;
            text-decoration: none;
        }
        .nav-links a:hover {
            text-decoration: underline;
        }
        .container {
            background-color: #ffffff;
            border: 1px solid #b71c1c;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 400px;
        }
        .container h1 {
            color: #b71c1c;
        }
        .container p {
            color: #d32f2f;
            font-size: 16px;
        }
        .container .error-code {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="nav-links">
    <a href="${pageContext.request.contextPath}/match-score">Welcome page</a>
    <a href="${pageContext.request.contextPath}/new-match/">New match</a>
    <a href="${pageContext.request.contextPath}/matches/">List matches</a>
</div>
<div class="container">
    <h1>Error Occurred</h1>
    <div class="error-code">Error Code: ${errorCode}</div>
    <p>${errorMessage}</p>
</div>
</body>
</html>
