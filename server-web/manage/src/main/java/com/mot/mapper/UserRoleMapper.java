package com.mot.mapper;

import com.mot.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {

    int insertUserRoleByRoleCode(@Param("entity") UserRoleEntity entity, @Param("roleCode") String roleCode);

    void insertUserRole(List<UserRoleEntity> list);
}
