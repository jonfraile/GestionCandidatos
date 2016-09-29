<%@include file="../includes/header.jsp" %>

<div class="container">
	
	<c:set var="titulo" value="Modificar candidato" scope="page"/>
	<c:set var="boton" value="Modificar" scope="page"/>
	
	<c:if test="${isNew}">
		<c:set var="titulo" value="Crear Candidato" scope="page"/>
		<c:set var="boton" value="Crear" scope="page"/>
	</c:if>
	
	<h1>${titulo}</h1>
	<br>
	
		<form:form action="candidato/save" method="post" commandName="candidato" class="form-inline">
		
				<c:if test="${!isNew}">
					<div class="form-group">
						<form:label path="id">ID</form:label>
						<form:input path="id" readonly="true" class="form-control"/>
						<form:errors path="id" cssClass="error"/>
					</div>
					<div class="form-group">
						<form:label path="fecha_alta">Fecha de alta</form:label>
						<form:input path="fecha_alta" class="form-control"/>
					</div>            				
				</c:if>
				<div class="form-group">
					<form:label path="dni">DNI</form:label>
					<form:input path="dni" class="form-control"/>
					<form:errors path="dni" cssClass="error"/>
				</div>
				<div class="form-group">
					<form:label path="nombre">Nombre</form:label>
					<form:input path="nombre" class="form-control"/>
					<form:errors path="nombre" cssClass="error"/>
				</div>
		
			
				
		</form:form>
	<br>
	<input type="submit" value="${boton}">
	<br><br>
	<a href="candidato">Atras</a>
	
	<c:if test="${not empty msg}">
		<div class="alert alert-success" role="alert" dismissible>
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			${msg}
		</div>
	</c:if>
	
	
	
	
</div>


<%@include file="../includes/footer.jsp" %>