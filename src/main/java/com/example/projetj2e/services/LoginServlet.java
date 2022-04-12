package com.example.projetj2e.services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String rep = "";

        // Interface Utilisateur
        Utilisateur u = Mediatheque.getInstance().getUser(login,password);
        if (u == null) {
            rep += "Aucun utilisateur n'est connu pour ces identifiants !";
            request.setAttribute("rep", rep);

            this.getServletContext().getRequestDispatcher("/connexion.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession(true); // Créer une session utilisateur
            session.setAttribute("nom", u.name());
            session.setAttribute("prenom", u.data()[1]);

            session.setAttribute("user", u);
            session.setAttribute("idU", u.data()[0]);
        }
        assert u != null;
        if (u.isBibliothecaire())
            this.getServletContext().getRequestDispatcher("/accueilAdmin.jsp").forward(request, response);
        else
            this.getServletContext().getRequestDispatcher("/accueilAbonne.jsp").forward(request, response);
    }
}
