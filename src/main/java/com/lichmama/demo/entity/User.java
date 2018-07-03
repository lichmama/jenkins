package com.lichmama.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class User implements Serializable {
	
	private int id;
	
	private String username;
	
	private String password;
	
	private String realname;
	
	private int gender;
	
	private String phone;
	
	private String email;
	
	private int status;
	
	private Date createTime;
	
	private Date updateTime;
}
