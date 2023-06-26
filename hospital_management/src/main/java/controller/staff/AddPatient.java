package controller.staff;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.MyDao;
import dto.Patient;

@WebServlet("/addpatient")
@MultipartConfig
public class AddPatient extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		Date dob=Date.valueOf(req.getParameter("dob"));
		int age=Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
		Part pic=req.getPart("picture");
		
		byte[] picture=new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		
		MyDao dao=new MyDao();
		
		Patient patient1=dao.fetchPatient(mobile);
		if(patient1==null)
		{
		Patient patient=new Patient();
		patient.setAge(age);
		patient.setDob(dob);
		patient.setMobile(mobile);
		patient.setName(name);
		patient.setPicture(picture);
				
		dao.savePatient(patient);
		
		resp.getWriter().print("<h1 style='color:green'>Patient Data Added Successfully</h1>");
		req.getRequestDispatcher("StaffHome.html").include(req, resp);
		}
		else{
			resp.getWriter().print("<h1 style='color:red'>Mobile Number should be unique</h1>");
			req.getRequestDispatcher("AddPatient.html").include(req, resp);
		}
	}
}
