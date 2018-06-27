package com.lichmama.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class User implements Serializable {
	
	private int id;
	
	private String username;
	
	private String password;
	
	private int sex;
	
	private Date createTime;
}
