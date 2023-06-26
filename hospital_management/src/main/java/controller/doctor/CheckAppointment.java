package controller.doctor;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Appointment;

@WebServlet("/checkappointment")
public class CheckAppointment extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		MyDao dao = new MyDao();

		Appointment appointment = dao.fetchAppointment(id);
		appointment.setTime(LocalDateTime.now());
		dao.updateAppointment(appointment);

		resp.getWriter().print("<h1>Successfully Updated</h1>");
		req.getRequestDispatcher("DoctorHome.html").include(req, resp);
	}
}
