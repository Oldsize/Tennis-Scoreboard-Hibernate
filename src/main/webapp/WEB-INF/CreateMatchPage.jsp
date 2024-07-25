<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Match</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e0f7fa;
            color: #006064;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #ffffff;
            border: 1px solid #004d40;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin-top: 50px;
        }
        .container h1 {
            text-align: center;
            color: #004d40;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #004d40;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #004d40;
            color: white;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }
        input[type="submit"]:hover {
            background-color: #00332e;
        }
        .nav-bar {
            position: absolute;
            top: 0;
            width: 100%;
            background-color: #004d40;
            color: white;
            text-align: center;
            padding: 10px 0;
        }
        .nav-bar a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-size: 16px;
            transition: color 0.3s ease;
        }
        .nav-bar a:hover {
            color: #b2dfdb;
        }
    </style>
    <script>
        function validateForm() {
            var name1 = document.getElementById("name1").value.trim();
            var name2 = document.getElementById("name2").value.trim();
            if (name1 === "" || name2 === "") {
                alert("Please fill out both fields.");
                return false;
            }
            if (name1 === name2) {
                alert("Player names must be different.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<div class="nav-bar">
    <a href="${pageContext.request.contextPath}/">Welcome Page</a>
    <a href="${pageContext.request.contextPath}/matches/">Matches List</a>
</div>

<div class="container">
    <h1>Create Match</h1>
    <% String contextPath = request.getContextPath(); %>
    <form method="post" action="<%= contextPath %>/new-match/" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="name1">Player 1 Name:</label>
            <input type="text" id="name1" name="player1" maxlength="25" placeholder="Player 1">
        </div>
        <div class="form-group">
            <label for="name2">Player 2 Name:</label>
            <input type="text" id="name2" name="player2" maxlength="25" placeholder="Player 2">
        </div>
        <input type="submit" value="Create">
    </form>
</div>

</body>
</html>