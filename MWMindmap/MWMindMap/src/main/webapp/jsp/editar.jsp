<%@ page import="Model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Usuario usuarioalterar = (Usuario) session.getAttribute("usuarioalterar");%>
<html>
<head>
    <title>Editar</title>
</head>
<body>
<div id="box" class="container">
    <form  action="atualizar" method="post">
        <input hidden name="id" value=<%=usuarioalterar.getId()%>>
        <input name="nome" placeholder="Nome" type="text" value=<%=usuarioalterar.getNome()%>>
        <input name="login" placeholder="Login" type="text" value=<%=usuarioalterar.getLogin()%>>
        <input name="senha" placeholder="Senha" type="text" value=<%=usuarioalterar.getSenha()%>>
        <input name="email" placeholder="Email" type="email" value=<%=usuarioalterar.getEmail()%>>
        <div class="butao" id="btnBemNice">
            <input class="btn" value="IR" id="btnVerMais" type="submit">
        </div>
    </form>
    <form id="formin" action="deletar" method="post">
        <input hidden name="id" value=<%=usuarioalterar.getId()%>>
        <div class="butao" id="btnBemNice2">
            <input class="btn" value="DEL" id="btnVerMais2" type="submit">
        </div>
    </form>
</div>

</body>
</html>