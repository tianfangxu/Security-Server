package com.mot.service.impl;

import com.mot.common.utils.JwtTokenUtil;
import com.mot.model.AuthUserModel;
import com.mot.model.ResultBaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginUserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public ResultBaseModel login(String username, String password){
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken upToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (AuthenticationException e){
            e.printStackTrace();
            return ResultBaseModel.getFailure().setMessage("用户名或密码错误");
        }
        String token = jwtTokenUtil.generateToken(username);
        AuthUserModel userModel = (AuthUserModel) authentication.getPrincipal();
        AuthUserModel clone = userModel.clone();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("info",clone.clearSensitiveInfo());
        return ResultBaseModel.getSuccess().setResult(map).setMessage("登陆成功！");
    }

    public ResultBaseModel refreshToken(String token){
        String refreshToken = jwtTokenUtil.refreshToken(token);
        return ResultBaseModel.getSuccess().setResult(refreshToken);
    }

}
