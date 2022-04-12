package com.example.projetj2e.services;

import mediatek2022.Document;
import mediatek2022.Mediatheque;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ReturnDocServlet", value = "/ReturnDocServlet")
public class ReturnDocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        int num = Integer.parseInt(request.getParameter("id"));
        synchronized (this) {
            Document d = Mediatheque.getInstance().getDocument(num);
            try {
                d.retour();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("/accueilAbonne.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
