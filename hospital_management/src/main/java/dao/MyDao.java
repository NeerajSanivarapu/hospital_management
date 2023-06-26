package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Appointment;
import dto.Doctor;
import dto.Patient;
import dto.Staff;

public class MyDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public void saveStaff(Staff staff) {
		transaction.begin();
		manager.persist(staff);
		transaction.commit();
	}

	public void saveDoctor(Doctor doctor) {
		transaction.begin();
		manager.persist(doctor);
		transaction.commit();
	}

	public void savePatient(Patient patient) {
		transaction.begin();
		manager.persist(patient);
		transaction.commit();
	}

	public void updateStaff(Staff staff) {
		transaction.begin();
		manager.merge(staff);
		transaction.commit();
	}

	public void updateDoctor(Doctor doctor) {
		transaction.begin();
		manager.merge(doctor);
		transaction.commit();
	}

	public void updatePatient(Patient patient) {
		transaction.begin();
		manager.merge(patient);
		transaction.commit();
	}

	public List<Doctor> fetchAllDoctor() {
		return manager.createQuery("select x from Doctor x").getResultList();
	}

	public List<Staff> fetchAllStaff() {
		return manager.createQuery("select x from Staff x").getResultList();
	}

	public List<Patient> fetchAllPatient() {
		return manager.createQuery("select x from Patient x").getResultList();
	}

	public Staff fetchStaff(long mobile) {
		List<Staff> list = manager.createQuery("select x from Staff x where mobile=?1").setParameter(1, mobile)
				.getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Doctor fetchDoctor(long mobile) {
		List<Doctor> list = manager.createQuery("select x from Doctor x where mobile=?1").setParameter(1, mobile)
				.getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Patient fetchPatient(long mobile) {
		List<Patient> list = manager.createQuery("select x from Patient x where mobile=?1").setParameter(1, mobile)
				.getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Staff fetchStaff(String email) {
		List<Staff> list = manager.createNativeQuery("select * from staff where email=?1", Staff.class)
				.setParameter(1, email).getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Doctor fetchDoctor(String email) {
		List<Doctor> list = manager.createNativeQuery("select * from doctor where email=?1", Doctor.class)
				.setParameter(1, email).getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Staff fetchStaff(int id) {
		return manager.find(Staff.class, id);
	}

	public Doctor fetchDoctor(int id) {
		return manager.find(Doctor.class, id);
	}

	public Patient fetchPatient(int id) {
		return manager.find(Patient.class, id);
	}

	public List<Doctor> fetchAvailableDoctors() {
		return manager.createQuery("select x from Doctor x where available=true").getResultList();
	}

	public void saveAppointment(Appointment appointment) {
		transaction.begin();
		manager.persist(appointment);
		transaction.commit();
	}

	public void updateAppointment(Appointment appointment) {
		transaction.begin();
		manager.merge(appointment);
		transaction.commit();
	}

	public Appointment fetchAppointment(int id) {
		return manager.find(Appointment.class, id);
	}
}
