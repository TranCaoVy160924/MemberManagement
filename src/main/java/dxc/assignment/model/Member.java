package dxc.assignment.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Member {
	private int id;

	@NotBlank(message = "Username is required")
	private String username;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;

	@Pattern(regexp = "\\d{10}", message = "Invalid phone number format")
	@NotBlank(message = "Phone number is required")
	private String phoneNumber;

	@Size(min = 8, max = 25, message = "Password must be at least 8 characters")
	@NotBlank(message = "Password is required")
	private String password;

	public Member(int id, String username, String email, String phoneNumber,
			String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public static Member getDefault() {
		return new Member(0, "", "", "", "");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
