<%--
  Created by IntelliJ IDEA.
  User: springboot
  Date: 25.06.2024
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="en"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Create Match</title>
</head>
<body>
    <form action="processHome" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name">
        <input type="submit" value="Submit">
    </form>
</body>
</html>
