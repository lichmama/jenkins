package com.lichmama.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichmama.demo.dao.ILogDAO;
import com.lichmama.demo.entity.OperationLog;
import com.lichmama.demo.service.ILogService;

@Service
public class LogServiceImpl implements ILogService {
	
	@Autowired
	private ILogDAO logDAO;

	@Override
	public void saveLog(OperationLog log) {
		logDAO.saveLog(log);
	}
}
