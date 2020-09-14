package com.mot.mapper;

import com.mot.entity.UserEntity;
import com.mot.model.AuthUserModel;
import com.mot.model.ParamUserModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    /**
     * 通过用户名称查询 用户基本信息和用户role_id
     * @param username
     * @return
     */
    AuthUserModel queryLoginUser(@Param("username") String username);

    @Select("select * from sy_user")
    List<UserEntity> queryAll();

    List<UserEntity> queryUser(@Param("model")ParamUserModel model);

    int queryUserCount(@Param("model")ParamUserModel model);

    int insetUser(UserEntity entity);

    int updateUser(@Param("entity")UserEntity entity);

    void deleteAllRolesByUserId(String id);

    int resetPassword(@Param("entity")UserEntity userEntity);
}
