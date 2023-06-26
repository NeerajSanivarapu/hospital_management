<%@page import="dto.Doctor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approve doctor</title>
</head>
<body>
<% List<Doctor> list=(List<Doctor>)request.getAttribute("list"); %>
<table border="1">
<tr>
<th>ID</th>
<th>Name</th>
<th>Mobile</th>
<th>Age</th>
<th>Status</th>
<th>ChangeStatus</th>
</tr>
<%for(Doctor doctor:list){%>
<tr>
<th><%=doctor.getId()%></th>
<th><%=doctor.getName()%></th>
<th><%=doctor.getMobile()%></th>
<th><%=doctor.getAge()%></th>
<th><%=doctor.isStatus()%></th>

<th><a href="admindoctorstatus?id=<%=doctor.getId()%>"><button>Change Status</button></a></th>

</tr>
<%} %>
</table>
<br>
<a href="AdminHome.html"><button>Back</button></a>
</body>
</html>