package controller.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Appointment;
import dto.Doctor;
import dto.Patient;

@WebServlet("/bookappointment")
public class BookAppointment extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pid=Integer.parseInt(req.getParameter("pid"));
		int did=Integer.parseInt(req.getParameter("doctor"));
		String problem=req.getParameter("problem");
		MyDao dao=new MyDao();
		Doctor doctor=dao.fetchDoctor(did);
		Patient patient=dao.fetchPatient(pid);
		
		Appointment appointment=new Appointment();
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setProblem(problem);
		
		List<Appointment> list1=patient.getAppointments();
		if(list1==null)
		{
			list1=new ArrayList<>();
		}
		list1.add(appointment);
		patient.setAppointments(list1);
		
		List<Appointment> list2=doctor.getAppointments();
		if(list2==null)
		{
			list2=new ArrayList<>();
		}
		list2.add(appointment);
		doctor.setAppointments(list2);
		
		dao.saveAppointment(appointment);
		dao.updateDoctor(doctor);
		dao.updatePatient(patient);
		
		resp.getWriter().print("<h1>Appointment of "+patient.getName()+" is booked with "+doctor.getName()+" for "+problem+"</h1>");
		req.getRequestDispatcher("StaffHome.html").include(req, resp);
	}
}
