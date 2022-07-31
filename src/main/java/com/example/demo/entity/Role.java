package com.example.demo.entity;

import javax.persistence.*;

import com.example.demo.entity.enumeration.RoleName;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.*;

@Entity
@Table(name = "t_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role_name")
	private RoleName roleName;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
}
