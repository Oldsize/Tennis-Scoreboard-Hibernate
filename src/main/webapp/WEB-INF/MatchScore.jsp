<%@ page import="com.example.hibernate_practice.model.MatchScore" %>
<%@ page import="com.example.hibernate_practice.model.Match" %>
<%@ page import="com.example.hibernate_practice.model.EPlayer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Match Score</title>
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
            text-align: center;
            width: 600px;
            margin-top: 50px;
        }

        .scoreboard {
            width: 48%;
            border: 1px solid #004d40;
            border-radius: 5px;
            padding: 10px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            text-align: center;
            display: inline-block;
            margin-bottom: 20px;
        }

        .scoreboard .player {
            font-weight: bold;
            font-size: 20px;
            margin-bottom: 10px;
        }

        .scoreboard .details {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            margin-bottom: 10px;
            min-height: 80px;
        }

        .scoreboard .details .label {
            color: #004d40;
            margin: 5px 0;
        }

        .actions {
            margin-top: 10px;
        }

        .actions form {
            display: inline-block;
        }

        .actions button {
            padding: 10px 20px;
            border-radius: 5px;
            background-color: #004d40;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s ease;
        }

        .actions button:hover {
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
</head>
<body>
<%
    EPlayer firstPlayer = EPlayer.FIRST_PLAYER;
    EPlayer secondPlayer = EPlayer.SECOND_PLAYER;
    Match match = (Match) request.getAttribute("match");
    MatchScore matchScore = (MatchScore) request.getAttribute("matchScore");
    if (matchScore == null) {
        throw new IllegalStateException("matchScore attribute is missing");
    }

    String uuid = (String) request.getAttribute("uuid");
%>

<div class="nav-bar">
    <a href="${pageContext.request.contextPath}/">Welcome Page</a>
    <a href="${pageContext.request.contextPath}/matches">Matches List</a>
</div>

<div class="container">
    <div class="scoreboard">
        <div class="player">Player 1: <%= match.getPlayer1().getName() %></div>
        <div class="details">
            <div class="label">Points: <%= matchScore.getPlayerPoints(firstPlayer) %></div>
            <div class="label">Games: <%= matchScore.getPlayerGames(firstPlayer) %></div>
            <div class="label">Sets: <%= matchScore.getPlayerSets(firstPlayer) %></div>
            <div class="label">TieBreak Points: <%= matchScore.getPlayerTieBreakPoints(firstPlayer) %></div>
        </div>
        <div class="actions">
            <form action="${pageContext.request.contextPath}/match-score/" method="post">
                <input type="hidden" name="uuid" value="<%= uuid %>">
                <input type="hidden" name="player_winner_point" value="FIRST_PLAYER">
                <button type="submit">Win point</button>
            </form>
        </div>
    </div>
    <div class="scoreboard">
        <div class="player">Player 2: <%= match.getPlayer2().getName() %></div>
        <div class="details">
            <div class="label">Points: <%= matchScore.getPlayerPoints(secondPlayer) %></div>
            <div class="label">Games: <%= matchScore.getPlayerGames(secondPlayer) %></div>
            <div class="label">Sets: <%= matchScore.getPlayerSets(secondPlayer) %></div>
            <div class="label">TieBreak Points: <%= matchScore.getPlayerTieBreakPoints(secondPlayer) %></div>
        </div>
        <div class="actions">
            <form action="${pageContext.request.contextPath}/match-score" method="post">
                <input type="hidden" name="uuid" value="<%= uuid %>">
                <input type="hidden" name="player_winner_point" value="SECOND_PLAYER">
                <button type="submit">Win point</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>