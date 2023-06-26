package controller.doctor;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Doctor;

@WebServlet("/doctorsignup")
public class DoctorSignup extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	MyDao dao = new MyDao();

	String name = req.getParameter("name");
	long mobile = Long.parseLong(req.getParameter("mobile"));
	String email = req.getParameter("email");
	String password = req.getParameter("password");
	Date dob = Date.valueOf(req.getParameter("dob"));
	String gender = req.getParameter("gender");
	String qualification=req.getParameter("qualification");
	String specialization=req.getParameter("specialization");

	int age = Period.between(dob.toLocalDate(), LocalDate.now()).getYears();

	if (dao.fetchDoctor(mobile) == null && dao.fetchDoctor(email)==null
			&& dao.fetchStaff(mobile) == null && dao.fetchStaff(email)==null)
	{
		Doctor doctor = new Doctor();
		doctor.setName(name);
		doctor.setMobile(mobile);
		doctor.setEmail(email);
		doctor.setPassword(password);
		doctor.setDob(dob);
		doctor.setGender(gender);
		doctor.setAge(age);
		doctor.setQualification(qualification);
		doctor.setSpecialization(specialization);

		dao.saveDoctor(doctor);

		resp.getWriter().print("<h1 style='color:green'>Doctor Account Created Successfully, wait for Admin Approval</h1>");
		resp.getWriter().print("<h1 style='color:blue'>Your Doctor Id is : " + doctor.getId() + "</h1>");
		req.getRequestDispatcher("Login.html").include(req, resp);
	} else {
		resp.getWriter().print("<h1 style='color:red'>Mobile Number or Email already exists </h1>");
		req.getRequestDispatcher("DoctorSignup.html").include(req, resp);
	} 
}
}
