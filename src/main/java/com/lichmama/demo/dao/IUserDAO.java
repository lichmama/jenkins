package com.lichmama.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.lichmama.demo.entity.User;

@Repository
public interface IUserDAO {

	@Select("select "
			+ "id, username, password, realname, gender, phone, email, create_time createTime, update_time updateTime "
			+ "from users where id = #{id}")
	User getUserById(int id);

	@Select("select "
			+ "id, username, password, realname, gender, phone, email, create_time createTime, update_time updateTime "
			+ "from users where username = #{username}")
	User getUserByName(String username);

	@Insert("insert into users(id, username, password, realname, gender, phone, email, create_time createTime, update_time updateTime) "
			+ "values(#{id}, #{username}, #{password}, #{realname}, #{gender}, #{phone}, #{email}, sysdate, sysdate)")
	void addUser(User user);

	@Update("update users "
			+ "set username = #{username}, password = #{password}, realname = #{realname}, gender = #{sex}, phone = #{phone}, "
			+ "email = #{email}, update_time = sysdate "
			+ "where id = #{id}")
	void updateUser(User user);

	@Delete("delete from users where id = #{id}")
	void deleteUser(int id);
}
