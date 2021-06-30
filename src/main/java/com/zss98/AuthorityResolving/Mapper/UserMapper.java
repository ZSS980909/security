package com.zss98.AuthorityResolving.Mapper;

import com.zss98.AuthorityResolving.Entity.User;
import com.zss98.AuthorityResolving.Entity.UserData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from sys_user")
    List<UserData> getAllUser();

    User selectUserByName(String user);

    UserData getUserInfo(String user);
}
