<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form method="POST" action="page2">
		<input type="hidden" name="getInfo" value="1" />
	   <table>
		    <tr>
		        <td><form:label path="name">Name</form:label></td>
		        <td><input type="text" value="${name}" name="name" /></td>
		    </tr>
		    <tr>
		        <td><form:label path="age">Age</form:label></td>
		        <td><form:input path="age" /></td>
		    </tr>
		    <tr>
		        <td><form:label path="cid">Cid</form:label></td>
		        <td><form:input path="cid" /></td>
		    </tr>
		    <tr>
		        <td><form:label path="color">Color</form:label></td>
		        <td><form:input path="color" /></td>
		    </tr>
		    <tr>
		        <td colspan="2">
		            <input type="submit" value="Submit"/>
		        </td>
		    </tr>
		</table>  
	</form:form>
</body>
</html>