<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="${locale}">
<head>
	<meta charset="utf-8">
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>${pageContext.request.contextPath}/</title>
	
	
	<!-- CSS reset -->
	<link href="<c:url value="/resources/css/normalize.css" />" rel="stylesheet">
	
	<!-- CSS bootstrap -->
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	
	
	<!-- CSS bootstrap-theme -->
	<link href="<c:url value="/resources/css/bootstrap-theme.css" />" rel="stylesheet">
	
	
	<!-- CSS propio -->
	<link href="<c:url value="/resources/css/styles.css?v=1.0" />" rel="stylesheet">
	
	
	
	
	<base href="${pageContext.request.contextPath}/"></base>
</head>
<body>