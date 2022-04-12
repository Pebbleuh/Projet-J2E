<%--
  Created by IntelliJ IDEA.
  User: Caillou
  Date: 03/03/2022
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>

    <nav class="navbar">
        <div class="navbar-brand">
        </div>
        <div class="navbar-menu">
            <div class="navbar-start">
                <a class="navbar-item" href="accueilAbonne.jsp">Accueil</a>
            </div>

            <div class="navbar-end">
                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-link">
                        Fonctions
                    </a>
                    <div class="navbar-dropdown">
                        <a href="allDocuments.jsp" class="navbar-item">Emprunter un document</a>
                        <a href="returnDocument.jsp" class="navbar-item">Retourner un document</a>
                    </div>
                </div>

                <button class="button is-primary"><a class="navbar-item" href="accueil.jsp"
                                                     METHOD="post" action="${pageContext.request.contextPath}/Deconnexion">Se deconnecter</a></button>
            </div>
        </div>
    </nav>

</html>