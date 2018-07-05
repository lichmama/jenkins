package com.lichmama.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.lichmama.demo.entity.OperationLog;

@Repository
public interface ILogDAO {

	@Insert("insert into operation_logs "
			+ "(user_id, module, operation, source, device, user_agent, description, create_time) "
			+ "values (#{userId}, #{module}, #{operation}, #{source}, #{device}, #{userAgent}, #{description}, #{createTime})")
	int saveLog(OperationLog log);
}
