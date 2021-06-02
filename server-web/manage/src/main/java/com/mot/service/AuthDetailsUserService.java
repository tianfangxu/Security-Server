package com.mot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mot.common.constant.CommonConstant;
import com.mot.common.utils.RedisUtil;
import com.mot.mapper.ResourceMapper;
import com.mot.mapper.UserMapper;
import com.mot.model.AuthResourceModel;
import com.mot.model.AuthUserModel;
import com.mot.model.ResultResourceModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthDetailsUserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userObject = redisUtil.getCache(redisUtil.userLoginPrefix+username);
        AuthUserModel authUserModel;
        if (userObject == null){
            /**
             * 需要做缓存机制，不然需要每次查询数据库
             */
            authUserModel = userMapper.queryLoginUser(username);
            List<ResultResourceModel> resultResourceList = resourceMapper.queryResouceByRoleId
                    (authUserModel.getRoleList().stream().map(resultRoleInfoModel -> resultRoleInfoModel.getId()).collect(Collectors.toList()));
            getMethodResource(resultResourceList,authUserModel);
            getPageResource(resultResourceList,authUserModel);
            AuthUserModel clone = authUserModel.clone();
            Collection<? extends GrantedAuthority> authorities = clone.getAuthorities();
            List<String> list = authorities.stream().map(authoritie -> authoritie.getAuthority()).collect(Collectors.toList());
            clone.setAuthorities(null);
            String string = JSON.toJSONString(clone);
            JSONObject jsonObject = JSON.parseObject(string);
            jsonObject.put("authoritieList", list);
            redisUtil.setCache(redisUtil.userLoginPrefix+username,JSON.toJSONString(jsonObject));
        }else {
            /**
             * GrantedAuthority序列化有问题，需要手动排除authorities
             */
            JSONObject jsonObject = JSON.parseObject(userObject);
            List<String> authorities = jsonObject.getObject("authoritieList",List.class);
            authUserModel = JSON.parseObject(JSON.toJSONString(jsonObject), AuthUserModel.class);
            authUserModel.setAuthorities(AuthorityUtils.createAuthorityList(authorities.toArray(new String[authorities.size()])));
        }
        return authUserModel;
    }

    public void getMethodResource(List<ResultResourceModel> resultResourceList,AuthUserModel authUserModel){
        List<String> urls = resultResourceList.stream()
                .filter( resultResourceModel -> resultResourceModel.getType() == CommonConstant.BASE_DATA_RESORCE_TYPE_METHOD)
                .map(resultResourceModel -> resultResourceModel.getResourceUrl())
                .collect(Collectors.toList());
        authUserModel.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",urls)));
    }

    public void getPageResource(List<ResultResourceModel> resultResourceList,AuthUserModel authUserModel){
        ArrayList<AuthResourceModel> list = new ArrayList<>();
        for (ResultResourceModel model : resultResourceList) {
            if (model.getType()== CommonConstant.BASE_DATA_RESORCE_TYPE_PAGE
                && (model.getId().equals( model.getParentId()) || StringUtils.isEmpty(model.getParentId()))) {
                AuthResourceModel resourceModel = new AuthResourceModel();
                BeanUtils.copyProperties(model,resourceModel);
                getChild(resultResourceList,resourceModel);
                list.add(resourceModel);
            }
        }
        authUserModel.setResourceList(list);
    }

    private void getChild(List<ResultResourceModel> list, AuthResourceModel model) {
        for (ResultResourceModel resourceModel : list) {
            if (model.getId().equals(resourceModel.getParentId())){
                List<AuthResourceModel> childs = model.getChilds();
                if (childs == null){
                    childs = new ArrayList<>();
                    model.setChilds(childs);
                }
                AuthResourceModel authResourceModel = new AuthResourceModel();
                BeanUtils.copyProperties(resourceModel,authResourceModel);
                childs.add(authResourceModel);
                getChild(list,authResourceModel);
            }
        }
    }

}
