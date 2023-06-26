<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="dto.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="dto.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		if (doctor == null) {
			response.getWriter().print("<h1>Invalid Session </h1>");
			request.getRequestDispatcher("Login.html").include(request, response);
		} else {
			List<Appointment> list = doctor.getAppointments();
			if (list.isEmpty()) {
				response.getWriter().print("<h1>No Appointments Yet</h1>");
				request.getRequestDispatcher("DoctorHome.html").include(request, response);
			} else {
	%>
	<table border="1">
		<tr>
			<th>Appointment Id</th>
			<th>Patient Id</th>
			<th>Patient Name</th>
			<th>Patient Picture</th>
			<th>Patient Problem</th>
			<th>Check</th>
		</tr>
		<%
			for (Appointment appointment : list) {
		%>
		<%
			if (appointment.getTime() == null) {
		%>
		<tr>
			<th><%=appointment.getId()%></th>
			<th><%=appointment.getPatient().getId()%></th>
			<th><%=appointment.getPatient().getName()%></th>
			<th>
				<%
					String base64 = Base64.encodeBase64String(appointment.getPatient().getPicture());
				%> <img height="100px" width="100px" alt="unknown"
				src="data:image/jpeg;base64,<%=base64%>">
			</th>
			<th><%=appointment.getProblem()%></th>
			<th><a href="checkappointment?id=<%=appointment.getId()%>"><button>Check</button></a></th>
		</tr>
		<%
			}
		%>
		<%
			}
		%>
	</table>
	<br>
	<a href="DoctorHome.html"><button>Back</button></a>
	<%
		}
	%>
	<%
		}
	%>
</body>
</html>