package com.mot.service;

import com.mot.common.utils.ModelUtils;
import com.mot.common.utils.RedisUtil;
import com.mot.entity.UserEntity;
import com.mot.entity.UserRoleEntity;
import com.mot.mapper.UserMapper;
import com.mot.mapper.UserRoleMapper;
import com.mot.model.AuthUserModel;
import com.mot.model.ParamUserModel;
import com.mot.model.ResultBaseModel;
import com.mot.model.ResultUserInfoModel;
import com.mot.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Map queryUser(ParamUserModel model) {
        Map map = new HashMap<>();
        ModelUtils.clearModel(model);
        if (!StringUtils.isEmpty(model.getUsername())){
            model.setUsername("%"+model.getUsername()+"%");
        }
        if (!StringUtils.isEmpty(model.getRealName())){
            model.setRealName("%"+model.getRealName()+"%");
        }
        model.setPage((model.getPage() -1) * model.getRows());
        List<UserEntity> entityList = userMapper.queryUser(model);
        int count = userMapper.queryUserCount(model);
        List<ResultUserInfoModel> list =
                entityList.stream().map(ModelUtils::transferUserEntity).collect(Collectors.toList());
        map.put("total",count);
        map.put("rows",list);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBaseModel insertUser(ParamUserModel model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return ResultBaseModel.getFailure().setMessage("当前用户未登陆");
        }
        AuthUserModel currentUser = (AuthUserModel) authentication.getPrincipal();
        UserEntity userEntity = new UserEntity();
        model.transferEntity(userEntity,currentUser.getId());
        userEntity.setPassword(passwordEncoder.encode("123456"));
        int count = userMapper.insetUser(userEntity);
        if (count != 1){
            throw new RuntimeException("新增条数为0，请检查！");
        }
        String userId = userEntity.getId();
        String defaultRole = "common";
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userId);
        userRoleEntity.createEntity(currentUser.getId());
        userRoleMapper.insertUserRoleByRoleCode(userRoleEntity,defaultRole);
        return ResultBaseModel.getSuccess().setMessage("成功新增"+count+"条数据");

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBaseModel updateUser(ParamUserModel model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return ResultBaseModel.getFailure().setMessage("当前用户未登陆");
        }
        if (model.getId() == null ){
            return ResultBaseModel.getFailure().setMessage("主键必须传！");
        }
        AuthUserModel currentUser = (AuthUserModel) authentication.getPrincipal();
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(model,userEntity);
        userEntity.updateEntiy(currentUser.getId());
        int count = userMapper.updateUser(userEntity);
        //权限改变
        userMapper.deleteAllRolesByUserId(model.getId());
        List<UserRoleEntity> userRoleEntityList = model.getRoleIds().stream().map(role -> {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setRoleId(role);
            userRoleEntity.setUserId(model.getId());
            userRoleEntity.createEntity(currentUser.getId());
            return userRoleEntity;
        }).collect(Collectors.toList());
        userRoleMapper.insertUserRole(userRoleEntityList);
        //清空缓存
        redisUtil.del(redisUtil.userLoginPrefix+model.getUsername());
        return ResultBaseModel.getSuccess().setMessage("操作成功");
    }

    @Override
    public ResultBaseModel resetPassword(ParamUserModel model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return ResultBaseModel.getFailure().setMessage("当前用户未登陆");
        }
        if (model.getId() == null ){
            return ResultBaseModel.getFailure().setMessage("主键必须传！");
        }
        AuthUserModel currentUser = (AuthUserModel) authentication.getPrincipal();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(model.getId());
        userEntity.setPassword(passwordEncoder.encode("123456"));
        userEntity.updateEntiy(currentUser.getId());
        int count = userMapper.resetPassword(userEntity);
        return ResultBaseModel.getSuccess().setMessage("操作成功,重置后密码为：123456 ,请妥善保管！");
    }
}
