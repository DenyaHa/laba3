package com.github.denis11.lab23.servlets;

import com.github.denis11.lab23.controller.DatabaseController;
import com.github.denis11.lab23.modal.Record;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;

@WebServlet("/records")
public class LabServlet extends HttpServlet {
    private static DatabaseController dbController = new DatabaseController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            // пользователь залогинен, передать запрос нужному jsp

            getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
        }
        else
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        final HttpSession session = request.getSession();
        // если пользователь уже выполнил вход
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            String type = null;
            String company = "";
            String mail = "";
            String url = "";

            int id = 0;

            try{
                type = request.getParameter("type");
            }
            catch (Exception e) {
                return;
            }

            switch (type) {
                case "UPDATE" :
                    JsonArray responseObject = dbController.getAll();
                    try (PrintWriter writer = response.getWriter()) {
                        writer.println(responseObject);
                    }
                    break;
                case "ADD" :
                    try{
                        company  = request.getParameter("company");
                        mail  = request.getParameter("mail");
                        url  = request.getParameter("url");
                    }
                    catch (Exception e) {}

                    dbController.add(new Record(company, mail, url));
                    break;
                case "CHANGE" :
                    try{
                        id = Integer.parseInt(request.getParameter("id"));
                        company  = request.getParameter("company");
                        mail  = request.getParameter("mail");
                        url  = request.getParameter("url");
                    }
                    catch (Exception e) {}

                    dbController.change(new Record(company, mail, url, id));
                    break;
                case "DELETE" :
                    try{
                        id = Integer.parseInt(request.getParameter("id"));
                    }
                    catch (Exception e) {}

                    dbController.delete(id);
                    break;
                case "CLEAR" :
                    dbController.clear();
                    break;
            }
        }
        else {
            String login = "";
            String password = "";

            try {
                login = request.getParameter("login");
                password = request.getParameter("password");
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            if (dbController.isUserIsExist(login, password)) {
                request.getSession().setAttribute("login", login);
                request.getSession().setAttribute("password", password);
                getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
            }
            else {
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }
}
