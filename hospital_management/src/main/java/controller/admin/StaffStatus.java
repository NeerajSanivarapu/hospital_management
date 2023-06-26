package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Staff;

@WebServlet("/adminstaffstatus")
public class StaffStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		MyDao dao = new MyDao();
		Staff staff = dao.fetchStaff(id);
		if (staff.isStatus())
			staff.setStatus(false);
		else
			staff.setStatus(true);

		dao.updateStaff(staff);

		resp.getWriter().print("<h1 style='color:green'>Status Updated</h1>");
		req.setAttribute("list", dao.fetchAllStaff());
		req.getRequestDispatcher("ApproveStaff.jsp").include(req, resp);
	}
}
