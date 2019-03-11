<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Post"%>
<%@ page import="DAO.MySQL.PostDAOMySQL" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
</head>
<body>
<%
    ArrayList<Post> posts = new ArrayList<>();
    PostDAOMySQL postdao = new PostDAOMySQL();
    posts=postdao.buscarTodos();

%>
<div id="postagens">
        <%for (Post post : posts) {%>
    <div class="postagem">
        <form id=<%=post.getId()%>, action="mostrapostagem" method="post">
            <h3><%=post.getTitulo().toUpperCase()%>
            </h3>
            <label class="formLabel">
                <input name="postId" hidden value=<%=post.getId()%>>
            </label>
            <div class="butao">
                <input class="btn" value="VER" id="btnVerMais" type="submit">
            </div>
        </form>
    </div>
    <%}%>
</div>
<br>HOME<br>
</body>
</html>