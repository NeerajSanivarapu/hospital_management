package dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Staff {
	@Id
	@GeneratedValue(generator = "staffid")
	@SequenceGenerator(initialValue = 121001, allocationSize = 1, name = "staffid", sequenceName = "staffid")
	private int id;
	private String name;
	private long mobile;
	private String email;
	private String password;
	private String gender;
	private Date dob;
	private int age;
	private boolean status;
}
