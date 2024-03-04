<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/styles.css">
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
							<td><input type="submit" id="botao" name="botao"
							value="View Onibus"></td>
						<td><input type="submit" id="botao" name="botao"
							value="View Viagens"></td>
					</tr>
					<br/>
				</table>
			</form>
		</div>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2>
				<b><c:out value="${erro }" /></b>
			</H2>
		</c:if>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2>
				<b><c:out value="${erro }" /></b>
			</H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty tabela && tabela eq 'listar' }">
			<table class="table_round">
				<thead>
					<tr>
						<th>Codigo</th>
						<th>Placa Onibus</th>
						<th>Cod. Motorista</th>
						<th>Horario Saída</th>
						<th>Horario Chegada</th>
						<th>Local de Partida</th>
						<th>Local de Destino</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="v" items="${viagens}">
						<tr>
							<td><c:out value="${v.codigo}" /></td>
							<td><c:out value="${v.onibus}" /></td>
							<td><c:out value="${v.motorista}" /></td>
							<td><c:out value="${v.hora_saida}" /></td>
							<td><c:out value="${v.hora_chegada}" /></td>
							<td><c:out value="${v.partida}" /></td>
							<td><c:out value="${v.destino}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty tabela && tabela eq 'vonibus'}">
			<table class="table_round">
				<thead>
					<tr>
						<th>Cod. Viagem</th>
						<th>Nome Motorista</th>
						<th>Placa Onibus</th>
						<th>Marca Onibus</th>
						<th>Ano Onibus</th>
						<th>Desc. Onibus</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="v" items="${viagens}">
						<tr>
							<td><c:out value="${v.codigo}" /></td>
							<td><c:out value="${v.motorista.nome}" /></td>
							<td><c:out value="${v.onibus.placa}" /></td>
							<td><c:out value="${v.onibus.marca}" /></td>
							<td><c:out value="${v.onibus.ano}" /></td>
							<td><c:out value="${v.onibus.descricao}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty tabela && tabela eq 'vmotoristas'}">
			<table class="table_round">
				<thead>
					<tr>
						<th>Cod. Viagem</th>
						<th>Placa Onibus</th>
						<th>Hora Saida</th>
						<th>Hora Chegada</th>
						<th>Partida</th>
						<th>Destino</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="v" items="${viagens}">
						<tr>
							<td><c:out value="${v.codigo}" /></td>
							<td><c:out value="${v.onibus.placa}" /></td>
							<td><c:out value="${v.hora_saida}" /></td>
							<td><c:out value="${v.hora_chegada}" /></td>
							<td><c:out value="${v.partida}" /></td>
							<td><c:out value="${v.destino}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>