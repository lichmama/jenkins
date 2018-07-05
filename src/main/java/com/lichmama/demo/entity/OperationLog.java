package com.lichmama.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class OperationLog implements Serializable {
	private int id;
	
	private int userId;
	
	private String module;
	
	private String operation;
	
	private String source;
	
	private String userAgent;
	
	private String device;
	
	private String description;
	
	private Date createTime;
}
