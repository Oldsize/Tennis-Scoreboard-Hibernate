<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Match Result</title>
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
            background-color: #004d40;
            color: white;
            padding: 10px 0;
            width: 100%;
        }
        .nav-links a {
            color: white;
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
            margin-top: 50px;
        }
        .player-info {
            margin: 20px 0;
        }
        .player-info div {
            font-size: 18px;
            margin: 10px 0;
        }
        .winner {
            font-size: 24px;
            font-weight: bold;
            margin: 20px 0;
            color: #d32f2f;
        }
        .button-container {
            margin-top: 20px;
        }
        .button-container a {
            display: inline-block;
            padding: 10px 20px;
            border-radius: 5px;
            background-color: #004d40;
            color: white;
            text-decoration: none;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .button-container a:hover {
            background-color: #00332e;
        }
    </style>
</head>
<body>
<div class="nav-links">
    <a href="/hibernate_practice_war_exploded/">Welcome page</a>
    <a href="/hibernate_practice_war_exploded/new-match">New match</a>
    <a href="/hibernate_practice_war_exploded/matches">List matches</a>
</div>
<div class="container">
    <div class="player-info">
        <div>Player 1: ${player1Name}</div>
        <div>Player 2: ${player2Name}</div>
    </div>
    <div class="winner">Winner: ${winnerName}</div>
</div>
</body>
</html>