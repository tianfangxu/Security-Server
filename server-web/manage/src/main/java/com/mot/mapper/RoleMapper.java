package com.mot.mapper;

import com.mot.entity.RoleEntity;
import com.mot.entity.RoleResourceEntity;
import com.mot.model.ParamRoleModel;
import com.mot.model.ParamUserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    List<RoleEntity> queryRole(@Param("model") ParamRoleModel model);

    int queryRoleCount(@Param("model") ParamRoleModel model);

    int insertRole(RoleEntity roleEntity);

    int batchInsertRoleResource(List<RoleResourceEntity> list);

    int batchDeleteRoleResource(List<RoleResourceEntity> list);

    List<RoleEntity> queryRoleByUserId(@Param("model") ParamUserModel model);
}
