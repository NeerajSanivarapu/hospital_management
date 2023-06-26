package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Doctor;
import dto.Staff;

@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String password = req.getParameter("password");

		MyDao dao = new MyDao();
		Doctor doctor = dao.fetchDoctor(id);
		Staff staff = dao.fetchStaff(id);

		if (staff == null && doctor == null && id != 999999) {
			resp.getWriter().print("<h1 style='color:red'>Incorrect Id</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			if (staff != null) {
				if (staff.getPassword().equals(password)) {
					if (staff.isStatus()) {
						req.getSession().setAttribute("staff", staff);
						resp.getWriter().print("<h1 style='color:green'>Login Success</h1>");
						req.getRequestDispatcher("StaffHome.html").include(req, resp);
					} else {
						resp.getWriter().print("<h1 style='color:red'>Wait for Admin approval</h1>");
						req.getRequestDispatcher("Login.html").include(req, resp);
					}
				} else {
					resp.getWriter().print("<h1 style='color:red'>Incorrect Password</h1>");
					req.getRequestDispatcher("Login.html").include(req, resp);
				}
			}
			if (doctor != null) {
				if (doctor.getPassword().equals(password)) {
					if (doctor.isStatus()) {
						req.getSession().setAttribute("doctor", doctor);
						resp.getWriter().print("<h1 style='color:green'>Login Success</h1>");
						req.getRequestDispatcher("DoctorHome.html").include(req, resp);
					} else {
						resp.getWriter().print("<h1 style='color:red'>Wait for Admin approval</h1>");
						req.getRequestDispatcher("Login.html").include(req, resp);
					}
				} else {
					resp.getWriter().print("<h1 style='color:red'>Incorrect Password</h1>");
					req.getRequestDispatcher("Login.html").include(req, resp);
				}
			}
			if (id == 999999) {
				if ("999999".equals(password)) {
					req.getSession().setAttribute("admin", "admin");
					resp.getWriter().print("<h1 style='color:green'>Login Success</h1>");
					req.getRequestDispatcher("AdminHome.html").include(req, resp);
				} else {
					resp.getWriter().print("<h1 style='color:red'>Incorrect Password</h1>");
					req.getRequestDispatcher("Login.html").include(req, resp);
				}
			}
		}
	}
}
