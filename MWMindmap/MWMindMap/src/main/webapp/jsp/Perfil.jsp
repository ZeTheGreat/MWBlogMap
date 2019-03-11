<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Usuario" %>
<%@ page import="DAO.MySQL.UsuarioDAOMySQL" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Model.Papel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    UsuarioDAOMySQL usudao= null;
    List<Usuario> usuarios =null;
    try {
        usudao = new UsuarioDAOMySQL();
        usuarios = usudao.listarUsuarios();
        for(Usuario usu : usuarios){
            System.out.println(usu.getNome());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }%>

<html>
<head>
    <title>Gerenciar</title>
</head>
<body>
<div id="usuarios">
    <%
        Usuario usu1=(Usuario)session.getAttribute("login");
    if(!(boolean)session.getAttribute("ADM")){ %>
        <div>
    <p>Olá, <%= usu1.getNome() %>
    </p>
    <p>Seu e-mail cadastrado é: <%= usu1.getEmail() %>
    </p>
    <p>Sua senha cadastrada é: <%= usu1.getSenha() %>
    </p>
</div>
   <%}else {
    %>
    <%for (Usuario usuario1 : usuarios) {%>
    <div id="usuario">
        <form action="editar">
            <input name="usuarioId" hidden value=<%=usuario1.getId()%>>
            <h3><%=usuario1.getNome().toUpperCase()%></h3>
            <div class="butao">
                <input class="btn" value="EDIT" id="btnVerMais" type="submit">
            </div>
        </form>
    </div>
    <%}}%>
</div>
</body>
</html>