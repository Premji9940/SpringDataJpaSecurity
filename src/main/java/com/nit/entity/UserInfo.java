package com.nit.entity;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="USERS")
@Entity
@Data
public class UserInfo {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer unid;
	@Column(length = 20,unique = true,nullable = false)
	private String uname;
	@Column(length = 150,nullable = false)
	private String  pwd;
	@Column(length = 20,nullable = false)
	private  String  status="1";
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name ="USER_ROLES",
	                                    joinColumns = @JoinColumn(name="USER_ID",referencedColumnName = "unid"))
	@Column(name="role")
	private Set<String> roles;

}