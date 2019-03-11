<%@ page import="Model.Usuario" %>
<%@ page import="Model.Papel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.PapelEnum" %>
<%@ page import="com.mysql.cj.Session" %><%--
  Created by IntelliJ IDEA.
  User: junio
  Date: 11/26/2018
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%


    Usuario usu = (Usuario)session.getAttribute("login");
    request.getSession(true).setAttribute("ADM",false);
    if(usu!=null){
        if(usu.getPapeis().contains(PapelEnum.ADM)){
            System.out.println(usu.getNome());
            request.getSession(false).setAttribute("ADM",true);
        }
    }
%>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>My Way MindMap</title>
    <meta name="robots" content="index, follow">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="mindmaps is an HTML5 based mind mapping app. It lets you create neat looking mind maps in the browser." />
    <meta name="keywords" content="mind maps html5 mindmaps offline easy intuitive My way Myway easy facil prático" />
    <meta name="google" content="notranslate" />
    <link rel="icon" type="image/png" href="img/Mazul.png">
    <link rel="stylesheet" type="text/css" media="screen" href="css/Main.css" />


</head>

<body>
<div class="site">
    <header class="cabecalho" role="banner">
        <div class="largura-site ma">
            <div class="cabecalho_grid">
                <a href="home" class="cabecalho_logo"><img src="img/Mazul.png" height="100px" width="100px"></a>
                <nav class="menu grupo" role="navigation">
                        <span class="menu__trigger">
						<i class="fa fa-bars" aria-hidden="true"></i>
						<span class="menu__trigger-label">Menu</span>
                        </span>
                    <ul class="menu__ul" id="menu" style="display:none">
                        <li class="menu__item">
                            <a class="menu__link" href="home">
                                Home							</a>
                        </li>
                        <%if((boolean)session.getAttribute("ADM")){
                            %><li class="menu__item">
                        <a class="menu__link " href="post" >
                            Map							</a>
                    </li><%
                        };%>

                        <li class="menu__item">
                            <a class="menu__link " href="search">
                                Search							</a>
                        </li>
                        <li class="menu__item">
                            <a class="menu__link " href="perfil">
                                Perfil							</a>
                        </li>
                        <li class="menu__item">
                            <a class="menu__link " href="about">
                                About							</a>
                        </li>
                        <li class="menu__item">
                            <a class="menu__link " href="setings">
                                Setings							</a>
                        </li>
                        <%if(usu!=null){%>
                        <li class="menu__item">
                            <a class="menu__link" href="sair">
                                logout        </a>
                        </li><%};%>
                    </ul>
                </nav>
            </div>
        </div>
    </header>


    <div class="conteudo">
        <a class="link-topo" href="#" style="display: none;">
            <i class="fa fa-angle-up"></i> Topo </a>

        <div class="conteudomaster">
            <%
                if (request.getSession(true).getAttribute("pagina").equals("cadastrar")){
            %><jsp:include page="Cadastro.jsp"/><%
            }else
                if(session.getAttribute("login") == null){
                    if(request.getSession(true).getAttribute("pagina").equals("login2")) {%>
                        <jsp:include page="Login2vez.jsp" />
                    <%
                    }else {
                    %><jsp:include page="Login.jsp"/>
            <%}
        }else {
            System.out.println(request.getSession(true).getAttribute("pagina"));
            if(request.getSession(true).getAttribute("pagina").equals("home")){
        %>
            <jsp:include page="Home.jsp" />
            <%}

                if(request.getSession(true).getAttribute("pagina").equals("post")){
            %>
            <jsp:include page="CriarPost.jsp"/>
            <%}

                if(request.getSession(true).getAttribute("pagina").equals("search")){
            %>
            <jsp:include page="Post.jsp"/>
            <%}

                if(request.getSession(true).getAttribute("pagina").equals("perfil")){
            %>
            <jsp:include page="Perfil.jsp"/>
            <%}


                if (request.getSession(true).getAttribute("pagina").equals("postagem")){
            %><jsp:include page="PaginaPost.jsp"/><%
            }if(request.getSession(true).getAttribute("pagina").equals("editar")){
                    %><jsp:include page="editar.jsp"/><%
        }

            if(request.getSession(true).getAttribute("pagina").equals("sobre")){
        %>
            <jsp:include page="Sobre.jsp"/>
            <%}
                if(request.getSession(true).getAttribute("pagina").equals("setings")){
            %>
            <jsp:include page="Setings.jsp"/>
            <%}}%>
        </div>

    </div>


    <div class="footer">
        <div class="sep">
        </div>
        <div class="footerlogo">
            <a><img src="img/Mazul.png" height="120px" width="120px"></a>
            <div class="footertxt">
                <a>Nós da equipe</a><a class="Mywayf"> My way</a><a> agradecemos sua visita. </a>
            </div>
        </div>
    </div>
</div>
<%
    request.getSession(true).setAttribute("2cad",false);

%>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/main.js"></script>
</body>

</html>