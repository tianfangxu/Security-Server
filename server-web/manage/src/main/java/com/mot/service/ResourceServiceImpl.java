package com.mot.service;

import com.mot.common.utils.ModelUtils;
import com.mot.common.utils.RedisUtil;
import com.mot.entity.ResourceEntity;
import com.mot.mapper.ResourceMapper;
import com.mot.model.*;
import com.mot.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Map queryResource(ParamResourceModel model) {
        Map map = new HashMap<>();
        ModelUtils.clearModel(model);
        if (!StringUtils.isEmpty(model.getResourceCode())){
            model.setResourceCode("%"+model.getResourceCode()+"%");
        }
        if (!StringUtils.isEmpty(model.getResourceName())){
            model.setResourceName("%"+model.getResourceName()+"%");
        }
        model.setPage((model.getPage() -1) * model.getRows());
        List<ResourceEntity> entityList = resourceMapper.queryResouce(model);
        int count = resourceMapper.queryResouceCount(model);
        List<ResultResourceInfoModel> list =
                entityList.stream().map(ModelUtils::transferResourceEntity).collect(Collectors.toList());
        map.put("total",count);
        map.put("rows",list);
        return map;
    }

    @Override
    public ResultBaseModel insertResource(ParamResourceModel model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return ResultBaseModel.getFailure().setMessage("当前用户未登陆");
        }
        AuthUserModel currentUser = (AuthUserModel) authentication.getPrincipal();
        ResourceEntity resourceEntity = new ResourceEntity();
        ModelUtils.clearModel(model);
        model.transferEntity(resourceEntity,currentUser.getId());
        int count = resourceMapper.insertResource(resourceEntity);
        return ResultBaseModel.getSuccess().setMessage("成功新增"+count+"条数据");
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ResultBaseModel deleteResource(ParamResourceModel model) {
        if (ModelUtils.isNullOrEmpty(model.getId())) {
            return ResultBaseModel.getFailure().setMessage("主键必须传！");
        }
        int count = resourceMapper.deleteResource(model.getId());
        if (count == 1){
            //删除关联表数据
            int i = resourceMapper.deleteRoleResourceByResourceId(model.getId());
            //删除缓存中数据
            redisUtil.deleteUniformKeys(redisUtil.userLoginPrefix+"*");
        }
        return ResultBaseModel.getSuccess().setMessage("操作成功");
    }
}
