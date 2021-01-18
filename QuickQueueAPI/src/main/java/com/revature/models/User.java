package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.enums.UserRole;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
@Table(name = "users")
@TypeDef(
    name = "user_role_enum",
    typeClass = PostgreSQLEnumType.class
)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	private String username;
	
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	private String email;

    @Enumerated(EnumType.STRING)
	@Column(name = "user_role")
    @Type( type = "user_role_enum" )
    private UserRole userRole;

    @OneToMany(mappedBy = "cartOwner", fetch = FetchType.LAZY)
    @JsonManagedReference(value="cartOwners")
	private List<Cart> cartOwners;
    
    @OneToMany(mappedBy = "cartShopper", fetch = FetchType.LAZY)
    @JsonManagedReference(value="cartShopper")
	private List<Cart> cartShopper;

	public User() {
		super();		
	}

	public User(int userId, String username, String password, String firstName, String lastName, String email,
			UserRole userRole, List<Cart> cartOwners, List<Cart> cartShopper) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
		this.cartOwners = cartOwners;
		this.cartShopper = cartShopper;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
		
	public void setPassword(String password){	
		this.password = password;
	}

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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<Cart> getCartOwners() {
		return cartOwners;
	}

	public void setCartOwners(List<Cart> cartOwners) {
		this.cartOwners = cartOwners;
	}

	public List<Cart> getCartShopper() {
		return cartShopper;
	}

	public void setCartShopper(List<Cart> cartShopper) {
		this.cartShopper = cartShopper;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartOwners == null) ? 0 : cartOwners.hashCode());
		result = prime * result + ((cartShopper == null) ? 0 : cartShopper.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (cartOwners == null) {
			if (other.cartOwners != null)
				return false;
		} else if (!cartOwners.equals(other.cartOwners))
			return false;
		if (cartShopper == null) {
			if (other.cartShopper != null)
				return false;
		} else if (!cartShopper.equals(other.cartShopper))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (userRole != other.userRole)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", userRole=" + userRole + ", cartOwners="
				+ cartOwners + ", cartShopper=" + cartShopper + "]";
	}

		
}
