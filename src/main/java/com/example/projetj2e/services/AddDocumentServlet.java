package com.example.projetj2e.services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddDocumentServlet", value = "/AddDocumentServlet")
public class AddDocumentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object[] args = new Object[10];

        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String date = request.getParameter("date");
        int type = Integer.parseInt(request.getParameter("type"));

        HttpSession session = request.getSession(true);
        Utilisateur u = (Utilisateur) session.getAttribute("user");

        args[0] = name;
        args[1] = author;
        args[2] = date;
        args[3] = u.data()[0]; //Ajout de l'ID bibliothécaire qui a ajouté le document

        synchronized (this){
            Mediatheque.getInstance().ajoutDocument(type, args);
        }

        String rep = "L'ajout de document s'est effectuée avec succès !";
        request.setAttribute("rep", rep);
        request.getRequestDispatcher("/accueilAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
