package com.dahirkanehl.olakoa.users;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private Role role;
	private String id;
	private String password;
	private boolean enabled;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	private User(Builder b) {
		this.firstName = b.firstName;
		this.lastName = b.lastName;
		this.username = b.username;
		this.email = b.email;
		this.role = b.role;
		this.id = b.id;
		this.password = b.password;
		this.enabled = b.enabled;
	}
	
	@Override
	public String toString() {
		return "User [name=" + firstName + " " + lastName + ", id=" + id + ", username="+ username + ", email="+ email + ", role="+ role + ", password=" + password + ", enabled="+ enabled + "]";
	}

	public static class Builder {
		private String firstName;
		private String lastName;
		private String email;
		private String username;
		private Role role;
		private String id;
		private String password;
		private boolean enabled;
		
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder username(String username) {
			this.username = username;
			return this;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public Builder role(Role role) {
			this.role = role;
			return this;
		}
		
		public Builder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
	
}
