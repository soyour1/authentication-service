package com.example.demo.entity;

import java.util.*;

import javax.persistence.*;

import com.example.demo.entity.enumeration.RoleName;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ManyToAny;

import lombok.Data;

@Entity
@Table(name = "t_user")
@NoArgsConstructor
@Data
public class User {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "picture")
	private String picture;
	
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Role> roles = new ArrayList<>();
	
	public void addRole(Role role){
		this.roles.add(role);
		role.setUser(this);
	}
}
