<%--
  Created by IntelliJ IDEA.
  User: Uzeir
  Date: 02/03/2022
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
        <title>Ajouter un document</title>
    </head>

    <body>
        <%@ include file="navbarAdmin.jsp" %>
        <div class="container">
            <h1 class="title is-2 has-text-centered">Ajouter un document</h1>
        </div>

        <div class="container columns">
            <div class="column is-three-fifths is-offset-one-fifth">

                <form class="box" method="get" action="${pageContext.request.contextPath}/AddDocumentServlet">
                    <div class="field">
                        <label class="label" for="name">Nom</label>
                        <div class="control">
                            <input id="name" name="name" class="input" type="text" required>
                        </div>
                    </div>

                    <div class="field">
                        <label class="label" for="author">Auteur</label>
                        <div class="control">
                            <input id="author" name="author" class="input" type="text" required>
                        </div>
                    </div>

                    <div class="field">
                        <label class="label" for="date">Date de parution</label>
                        <div class="control">
                            <input id="date" name="date" class="input" type="date" required>
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">Type de document</label>
                        <div class="control">
                            <div class="select">
                                <select name="type">
                                    <option value="1">Livre</option>
                                    <option value="2">CD</option>
                                    <option value="3">DVD</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="control">
                        <button type="submit" class="button is-primary">Ajouter</button>
                    </div>
                </form>

            </div>


            <div id="result" class="container">
                <h2 class="has-text-centered">
                </h2>
            </div>

        </div>

     </body>
</html>
