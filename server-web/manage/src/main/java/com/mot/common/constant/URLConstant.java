package com.mot.common.constant;

import com.mot.common.annotation.LogsWriteAnnotation;

public class URLConstant {

    public static final String LOGIN_URL_01 = "login";
    public static final String LOGIN_URL_02 = "refreshToken";

    public static final String USER_URL_01 = "queryUser";
    public static final String USER_URL_02 = "insertUser";
    public static final String USER_URL_03 = "updateUser";
    public static final String USER_URL_04 = "deleteUser";
    public static final String USER_URL_05 = "batchInsertUser";
    public static final String USER_URL_06 = "batchUpdateUser";
    public static final String USER_URL_07 = "batchDeleteUser";
    public static final String USER_URL_08 = "resetPassword";


    public static final String ROLE_URL_01 = "queryRole";
    public static final String ROLE_URL_02 = "insertRole";
    public static final String ROLE_URL_03 = "queryRoleResource";
    public static final String ROLE_URL_04 = "batchInsertRoleResource";
    public static final String ROLE_URL_05 = "batchDeleteRoleResource";
    public static final String ROLE_URL_06 = "batchUpdateRoleResource";
    public static final String ROLE_URL_07 = "queryRoleByUserId";

    public static final String RESOURCE_URL_01 = "queryResource";
    public static final String RESOURCE_URL_02 = "insertResource";
    public static final String RESOURCE_URL_03 = "deleteResource";

    /**
     * @LogsWriteAnnotation 标注该注解表示该接口不需要写入访问日志到数据库
     */
    @LogsWriteAnnotation
    public static final String LOGACTION_URL_01 = "queryLogAction";
}
