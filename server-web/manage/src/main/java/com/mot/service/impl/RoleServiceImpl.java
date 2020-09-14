package com.mot.service.impl;

import com.mot.common.constant.CommonConstant;
import com.mot.common.utils.ModelUtils;
import com.mot.common.utils.RedisUtil;
import com.mot.entity.ResourceEntity;
import com.mot.entity.RoleEntity;
import com.mot.entity.RoleResourceEntity;
import com.mot.mapper.ResourceMapper;
import com.mot.mapper.RoleMapper;
import com.mot.model.*;
import com.mot.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public Map queryRole(ParamRoleModel model) {
        Map map = new HashMap<>();
        ModelUtils.clearModel(model);
        if (model.getRoleCode() != null){
            model.setRoleCode("%"+model.getRoleCode()+"%");
        }
        if (model.getRoleName() != null){
            model.setRoleName("%"+model.getRoleName()+"%");
        }
        model.setPage((model.getPage() -1) * model.getRows());
        List<RoleEntity> roleEntities = roleMapper.queryRole(model);
        int count = roleMapper.queryRoleCount(model);
        List<ResultRoleInfoModel> list =
                roleEntities.stream().map(ModelUtils::transferRoleEntity).collect(Collectors.toList());
        map.put("total",count);
        map.put("rows",list);
        return map;
    }

    @Override
    public ResultBaseModel insertRole(ParamRoleModel model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return ResultBaseModel.getFailure().setMessage("当前用户未登陆");
        }
        AuthUserModel currentUser = (AuthUserModel) authentication.getPrincipal();
        RoleEntity roleEntity = new RoleEntity();
        model.transferEntity(roleEntity,currentUser.getId());
        int count = roleMapper.insertRole(roleEntity);
        return ResultBaseModel.getSuccess().setMessage("成功新增"+count+"条数据");
    }

    @Override
    public Map queryRoleResource(ParamRoleModel model) {
        if (ModelUtils.isNullOrEmpty(model.getId())){
            throw new RuntimeException("参数错误，roleId必须传");
        }
        List<ResultResourceModel> resultResourceModels = resourceMapper.queryResouceByRoleId(Arrays.asList(model.getId()));
        List<String> list =
                resultResourceModels.stream()
                        .filter(resultResourceModel -> resultResourceModel.getType() == CommonConstant.BASE_DATA_RESORCE_TYPE_METHOD)
                        .map(resultResourceModel -> resultResourceModel.getId()).collect(Collectors.toList());
        ParamResourceModel allModel = new ParamResourceModel();
        int maxCount = resourceMapper.queryResouceCount(allModel);
        allModel.setRows(maxCount);
        allModel.setPage(0);
        allModel.setType(String.valueOf(CommonConstant.BASE_DATA_RESORCE_TYPE_METHOD));
        List<ResourceEntity> entityList = resourceMapper.queryResouce(allModel);
        List<Map<String, Object>> maps = entityList.stream().map(entity -> {
            Map<String, Object> map = new HashMap<>();
            map.put("key", entity.getId());
            map.put("label", entity.getResourceName());
            map.put("disabled", false);
            return map;
        }).collect(Collectors.toList());
        Map map = new HashMap<>();
        map.put("list",list);
        map.put("maps",maps);
        return map;
    }

    @Override
    public ResultBaseModel batchInsertRoleResource(List<ParamRoleResourceModel> list) {
        if (list ==null || list.size() == 0){
            return ResultBaseModel.getSuccess().setMessage("成功新增0条数据");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return ResultBaseModel.getFailure().setMessage("当前用户未登陆");
        }
        AuthUserModel currentUser = (AuthUserModel) authentication.getPrincipal();
        List<RoleResourceEntity> entityList = list.stream().map(paramRoleResourceModel -> {
            RoleResourceEntity entity = new RoleResourceEntity();
            paramRoleResourceModel.transferEntity(entity, currentUser.getId());
            return entity;
        }).collect(Collectors.toList());
        int count = roleMapper.batchInsertRoleResource(entityList);
        return ResultBaseModel.getSuccess().setMessage("成功新增"+count+"条数据");
    }

    @Override
    public ResultBaseModel batchDeleteRoleResource(List<ParamRoleResourceModel> list) {
        if (list ==null || list.size() == 0){
            return ResultBaseModel.getSuccess().setMessage("成功删除0条数据");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return ResultBaseModel.getFailure().setMessage("当前用户未登陆");
        }
        AuthUserModel currentUser = (AuthUserModel) authentication.getPrincipal();
        List<RoleResourceEntity> entityList = list.stream().map(paramRoleResourceModel -> {
            RoleResourceEntity entity = new RoleResourceEntity();
            BeanUtils.copyProperties(paramRoleResourceModel,entity);
            entity.deleteEntiy(currentUser.getId());
            return entity;
        }).collect(Collectors.toList());
        int count = roleMapper.batchDeleteRoleResource(entityList);
        return ResultBaseModel.getSuccess().setMessage("成功删除"+count+"条数据");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBaseModel batchUpdateRoleResource(ParamRoleResourceModel list) {
        List<ParamRoleResourceModel> add = list.getAdd();
        List<ParamRoleResourceModel> del = list.getDel();
        String addMessage = batchInsertRoleResource(add).getMessage();
        String delMessage = batchDeleteRoleResource(del).getMessage();
        //删除缓存中数据
        redisUtil.deleteUniformKeys(redisUtil.userLoginPrefix+"*");
        return ResultBaseModel.getSuccess().setMessage(addMessage+";"+delMessage);
    }

    @Override
    public Map queryRoleByUserId(ParamUserModel model) {
        ParamRoleModel paramRoleModel = new ParamRoleModel();
        int count = roleMapper.queryRoleCount(paramRoleModel);
        paramRoleModel.setPage(0);
        paramRoleModel.setRows(count);
        List<RoleEntity> roleEntities = roleMapper.queryRole(paramRoleModel);
        List<HashMap<String, String>> mapList = roleEntities.stream().map(entity -> {
            HashMap<String, String> roleMap = new HashMap<>();
            roleMap.put("value", entity.getId());
            roleMap.put("label", entity.getRoleName());
            return roleMap;
        }).collect(Collectors.toList());
        List<RoleEntity> roles = roleMapper.queryRoleByUserId(model);

        HashMap<String, Object> map = new HashMap<>();
        map.put("roleAllList",mapList);
        map.put("roles",roles);
        return map;
    }
}
