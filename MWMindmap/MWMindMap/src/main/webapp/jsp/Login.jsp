<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/Login.css
" />
    <title>Login</title>
</head>

<body>
<div class="BoxBemvindo">
    <h1>Bem vindo</h1>
</div>
<form class="baseForm" action="validador" method="post" >
    <label class="formLabel">
        <h1>Nome:</h1>
        <input name="nomeUsuario" type="text" required class="formInput formTextInput">
    </label>
    <label class="formLabel">
        <h1>Senha:</h1>
        <input name="senha" type="password" required class="formInput formTextInput">
    </label>
    <div class="btn">
        <input type="submit" value="Entrar" class="submitButton">
        <br>ainda nao e cadastrado?</br>

    </div>
</form>
<a href="cadastrar" class="cad">Cadastrar</a>
</body>

</html>