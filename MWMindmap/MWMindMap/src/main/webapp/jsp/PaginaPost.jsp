<%@ page import="javafx.geometry.Pos" %>
<%@ page import="Model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Comentario" %>
<%@ page import="DAO.MySQL.PostDAOMySQL" %>
<%@ page import="DAO.MySQL.ComentarioDAOMySQL" %>
<%@ page import="DAO.MySQL.UsuarioDAOMySQL" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Post postagem = (Post) session.getAttribute("postagem");
    ComentarioDAOMySQL comentarioDAO = new ComentarioDAOMySQL();
    List<Comentario> comentarios = comentarioDAO.acharComentariosPorPost(postagem.getId());
    session.setAttribute("postagem", postagem);
    Usuario usu = (Usuario) session.getAttribute("login");
    UsuarioDAOMySQL usudao= new UsuarioDAOMySQL();
    ComentarioDAOMySQL coment = new ComentarioDAOMySQL();
%>


<html>
<head>
    <title>Postagem</title>
</head>
<body>

<div class="container">

    <div id="post">
        <h1 id="titulo"><%=postagem.getTitulo().toUpperCase()%>
        </h1>
        <p id="texto"><%=postagem.getTexto()%>
        </p>
    </div>

    <div id="comentar">
        <h1>Comentários:</h1>
        <form id="tmj" action="comentar" method="post">

            <label class="formLabel">
                <input name="nomeUsuario" disabled value=<%=usu.getNome()%>, type="text" required>
            </label>

            <label class="formLabel">
                <input name="comentario" placeholder="Comentário" type="text" required>
            </label>

            <label class="formLabel">
                <input name="idUsuario" hidden value=<%=usu.getId()%>>
            </label>

            <label class="formLabel">
                <input name="idPostagem" hidden value=<%=postagem.getId()%>>
            </label>
            <div class="butao">
                <input class="btn" value="COM" id="btnVerMais2" type="submit">
            </div>

        </form>


        <%
            if (!comentarios.isEmpty()) {
                for (Comentario comentario : comentarios) {
        %>
        <div id="comentario">
            <div id="autor">
                <h4><%=usudao.buscarUsuarioPorId(comentario.getIdusuario()).getNome() + " • " %>
                </h4>
                <div id="conteudo">
                    <h3><%=comentario.getTexto()%>
                    </h3>
                </div>
            </div>

            <%if(session.getAttribute("ADM").equals(true)) {
                System.out.println("admin logado");%>
            <form id="top" action="delcom" method="post">
                <input hidden name="comid" value=<%=comentario.getId()%>>
                <div class="butao">
                    <input class="btn" value="DEL" id="btnVerMais" type="submit">
                </div>
            </form>
            <%}%>
        </div>

        <%}%>
        <%} else {
            %>
        <h2>Lista de comentários vazia!</h2>
        <%}%>
    </div>

</div>

</body>
</html>
