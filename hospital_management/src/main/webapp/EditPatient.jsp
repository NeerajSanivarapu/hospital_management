<%@page import="dto.Patient"%>
<%@page import="dao.MyDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%int id=Integer.parseInt(request.getParameter("id")); 
MyDao dao=new MyDao();
Patient patient=dao.fetchPatient(id);
if(patient==null)
{
	response.getWriter().print("<h1>Enter Valid id</h1>");
	request.getRequestDispatcher("Enterpatientid.html").include(request, response);
}
else{
%>

<form action="updatepatient" method="post">
Id:<input type="text" name="id" value="<%=patient.getId()%>" readonly="readonly">
<br>
Name:<input type="text" name="name" value="<%=patient.getName()%>">
<br>
Mobile:<input type="text" name="mobile" value="<%=patient.getMobile()%>" readonly="readonly">
<br>
Date of Birth:<input type="date" name="dob" value="<%=patient.getDob()%>">
<br>
<button>Update</button><button type="reset">Cancel</button>
</form>
<br>
<a href="Enterpatientid.html"><button>Back</button></a>
<%} %>
</body>
</html>