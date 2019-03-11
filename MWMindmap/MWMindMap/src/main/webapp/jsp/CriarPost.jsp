<%--
  Created by IntelliJ IDEA.
  User: junio
  Date: 12/13/2018
  Time: 6:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Escrever Postagem</title>
</head>
<body>
<div class="container">
    <form class="baseForm" action="postar" method="post">
        <div class="dados">
            <label class="formLabel" id="titulo">
                <input name="titulo" placeholder="Título" type="text" required class="formInput formTextInput">
            </label>
            <label class="formLabel" id="texto">
                <input name="texto" placeholder="Texto" type="text" required class="formInput formTextInput">
            </label>
            <div class="butao">
                <input class="btn" value="POST" id="btnVerMais" type="submit">
            </div>
        </div>
    </form>
</div>
</body>
</html>