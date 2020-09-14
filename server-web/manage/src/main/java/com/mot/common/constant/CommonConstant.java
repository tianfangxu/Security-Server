package com.mot.common.constant;

public class CommonConstant {

    /***************************返回值状态描述*********************************/
    public static final int RESULT_SUCCESS_STATE = 200;        //成功
    public static final int RESULT_EMPTY_STATE = 404;        //没有资源
    public static final int RESULT_FAILURE_STATE = 403;        //没有权限
    public static final int RESULT_ERROR_STATE = 500;            //系统错误

    /****************************数据字典项**********************************/
    public static final int BASE_DATA_FLAG_EXIT = 1;        //存在
    public static final int BASE_DATA_FLAG_NO_EXIT = 0;     //不存在
    public static final int BASE_DATA_LOCKED_TRUE = 1;      //锁定
    public static final int BASE_DATA_LOCKED_FALSE = 0;     //未锁定
    public static final int BASE_DATA_SEX_MALE = 1;         //   男
    public static final int BASE_DATA_SEX_FEMALE = 0;       //   女
    public static final int BASE_DATA_LEAF_YES = 1;         //   叶子节点
    public static final int BASE_DATA_LEAF_NO = 0;          //   非叶子节点
    public static final int BASE_DATA_RESORCE_TYPE_PAGE = 1;       //   页面
    public static final int BASE_DATA_RESORCE_TYPE_METHOD = 2;       //   后台访问的方法
}
