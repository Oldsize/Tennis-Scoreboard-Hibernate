<%--
  Created by IntelliJ IDEA.
  User: springboot
  Date: 25.06.2024
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Match</title>
    <script>
        function validateForm() {
            var name1 = document.getElementById("name1").value;
            var name2 = document.getElementById("name2").value;

            if (name1.trim() === "" || name2.trim() === "") {
                alert("Пожалуйста, заполните оба поля.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<form method="post" action="processForm.jsp" onsubmit="return validateForm()">
    <label for="name1">Имя первого игрока:</label>
    <input type="text" id="name1" name="name1" maxlength="25" placeholder="Игрок 1">
    <br>
    <label for="name2">Имя второго игрока:</label>
    <input type="text" id="name2" name="name2" maxlength="25" placeholder="Игрок 2">
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>