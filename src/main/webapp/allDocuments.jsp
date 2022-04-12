<%@ page import="mediatek2022.Mediatheque" %>
<%@ page import="mediatek2022.Utilisateur" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Uzeir
  Date: 02/03/2022
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
        <title>Documents disponibles</title>
    </head>

    <body>
        <%
            Utilisateur u = (Utilisateur) session.getAttribute("user");
            if (u.isBibliothecaire()) { %>
                <%@include file="navbarAdmin.jsp" %>
            <% } else { %>
                <%@include file="navbarAbonne.jsp" %>
            <% } %>

        <h1 class="title is-2 has-text-centered">Documents disponibles dans la médiathèque</h1>

        <div class="container has-text-centered">
            <table class="table">
                <thead>
                    <th>Identifiant</th>
                    <th>Nom</th>
                    <th>Auteur(s)</th>
                    <th>Date de parution</th>
                    <th>Type</th>
                    <%if (!u.isBibliothecaire()) { %>
                    <th>Emprunt</th>
                    <% } %>
                </thead>

                <tbody>
                    <form method='get' action='${pageContext.request.contextPath}/EmpruntDocServlet' class='form'>
                        <%
                        List<mediatek2022.Document> docs = Mediatheque.getInstance().tousLesDocumentsDisponibles();
                        String ligne = "";
                        if (docs == null) {
                            ligne = "<h3 class='title is-4'>Aucun document disponible !</h3>";
                            out.println(ligne);
                        } else {
                            for (mediatek2022.Document d: docs) {
                                String[] infos = d.toString().split("/");
                                ligne = "<tr> <td>"
                                + infos[0] + "</td>" + "<td>"
                                + infos[1] + "</td>" + "<td>"
                                + infos[2] + "</td>" + "<td>"
                                + infos[3] + "</td>" + "<td>"
                                + infos[4] + "</td>";
                                if (!u.isBibliothecaire()) {
                                    ligne += "<td><input name='id' type='radio' value= '" + infos[0] + "'/></td>"
                                            + "<td><input class='button is-success' type='submit' value='Emprunter'/>" + "</tr>";
                                }
                                out.println(ligne);
                            }
                        }
                        %>
                    </form>
                </tbody>

            </table>
        </div>
    </body>
</html>
