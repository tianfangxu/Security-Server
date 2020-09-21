package com.mot.common.constant;

import com.mot.common.annotation.LogsWriteAnnotation;

public class URLConstant {

    /**
     * @LogsWriteAnnotation 标注该注解表示该接口不需要写入访问日志到数据库
     */

    @LogsWriteAnnotation
    public static final String LOGIN_URL_01 = "login";
    @LogsWriteAnnotation
    public static final String LOGIN_URL_02 = "refreshToken";

    @LogsWriteAnnotation
    public static final String USER_URL_01 = "queryUser";
    @LogsWriteAnnotation
    public static final String USER_URL_02 = "insertUser";
    @LogsWriteAnnotation
    public static final String USER_URL_03 = "updateUser";
    @LogsWriteAnnotation
    public static final String USER_URL_04 = "deleteUser";
    @LogsWriteAnnotation
    public static final String USER_URL_05 = "batchInsertUser";
    @LogsWriteAnnotation
    public static final String USER_URL_06 = "batchUpdateUser";
    @LogsWriteAnnotation
    public static final String USER_URL_07 = "batchDeleteUser";
    @LogsWriteAnnotation
    public static final String USER_URL_08 = "resetPassword";

    @LogsWriteAnnotation
    public static final String ROLE_URL_01 = "queryRole";
    @LogsWriteAnnotation
    public static final String ROLE_URL_02 = "insertRole";
    @LogsWriteAnnotation
    public static final String ROLE_URL_03 = "queryRoleResource";
    @LogsWriteAnnotation
    public static final String ROLE_URL_04 = "batchInsertRoleResource";
    @LogsWriteAnnotation
    public static final String ROLE_URL_05 = "batchDeleteRoleResource";
    @LogsWriteAnnotation
    public static final String ROLE_URL_06 = "batchUpdateRoleResource";
    @LogsWriteAnnotation
    public static final String ROLE_URL_07 = "queryRoleByUserId";

    @LogsWriteAnnotation
    public static final String RESOURCE_URL_01 = "queryResource";
    @LogsWriteAnnotation
    public static final String RESOURCE_URL_02 = "insertResource";
    @LogsWriteAnnotation
    public static final String RESOURCE_URL_03 = "deleteResource";

    public static final String LOGACTION_URL_01 = "queryLogAction";
}
