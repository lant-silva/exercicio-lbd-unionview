<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CRUD Viagem</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div>
		<div align="center" class="container">
			<form action="viagem" method="post">
				<p class="title">
					<b>Viagem</b>
				<p />
				<table>
					<tr>
						<td colspan="3"><input class="input_data" type="number"
							min="101" step="1" id="codigo" name="codigo" placeholder="Codigo"
							value='<c:out value="${viagem.codigo}"></c:out>'>
						<td />
						<td><input type="submit" id="botao" name="botao"
							value="Buscar">
						<td />
					<tr />
					<tr>
						<td colspan="4"><input class="input_data" type="text"
							maxlength="7" id="onibus" name="onibus" placeholder="Placa do Onibus"
							value='<c:out value="${viagem.onibus}"></c:out>'></td>
					</tr>
					<tr>
						<td colspan="3"><input class="input_data" type="number"
							min="12341" step="1" id="motorista" name="motorista" placeholder="Cod. Motorista"
							value='<c:out value="${viagem.motorista}"></c:out>'>
						<td />
					</tr>
					<tr>
						<td colspan="3"><input class="input_data" type="number"
							min="0" step="1" id="hora_saida" name="hora_saida" placeholder="Horário de Saída"
							value='<c:out value="${viagem.hora_saida}"></c:out>'>
						<td />
					</tr>
					<tr>
						<td colspan="3"><input class="input_data" type="number"
							min="0" step="1" id="hora_chegada" name="hora_chegada" placeholder="Horário de Chegada"
							value='<c:out value="${viagem.hora_chegada}"></c:out>'>
						<td />
					</tr>
					<tr>
						<td colspan="4"><input class="input_data" type="text"
							maxlength="40" id="partida" name="partida" placeholder="Local de Partida"
							value='<c:out value="${viagem.partida}"></c:out>'></td>
					</tr>
					<tr>
						<td colspan="4"><input class="input_data" type="text"
							maxlength="40" id="destino" name="destino" placeholder="Local de Destino"
							value='<c:out value="${viagem.destino}"></c:out>'></td>
					</tr>
					<tr>
						<td><input type="submit" id="botao" name="botao"
							value="Cadastrar"></td>
						<td><input type="submit" id="botao" name="botao"
							value="Alterar"></td>
						<td><input type="submit" id="botao" name="botao"
							value="Excluir"></td>
						<td><input type="submit" id="botao" name="botao"
							value="Listar"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>