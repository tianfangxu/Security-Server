package com.mot.common.constant;

import com.mot.common.annotation.FiledUrlAnnotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author tianfx
 * @Date 2020/9/20
 * 所有的访问接口都应该再这个类注明
 */
public class URLConstant {

    /**
     * @LogsWriteAnnotation 标注该注解表示该接口不需要写入访问日志到数据库
     */

    @FiledUrlAnnotation(hasPermit = false)
    public static final String LOGIN_URL_01 = "login";
    @FiledUrlAnnotation
    public static final String LOGIN_URL_02 = "refreshToken";

    @FiledUrlAnnotation
    public static final String USER_URL_01 = "queryUser";
    @FiledUrlAnnotation
    public static final String USER_URL_02 = "insertUser";
    @FiledUrlAnnotation
    public static final String USER_URL_03 = "updateUser";
    @FiledUrlAnnotation
    public static final String USER_URL_04 = "deleteUser";
    @FiledUrlAnnotation
    public static final String USER_URL_05 = "batchInsertUser";
    @FiledUrlAnnotation
    public static final String USER_URL_06 = "batchUpdateUser";
    @FiledUrlAnnotation
    public static final String USER_URL_07 = "batchDeleteUser";
    @FiledUrlAnnotation
    public static final String USER_URL_08 = "resetPassword";

    @FiledUrlAnnotation
    public static final String ROLE_URL_01 = "queryRole";
    @FiledUrlAnnotation
    public static final String ROLE_URL_02 = "insertRole";
    @FiledUrlAnnotation
    public static final String ROLE_URL_03 = "queryRoleResource";
    @FiledUrlAnnotation
    public static final String ROLE_URL_04 = "batchInsertRoleResource";
    @FiledUrlAnnotation
    public static final String ROLE_URL_05 = "batchDeleteRoleResource";
    @FiledUrlAnnotation
    public static final String ROLE_URL_06 = "batchUpdateRoleResource";
    @FiledUrlAnnotation
    public static final String ROLE_URL_07 = "queryRoleByUserId";

    @FiledUrlAnnotation
    public static final String RESOURCE_URL_01 = "queryResource";
    @FiledUrlAnnotation
    public static final String RESOURCE_URL_02 = "insertResource";
    @FiledUrlAnnotation
    public static final String RESOURCE_URL_03 = "deleteResource";
    @FiledUrlAnnotation(writeLogs = false)
    public static final String LOGACTION_URL_01 = "queryLogAction";



    private static URLConstant instance = new URLConstant();
    private List<String> list;
    public static URLConstant getInstance(){
        return instance != null ? instance : initBean();
    }
    private synchronized static URLConstant initBean() {
        return instance != null ? instance : (instance= new URLConstant());
    }

    /**
     * 获取所有的访问地址
     * @return
     */
    public List<String> urlsAll(){
        if (this.list == null){
            this.list = new ArrayList<>();
            Class<? extends URLConstant> clazz = this.getClass();
            Field[] fields = clazz.getFields();
            for (Field field : fields) {
                FiledUrlAnnotation annotation = field.getAnnotation(FiledUrlAnnotation.class);
                if (annotation != null){
                    field.setAccessible(true);
                    try {
                        Object o = field.get(null);
                        this.list.add("/"+String.valueOf(o));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.list;
    }
}
