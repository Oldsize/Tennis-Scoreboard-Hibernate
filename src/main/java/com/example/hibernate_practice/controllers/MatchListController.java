package com.example.hibernate_practice.controllers;

import com.example.hibernate_practice.dao.MatchDAO;
import com.example.hibernate_practice.dao.PlayerDAO;
import com.example.hibernate_practice.model.Match;
import com.example.hibernate_practice.model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "match_list_controller", value = "/matches/*")
public class MatchListController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MatchDAO matchDAO = MatchDAO.getInstance();
        boolean isNextPage;
        PlayerDAO playerDAO = PlayerDAO.getInstance();
        String pageParameter = request.getParameter("page");
        String filter_by_player_name_parameter = request.getParameter("find_by_player_name");
        final String DEFAULT_PAGE = "1";
        List<Match> currentPageMatchesList = new ArrayList<>();
        List<Match> allMatchesList = new ArrayList<>();
        List<Match> parcipicantPlayerMatchesList = new ArrayList<>();
        if (pageParameter == null)
            pageParameter = DEFAULT_PAGE;
        final int PAGE_PARAMETER_INT = Integer.parseInt(pageParameter);
        Optional<Match> matchOpt = matchDAO.isNextPage(PAGE_PARAMETER_INT);
        if (matchOpt.isEmpty()) {
            isNextPage = false;
            request.setAttribute("isNextPage", isNextPage);
        } else {
            isNextPage = true;
            request.setAttribute("isNextPage", isNextPage);
        }

        if (!matchDAO.checkIfMatchesExists()) {
            request.setAttribute("errorCode", 502);
            request.setAttribute("errorMessage", "В базе данных сейчас нет матчей.");
            request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
        }

        if (filter_by_player_name_parameter != null) {
            allMatchesList = matchDAO.getAllMatches();
            int counter = allMatchesList.size();
            Optional<Player> playerOpt = playerDAO.findPlayerByName(filter_by_player_name_parameter);

            if (playerOpt.isEmpty()) {
                request.setAttribute("errorCode", 404);
                request.setAttribute("errorMessage", "Указаного вами игрока не существует.");
                request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
            } else {
                parcipicantPlayerMatchesList = matchDAO.findMatchesByParticipantPlayer(filter_by_player_name_parameter, PAGE_PARAMETER_INT);
                int pageCounter = Math.floorDiv(counter, 10);
                if (pageCounter == 0)
                    pageCounter = 1;
                if (PAGE_PARAMETER_INT > pageCounter) {
                    request.setAttribute("errorCode", 404);
                    request.setAttribute("errorMessage", "Указана несуществующая страница списка матчей.");
                    request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
                } else {
                    for (int i = 0; i < 10; i++) {
                        if (i < parcipicantPlayerMatchesList.size()) {
                            request.setAttribute("Match" + (i + 1), parcipicantPlayerMatchesList.get(i));
                        } else {
                            request.setAttribute("Match" + (i + 1), null);
                        }

                        request.setAttribute("currentPage", PAGE_PARAMETER_INT);
                        request.getRequestDispatcher("/WEB-INF/MatchesList.jsp").forward(request, response);

                    }
                }
            }
        } else {
            currentPageMatchesList = matchDAO.getMatchesByPage(PAGE_PARAMETER_INT);
            allMatchesList = matchDAO.getAllMatches();
            int counter = allMatchesList.size();
            int pageCounter = Math.floorDiv(counter, 10);
            if (pageCounter == 0)
                pageCounter = 1;
            if (PAGE_PARAMETER_INT > pageCounter) {
                request.setAttribute("errorCode", 404);
                request.setAttribute("errorMessage", "Указана несуществующая страница списка матчей.");
                request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
            } else {
                for (int i = 0; i < 10; i++) {
                    if (i < currentPageMatchesList.size()) {
                        request.setAttribute("Match" + (i + 1), currentPageMatchesList.get(i));
                    } else {
                        request.setAttribute("Match" + (i + 1), null);
                    }
                }
                request.setAttribute("currentPage", PAGE_PARAMETER_INT);
                request.getRequestDispatcher("/WEB-INF/MatchesList.jsp").forward(request, response);
            }
        }
    }
}