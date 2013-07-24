<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<c:forEach var="i" items="${appartments}">
   <c:out value="${i.size}" />
	</c:forEach>
</body>
</html>
