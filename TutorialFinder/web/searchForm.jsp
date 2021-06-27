<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form</title>
</head>
<body>
	<s:form action="tuts/getTutorial">
		<s:textfield label="Enter the language" key="input"/>
		<s:submit/>
	</s:form>
</body>
</html>