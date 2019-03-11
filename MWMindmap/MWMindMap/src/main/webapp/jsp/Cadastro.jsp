<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro</title>
</head>
<body>

<div class="container">
    <form class="baseForm" action="cadastro" method="post">
        <div class="dados">
            <label class="formLabel">
                <input name="nomeUsuario" placeholder="Nome" type="text" required class="formInput formTextInput">
            </label>

            <label class="formLabel">
                <input name="apelidoUsuario" placeholder="Login" type="text" required class="formInput formTextInput">
            </label>

            <label class="formLabel">
                <input name="emailUsuario" placeholder="Email" type=email required class="formInput formTextInput">
            </label>
            <label class="formLabel">
                <input name="senha" placeholder="Senha" type="password" required class="formInput formTextInput">
            </label>
            <%
                if((Boolean) request.getSession(true).getAttribute("2cad")  ) {
                %>
                    <h1>AKI</h1>
                <%}
            %>
            <div class="butao">
                <input class="btn" value="CAD" id="btnVerMais" type="submit">
            </div>
        </div>
    </form>
</div>
</body>
</html>