<%--
  Created by IntelliJ IDEA.
  User: Caillou
  Date: 04/03/2022
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Accueil</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>

    <body>
        <%@ include file="navbarAdmin.jsp" %>
        <h1 class="title is-2 has-text-centered">Bienvenue ${sessionScope.prenom} ${sessionScope.nom} !</h1>
        <p class="is-2 has-text-centered">Vous pouvez consulter ou ajouter des documents via Fonctions dans le menu.</p>

        <div class="has-text-centered container">
            <%
                String rep = request.getParameter("rep");
                if (rep != null) {
                    out.println(rep);
                }
            %>
        </div>
    </body>
</html>
