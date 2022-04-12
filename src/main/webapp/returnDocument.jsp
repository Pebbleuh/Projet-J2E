<%@ page import="mediatek2022.Mediatheque" %>
<%@ page import="mediatek2022.Document" %>
<%@ page import="java.util.List" %>
<%@ page import="mediatek2022.Utilisateur" %>
<%--
  Created by IntelliJ IDEA.
  User: Uzeir
  Date: 02/03/2022
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Retourner un document</title>
    </head>

    <body>
        <%@ include file="navbarAbonne.jsp" %>
        <h1 class="title is-2 has-text-centered">Mes documents empruntés</h1>

        <div class="container has-text-centered">
            <table class="table">
                <thead>
                <th>Identifiant</th>
                <th>Nom</th>
                <th>Auteur(s)</th>
                <th>Date de parution</th>
                <th>Type</th>
                <th>Retour</th>
                </thead>

                <tbody>
                <form method='get' action='${pageContext.request.contextPath}/ReturnDocServlet' class='form'>
                    <%
                        //int id = Integer.parseInt(request.getParameter("idU"));
                        Utilisateur u = (Utilisateur) session.getAttribute("user");
                        List<Document> docs = (List<Document>) u.data()[5];
                        String ligne = "";
                        if (docs == null) {
                            ligne = "<h3 class='title is-4'>Aucun document emprunté !</h3>";
                            out.println(ligne);
                        } else {
                            for (mediatek2022.Document d: docs) {
                                String[] infos = d.toString().split("/");
                                ligne = "<tr> <td>"
                                        + infos[0] + "</td>" + "<td>"
                                        + infos[1] + "</td>" + "<td>"
                                        + infos[2] + "</td>" + "<td>"
                                        + infos[3] + "</td>" + "<td>"
                                        + infos[4] + "</td>"
                                        + "<td><input name='id' type='radio' value= '"+ infos[0] +"'/></td>"
                                        + "<td><input class='button is-danger' type='submit' value='Retourner'/>" + "</tr>";
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
