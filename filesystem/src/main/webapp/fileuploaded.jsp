<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../styles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Download</title>
</head>
<body bgcolor="#e74c3c">
<h3>Please click the button below to download the file:</h3>
	<p><a href="/filesystem/Download?FILE_NAME=<%=request.getPathInfo().replace("/", "")%>" class ="download">DOWNLOAD NOW</a></p>
</body>
</html>