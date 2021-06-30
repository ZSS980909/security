package com.zss98.AuthorityResolving.Mapper;

import com.zss98.AuthorityResolving.Entity.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResMapper {

    List<UserDTO> getAll();
}
