package com.example.projetj2e.services;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EmpruntDocServlet", value = "/EmpruntDocServlet")
public class EmpruntDocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        int num = Integer.parseInt(request.getParameter("id"));
        Utilisateur u = (Utilisateur) session.getAttribute("user");

        synchronized (this) {
            Document d = Mediatheque.getInstance().getDocument(num);
            try {
                d.emprunt(u);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/accueilAbonne.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
