package com.mot.common.utils;

import com.mot.common.constant.CommonConstant;
import com.mot.entity.ResourceEntity;
import com.mot.entity.RoleEntity;
import com.mot.entity.UserEntity;
import com.mot.model.ResultResourceInfoModel;
import com.mot.model.ResultRoleInfoModel;
import com.mot.model.ResultUserInfoModel;

import java.lang.reflect.Field;

public class ModelUtils {

    public static void clearModel(Object obj){
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object o = field.get(obj);
                if (isNullOrEmpty(o) ) {
                    field.set(obj, null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isNullOrEmpty(Object val){
        return val == null || "".equals(val) || "null".equals(val) || "undefined".equals(val);
    }

    public static ResultUserInfoModel transferUserEntity(UserEntity entity){
        ResultUserInfoModel model = new ResultUserInfoModel();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setPassword("******");
        model.setRealName(entity.getRealName());
        model.setAvatar(entity.getAvatar());
        model.setPhone(entity.getPhone());
        model.setEmail(entity.getEmail());
        model.setSex(entity.getSex() == CommonConstant.BASE_DATA_SEX_MALE ? "男":"女");
        model.setLocked(entity.getLocked() == CommonConstant.BASE_DATA_LOCKED_FALSE? "否":"是");
        model.setCreateTime(String.valueOf(entity.getCreateTime()));
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(String.valueOf(entity.getUpdateTime()));
        model.setUpdateUser(entity.getUpdateUser());
        model.setDelflag(entity.getDelflag() == CommonConstant.BASE_DATA_FLAG_EXIT?"存在":"删除");
        return model;
    }

    public static ResultResourceInfoModel transferResourceEntity(ResourceEntity entity) {
        ResultResourceInfoModel model = new ResultResourceInfoModel();
        model.setId(entity.getId());
        model.setResourceCode(entity.getResourceCode());
        model.setResourceName(entity.getResourceName());
        model.setResourceUrl(entity.getResourceUrl());
        model.setParentId(entity.getParentId());
        model.setSort(String.valueOf(entity.getSort()));
        model.setIsLeaf(entity.getIsLeaf() == CommonConstant.BASE_DATA_LEAF_YES?"是":"否");
        model.setType(entity.getType() == CommonConstant.BASE_DATA_RESORCE_TYPE_METHOD?"接口":"页面");
        model.setCreateTime(String.valueOf(entity.getCreateTime()));
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(String.valueOf(entity.getUpdateTime()));
        model.setUpdateUser(entity.getUpdateUser());
        model.setDelflag(entity.getDelflag() == CommonConstant.BASE_DATA_FLAG_EXIT?"存在":"删除");
        return model;
    }

    public static ResultRoleInfoModel transferRoleEntity(RoleEntity entity) {
        ResultRoleInfoModel model = new ResultRoleInfoModel();
        model.setId(entity.getId());
        model.setRoleCode(entity.getRoleCode());
        model.setRoleName(entity.getRoleName());
        model.setDescription(entity.getDescription());
        model.setCreateTime(String.valueOf(entity.getCreateTime()));
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(String.valueOf(entity.getUpdateTime()));
        model.setUpdateUser(entity.getUpdateUser());
        model.setDelflag(entity.getDelflag() == CommonConstant.BASE_DATA_FLAG_EXIT?"存在":"删除");
        return model;
    }
}
