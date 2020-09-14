package com.mot.service.impl;

import com.mot.model.AuthUserModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service
public class UserPermissionService {

    /**
     * 判断请求是否有权限访问
     * @param request   请求对象
     * @param authentication    请求用户信息
     * @return  true:请求通过
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthUserModel){
            Collection<? extends GrantedAuthority> authorities = ((AuthUserModel) principal).getAuthorities();
            return authorities.contains(AuthorityUtils.commaSeparatedStringToAuthorityList(request.getRequestURI()).get(0));
        }
        return false;
    }
}
