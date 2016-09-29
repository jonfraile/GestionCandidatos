<%@include file="../includes/header.jsp" %>

<div class="container">
	<h1>Lista de Candidatos</h1>
	<p>${fecha}</p>
		
		
		
		<form:form action="candidato/buscar/" method="get" commandName="searchItemForm">
			<form:input path="dni"/>
			<input type="submit" value="Buscar">	
		</form:form>
		
		<br><br>
		
		<p><a class="btn btn-primary btn-xs" href="candidato/nuevo" role="button">Crear</a></p>
		
		<br><br>
	
	<c:if test="${existeMsgEliminar}">
		<div class="alert alert-success" role="alert" dismissible>
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			${msgEliminado}
		</div>
	</c:if>
	
	
	<c:if test="${existeMsgCandidato}">
		<div class="alert alert-success" role="alert" dismissible>
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			${msgBusqueda}
		</div>
		<a href="">Atras</a>
	</c:if>
	
	 
	 <table class="table table-bordered">
	 	<tr>
	 		<td>DNI</td>
	 		<td>Nombre</td>
	 		<td>Fecha Alta</td>
	 		<td>Fecha Modificacion</td>
	 		<td>Fecha Eliminacion</td>
	 		<td>Eliminar</td>
	 	</tr>
	    <c:forEach items="${candidatos}" var="candidato">
	    	<tr>
	    		<td>
		    	<a href="candidato/mostrar/${candidato.id}">
		      		<c:out value="${candidato.dni}"/>
		      	</a>
		      	</td> 
		      	<td>
		      		<c:out value="${candidato.nombre}"/>
		      	</td>
		      	<td>
		      		<c:out value="${candidato.fecha_alta}"/>
		      	</td>
		      	<td>
		      		<c:out value="${candidato.fecha_modificacion}"/>
		      	</td>
		      	<td>
		      		<c:out value="${candidato.fecha_eliminacion}"/>
		      	</td>
		      	<td>
			      	<a href="candidato/eliminar/${candidato.id}">
			      		[X]
			      	</a>
		      	</td>      
	      	</tr>      
	    </c:forEach>
    </table>

<div class="container">
<%@include file="../includes/footer.jsp" %>