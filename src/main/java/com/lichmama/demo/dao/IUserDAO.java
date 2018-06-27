package com.lichmama.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.lichmama.demo.entity.User;

@Repository
public interface IUserDAO {

	@Select("select id, username, password, sex, create_time createTime from users where id = #{id}")
	User getUserById(int id);

	@Select("select id, username, password, sex, create_time createTime from users where username = #{username}")
	User getUserByName(String username);

	@Insert("insert into users(id, username, password, sex, create_time) values(#{id}, #{username}, #{password}, #{sex}, #{createTime})")
	void addUser(User user);

	@Update("update users set username = #{username}, password = #{password}, sex = #{sex} where id = #{id}")
	void updateUser(User user);

	@Delete("delete from users where id = #{id}")
	void deleteUser(int id);
}
