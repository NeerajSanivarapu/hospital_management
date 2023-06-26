package dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Doctor {
	@Id
	@GeneratedValue(generator = "doctorid")
	@SequenceGenerator(initialValue = 456001, allocationSize = 1, name = "doctorid", sequenceName = "doctorid")
	private int id;
	private String name;
	private long mobile;
	private String email;
	private String password;
	private String gender;
	private Date dob;
	private int age;
	private boolean status;
	private String qualification;
	private String specialization;
	private boolean available;

	@OneToMany
	List<Appointment> appointments;
}
