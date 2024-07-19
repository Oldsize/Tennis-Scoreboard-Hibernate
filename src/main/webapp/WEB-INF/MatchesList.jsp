<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Matches List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e0f7fa;
            color: #006064;
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
            color: #004d40;
            margin: 0 10px;
            text-decoration: none;
        }
        .nav-links a:hover {
            text-decoration: underline;
        }
        .container {
            background-color: #ffffff;
            border: 1px solid #004d40;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 600px;
            margin-top: 20px;
        }
        .match {
            background-color: #e0f2f1;
            border: 1px solid #004d40;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
        }
        .match div {
            margin: 5px 0;
        }
        .search-container {
            margin-bottom: 20px;
        }
        .search-container input {
            padding: 5px;
            border: 1px solid #004d40;
            border-radius: 5px;
            margin-right: 10px;
        }
        .search-container button {
            padding: 5px 10px;
            border-radius: 5px;
            background-color: #004d40;
            color: white;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .search-container button:hover {
            background-color: #00332e;
        }
        .pagination {
            margin-top: 20px;
        }
        .pagination button {
            padding: 5px 10px;
            border-radius: 5px;
            background-color: #004d40;
            color: white;
            border: none;
            cursor: pointer;
            margin: 0 5px;
            transition: background-color 0.3s ease;
        }
        .pagination button:hover {
            background-color: #00332e;
        }
    </style>
</head>
<body>
<div class="nav-links">
    <a href="/hibernate_practice_war_exploded/">Welcome page</a>
    <a href="/hibernate_practice_war_exploded/new-match">New match</a>
</div>

<div class="container">
    <div class="search-container">
        <form action="/hibernate_practice_war_exploded/matches" method="get">
            <label for="playerName">Find by player name:</label>
            <input type="text" id="playerName" name="find_by_player_name">
            <button type="submit">Search</button>
        </form>
    </div>

    <div class="matches-list">
        <c:if test="${not empty Match1}">
            <div class="match">
                <div>Match ID: ${Match1.getId()}</div>
                <div>Player 1: ${Match1.getPlayer1().getName()}</div>
                <div>Player 2: ${Match1.getPlayer2().getName()}</div>
                <div>Winner: ${Match1.getWinner().getName()}</div>
            </div>
        </c:if>
        <c:if test="${not empty Match2}">
            <div class="match">
                <div>Match ID: ${Match2.getId()}</div>
                <div>Player 1: ${Match2.getPlayer1().getName()}</div>
                <div>Player 2: ${Match2.getPlayer2().getName()}</div>
                <div>Winner: ${Match2.getWinner().getName()}</div>
            </div>
        </c:if>
        <c:if test="${not empty Match3}">
            <div class="match">
                <div>Match ID: ${Match3.getId()}</div>
                <div>Player 1: ${Match3.getPlayer1().getName()}</div>
                <div>Player 2: ${Match3.getPlayer2().getName()}</div>
                <div>Winner: ${Match3.getWinner().getName()}</div>
            </div>
        </c:if>
        <c:if test="${not empty Match4}">
            <div class="match">
                <div>Match ID: ${Match4.getId()}</div>
                <div>Player 1: ${Match4.getPlayer1().getName()}</div>
                <div>Player 2: ${Match4.getPlayer2().getName()}</div>
                <div>Winner: ${Match4.getWinner().getName()}</div>
            </div>
        </c:if>
        <c:if test="${not empty Match5}">
            <div class="match">
                <div>Match ID: ${Match5.getId()}</div>
                <div>Player 1: ${Match5.getPlayer1().getName()}</div>
                <div>Player 2: ${Match5.getPlayer2().getName()}</div>
                <div>Winner: ${Match5.getWinner().getName()}</div>
            </div>
        </c:if>
        <c:if test="${not empty Match6}">
            <div class="match">
                <div>Match ID: ${Match6.getId()}</div>
                <div>Player 1: ${Match6.getPlayer1().getName()}</div>
                <div>Player 2: ${Match6.getPlayer2().getName()}</div>
                <div>Winner: ${Match6.getWinner().getName()}</div>
            </div>
        </c:if>
        <c:if test="${not empty Match7}">
            <div class="match">
                <div>Match ID: ${Match7.getId()}</div>
                <div>Player 1: ${Match7.getPlayer1().getName()}</div>
                <div>Player 2: ${Match7.getPlayer2().getName()}</div>
                <div>Winner: ${Match7.getWinner().getName()}</div>
            </div>
        </c:if>
        <c:if test="${not empty Match8}">
            <div class="match">
                <div>Match ID: ${Match8.getId()}</div>
                <div>Player 1: ${Match8.getPlayer1().getName()}</div>
                <div>Player 2: ${Match8.getPlayer2().getName()}</div>
                <div>Winner: ${Match8.getWinner().getName()}</div>
            </div>
        </c:if>
        <c:if test="${not empty Match9}">
            <div class="match">
                <div>Match ID: ${Match9.getId()}</div>
                <div>Player 1: ${Match9.getPlayer1().getName()}</div>
                <div>Player 2: ${Match9.getPlayer2().getName()}</div>
                <div>Winner: ${Match9.getWinner().getName()}</div>
            </div>
        </c:if>
        <c:if test="${not empty Match10}">
            <div class="match">
                <div>Match ID: ${Match10.getId()}</div>
                <div>Player 1: ${Match10.getPlayer1().getName()}</div>
                <div>Player 2: ${Match10.getPlayer2().getName()}</div>
                <div>Winner: ${Match10.getWinner().getName()}</div>
            </div>
        </c:if>
    </div>

    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <form action="/hibernate_practice_war_exploded/matches" method="get" style="display: inline;">
                <input type="hidden" name="page" value="${currentPage - 1}">
                <button type="submit">Prev</button>
            </form>
        </c:if>
        <form action="/hibernate_practice_war_exploded/matches" method="get" style="display: inline;">
            <input type="hidden" name="page" value="${currentPage}">
            <button type="submit">${currentPage}</button>
        </form>
        <c:if test="${isNextPage}">
            <form action="/hibernate_practice_war_exploded/matches" method="get" style="display: inline;">
                <input type="hidden" name="page" value="${currentPage + 1}">
                <button type="submit">Next</button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>