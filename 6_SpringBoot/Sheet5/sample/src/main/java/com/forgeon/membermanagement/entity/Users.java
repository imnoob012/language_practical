package com.forgeon.membermanagement.entity;										
										
import java.math.BigDecimal;										
										
public class Users {										
										
	private BigDecimal id;										
	private String name;
	private String password;
										
	// コンストラクタ										
	public Users(BigDecimal id, String name) {										
		this.id = id;										
		this.name = name;										
	}										
											
	// setter										
	public void setId(BigDecimal id) {										
		this.id = id;										
	}										
											
	// getter										
	public BigDecimal getId() {										
		return id;										
	}										
											
	// setter										
	public void setName(String name) {										
		this.name = name;										
	}										
											
	// getter										
	public String getName() {										
		return name;										
	}
	
	// setter										
	public void setPassword(String password) {										
		this.password = password;										
	}										
											
	// getter										
	public String getPassword() {										
		return password;										
	}
										
}