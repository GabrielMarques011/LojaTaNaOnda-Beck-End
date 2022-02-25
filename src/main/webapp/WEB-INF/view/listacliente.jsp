<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="a" %><%// O PREFIXO É NECESSARIO E QUANDO FOR CHAMAR UMA TAG TEM QUE SER, c: nome da tag %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Css/estiloLista.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quantico:ital@1&display=swap" rel="stylesheet">
    <title>Lista de Clientes</title>
</head>
<body>

    <main>

    <table class="cliente">
	    <h1>Lista de Clientes</h1>
	        
	        <tr>
	            <th>Id</th>
	            <th>Nome</th>
	            <th>Idade</th>
	            <th>Data de Nascimento</th>
	            <th>Gênero</th>
	            <th>Endereço</th>
	            <th>Telefone</th>
	            <th>E-mail</th>
	            <th>Produto(s) de Interesse</th>
	            <th >Excluir</th>
	            <th>Atualizar</th>
	        </tr>
	   
		 
	   	<a:forEach items="${cliente }" var="c">
	    
	    <tr>
	    <td>${c.id }</td>
	    <td>${c.nome }</td>
	    <td>${c.idade }</td>
	    <td><fmt:formatDate value="${c.dataNascimento.time}" pattern="dd/MM/yyyy"/></td>
	    <td>${c.genero}</td>
	    <td>${c.endereco}</td>
	    <td>${c.telefone }</td>
	    <td>${c.email }</td>
	    <td>${c.produtoInteresse }</td>
	    <td><a href="excluirCliente?idCliente=${c.id }" onclick="return confirm('Confirmar a exclusão do cliente ${c.nome}?')" class="botao">Excluir</a></td>
	    <td><a href="alterarCliente?idCliente=${c.id }" class="botao">Atualizar</a></td>
	    </tr>
	    </a:forEach>
	
	<!--Iniciando tabela Genero-->
    <table class="faixa">
        <h1>Gênero</h1>
        <tr>
            <th>Masculino</th>
            <th>Feminino</th>
        </tr>
        
     
	    
	    <tr>
	 		<td>${masculino}</td>
	 		<td>${feminino}</td>
	    </tr>
	    
	
       
    </table>

    <!--Iniciando tabela Faixa Etária-->
    <table class="faixa">
        <h1>Faixa Etária</h1>
        <tr>
            <th>Jovem</th>
            <th>Adulto</th>
            <th>Idoso</th>
        </tr>
        
        <tr>
	 		<td>${jovem}</td>
	 		<td>${adulto}</td>
	 		<td>${idoso}</td>
	    </tr>
	    
    </table>

    <!--Iniciando tabela por dia da semana-->
    <table>
        <h1>Clientes por dia da semana</h1>
        <tr>
            <th>Segunda</th>
            <th>Terça</th>
            <th>Quarta</th>
            <th>Quinta</th>
            <th>Sexta</th>
            <th>Sabado</th>
            <th>Domingo</th>
        </tr>
        <tr>
	 		<td>${segunda}</td>
	 		<td>${terca}</td>
	 		<td>${quarta}</td>
	 		<td>${quinta}</td>
	 		<td>${sexta}</td>
	 		<td>${sabado}</td>
	 		<td>${domingo}</td>
	    </tr>
    </table>

    <!--Iniciando tabela periodo do dia-->
    <table>
        <h1>Clientes por periodo do dia</h1>
        <tr>
            <th>Manhã</th>
            <th>Tarde</th>
            <th>Noite</th>
        </tr>
        
          <tr>
	 		<td>${manha}</td>
	 		<td>${tarde}</td>
	 		<td>${noite}</td>
	    </tr>
    </table>

    </main>
    
</body>
</html>