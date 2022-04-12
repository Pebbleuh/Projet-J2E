<%--
  Created by IntelliJ IDEA.
  User: Uzeir
  Date: 23/02/2022
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
        <title>Connexion de l'utilisateur</title>
    </head>

    <body>
        <h1 class="title is-2 has-text-centered">Connexion de l'utilisateur</h1>

        <div id="content">
            <div class="columns">
                <div class="column is-three-fifths is-offset-one-fifth">
                    <form class="box" METHOD="post" action="${pageContext.request.contextPath}/LoginServlet">
                        <div class="container block">
                            <label class="label" for="login">Login :</label>
                            <input class="input" type="text" id="login" name="login" required/>
                            <label class="label" for="password">Mot de passe :</label>
                            <input class="input" type="password" id="password" name="password" required/>
                        </div>
                        <button class="button is-success has-text-centered" type="submit">Se connecter</button>
                    </form>
                </div>
            </div>
        </div>

        <div id="reponse" class="has-text-centered" >
            <h2>
                <%
                    String rep = (String) request.getAttribute("rep");
                    if (rep != null){
                        out.println(rep);
                    }
                %>
            </h2>
        </div>
    </body>
</html>
