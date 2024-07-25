package com.example.hibernate_practice.controllers;

import com.example.hibernate_practice.model.Match;
import com.example.hibernate_practice.service.NewMatchService;
import com.example.hibernate_practice.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "create_match_controller", value = "/new-match/*")
public class CreateMatchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/CreateMatchPage.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String player1 = req.getParameter("player1");
        String player2 = req.getParameter("player2");
        NewMatchService newMatchService = new NewMatchService();
        Match match = newMatchService.createMatch(player1, player2);
        String uuid = OngoingMatchesService.addMatch(match);
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/match-score/?uuid=" + uuid);
    }
}