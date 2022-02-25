<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt">
<head>
    
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Css/formEstilo.css">
    <title>Cadastro Clientes</title>

</head>
<body>

    <main>

        <h1>Cadastro de Cliente</h1>
        
        <form action="formCliente" method="post">
        <input type="hidden" name="id" value="${cliente.id }">

            <div class="Caixas">

                <!-- required é para que o usuario seja informado de preencher todos os campos -->
                <label for="username">Nome:</label><br>
                <input type="text" name="nome" id="username" class="inputCinza" placeholder="Informe seu Nome" value="${cliente.nome }" required>


            </div>

            <div class="Caixas">

                <label for="endereco">Endereço:</label><br>
                <input type="text" name="endereco" id="endereco" class="inputCinza" placeholder="Informe seu Endereço" value="${cliente.endereco }" required>

            </div>

            <div class="Caixas">

                <label for="telefone">Telefone:</label><br>
                <!-- no pattern estou formatando o numero da forma que quero que o cliente digite -->
                <input type="tel" name="telefone" id="telefone" class="inputCinza" pattern="\(0-9){2}\)[9]{1}[0-9]{4}-[0-9]{4}" placeholder="(xx)9xxxx-xxxx" value="${cliente.telefone }" required>

            </div>

            <div class="Caixas">

                <label for="e-mail">E-mail:</label><br>
                <input type="email" name="email" id="e-mail" class="inputCinza" placeholder="Informe seu E-mail" value="${cliente.email } "required>

            </div>
            
            <div class="Caixas">
            
            	<label for="data-nasc">Data de Nascimento:</label>
            	<input type="date" name="dataNascimento" id="data-nasc" class="inputCinza" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${cliente.dataNascimento.time }"/>" required>
            
            </div>
            
            <div class="Caixas">
            
            	<label class="genero">Gêneros:</label></br>
            	<label>Masculino</label>
            	<input type="radio" name="genero" id="genero" value="M" required <c:if test="${cliente.genero.equals('M')}">checked</c:if> >
            	<label>Feminino</label>
            	<input type="radio" name="genero" id="genero" value="F" required <c:if test="${cliente.genero.equals('F')}">checked</c:if> >
            
            </div>

            <div class="Caixas">

                <label for="produtos">Produto(s) de Interesse:</label><br>
                <input type="text" name="produtoInteresse" id="produtos" class="inputCinza"  placeholder="Quais produto(s)"  value="${cliente.produtoInteresse }" required>

            </div>
            

            <!-- determinando um botão (sem a tag button) -->
            <input class="botao" type="submit" value="cadastrar">

        </form>


    </main>
    
</body>
</html>