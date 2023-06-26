package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Doctor;

@WebServlet("/admindoctorstatus")
public class DoctorStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		MyDao dao = new MyDao();
		Doctor doctor = dao.fetchDoctor(id);
		if (doctor.isStatus())
			doctor.setStatus(false);
		else
			doctor.setStatus(true);

		dao.updateDoctor(doctor);

		resp.getWriter().print("<h1 style='color:green'>Status Updated</h1>");
		req.setAttribute("list", dao.fetchAllDoctor());
		req.getRequestDispatcher("ApproveDoctor.jsp").include(req, resp);
	}
}
