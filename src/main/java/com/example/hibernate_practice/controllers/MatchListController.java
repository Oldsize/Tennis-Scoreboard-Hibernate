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
        String filter_by_player_name_parameter = request.getParameter("filter_by_player_name");
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
                }
            }
            // TODO что еще надо реализовать
            // TODO реквест сет атрибуты и редирект или форвард.
            request.setAttribute("Match1", parcipicantPlayerMatchesList.get(0));
            request.setAttribute("Match2", parcipicantPlayerMatchesList.get(1));
            request.setAttribute("Match3", parcipicantPlayerMatchesList.get(2));
            request.setAttribute("Match4", parcipicantPlayerMatchesList.get(3));
            request.setAttribute("Match5", parcipicantPlayerMatchesList.get(4));
            request.setAttribute("Match6", parcipicantPlayerMatchesList.get(5));
            request.setAttribute("Match7", parcipicantPlayerMatchesList.get(6));
            request.setAttribute("Match8", parcipicantPlayerMatchesList.get(7));
            request.setAttribute("Match9", parcipicantPlayerMatchesList.get(8));
            request.setAttribute("Match10", parcipicantPlayerMatchesList.get(9));
            request.setAttribute("currentPage", PAGE_PARAMETER_INT);
            request.getRequestDispatcher("/WEB-INF/MatchesList.jsp").forward(request, response);
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
            }
            // TODO реквест сет атрибуты и редирект или форвард.
            request.setAttribute("Match1", currentPageMatchesList.get(0));
            request.setAttribute("Match2", currentPageMatchesList.get(1));
            request.setAttribute("Match3", currentPageMatchesList.get(2));
            request.setAttribute("Match4", currentPageMatchesList.get(3));
            request.setAttribute("Match5", currentPageMatchesList.get(4));
            request.setAttribute("Match6", currentPageMatchesList.get(5));
            request.setAttribute("Match7", currentPageMatchesList.get(6));
            request.setAttribute("Match8", currentPageMatchesList.get(7));
            request.setAttribute("Match9", currentPageMatchesList.get(8));
            request.setAttribute("Match10", currentPageMatchesList.get(9));
            request.setAttribute("currentPage", PAGE_PARAMETER_INT);
            request.getRequestDispatcher("/WEB-INF/MatchesList.jsp").forward(request, response);

//      TODO  Match1: объект типа Match
//            Match2: объект типа Match
//            Match3: объект типа Match
//            Match4: объект типа Match
//            Match5: объект типа Match
//            Match6: объект типа Match
//            Match7: объект типа Match
//            Match8: объект типа Match
//            Match9: объект типа Match
//            Match10: объект типа Match
//            currentPage: целочисленное значение, представляющее текущую страницу
//            isNextPage: логическое значение, указывающее, есть ли следующая страница

        }

        // TODO конкретные действия:
        // TODO гетнуть все в коллекцию, переменная int Counter будет служить счетчиком,
        // TODO Counter = MatchesList.length();
        // TODO проверка на то есть ли хоть один матч ваще


        // TODO если параметр page пустой то устанавливается default page тоесть 1
        // TODO filter_by_player_name тоже самое, если ищется по имени то выдаются страницы
        // TODO изначально должна быть логика того сколько страниц чтоб снизу рендерить одну или больш
        // TODO на одной page 10 матчей, значит должна быть переменная счетчик которая делится на 10 и выходит целое колво страниц
        // TODO округляется в меньшую сторону
    }
}