package com.mot.model;

import com.mot.common.constant.CommonConstant;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Data
public class AuthUserModel implements UserDetails,Cloneable {

    private String id;
    private String username;
    private String password;
    private String realName;
    private String avatar;
    private String phone;
    private String email;
    private int sex;
    private int locked;

    private List<ResultRoleInfoModel> roleList;
    @Ignore
    List<AuthResourceModel> resourceList;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return locked == CommonConstant.BASE_DATA_LOCKED_FALSE;
    }

    @Override
    public AuthUserModel clone(){
        try {
            AuthUserModel clone = (AuthUserModel) super.clone();
            Collection<? extends GrantedAuthority> authorities = clone.getAuthorities();
            if (authorities !=null){
                Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
                List<GrantedAuthority> newAuthorities = new ArrayList(authorities.size());
                while (iterator.hasNext()) {
                    newAuthorities.add(iterator.next());
                }
                clone.setAuthorities(newAuthorities);
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 清除敏感信息
     * @return
     */
    public AuthUserModel clearSensitiveInfo(){
        this.setPassword("");
        this.setAuthorities(null);
        return this;
    }
}
