<%@page import="dto.Staff"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approve Staff</title>
</head>
<body>
<% List<Staff> list=(List<Staff>)request.getAttribute("list"); %>
<table border="1">
<tr>
<th>ID</th>
<th>Name</th>
<th>Mobile</th>
<th>Age</th>
<th>Status</th>
<th>ChangeStatus</th>
</tr>
<%for(Staff staff:list){%>
<tr>
<th><%=staff.getId()%></th>
<th><%=staff.getName()%></th>
<th><%=staff.getMobile()%></th>
<th><%=staff.getAge()%></th>
<th><%=staff.isStatus()%></th>

<th><a href="adminstaffstatus?id=<%=staff.getId()%>"><button>Change Status</button></a></th>

</tr>
<%} %>
</table>
<br>
<a href="AdminHome.html"><button>Back</button></a>
</body>
</html>