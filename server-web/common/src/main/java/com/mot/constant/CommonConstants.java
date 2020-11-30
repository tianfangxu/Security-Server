package com.mot.constant;

public class CommonConstants {
    // ========================================================= //
    // ************************* 处理状态 *********************** //
    // ========================================================= //

    /** 处理状态：成功 */
    public static final boolean STATUS_SUCCESS = true;

    /** 处理状态：失败 */
    public static final boolean STATUS_FAILURE = false;

    /** 处理状态：警告 */
    public static final String STATUS_WARNING = "warning";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_200 = "200";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_400 = "400";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_401 = "401";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_403 = "403";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_404 = "404";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_408 = "408";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_500 = "500";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_NAME_200 = "成功";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_NAME_400 = "参数错误";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_NAME_401 = "用户凭证拒绝";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_NAME_403 = "用户无权限";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_NAME_404 = "资源不存在";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_NAME_408 = "请求超时";

    /** 网络请求状态： 200：成功；400：参数错误； 401：用户凭证拒绝；403：用户无权限；404：资源不存在；408：请求超时；500：服务器错误*/
    public static final String HTTP_STATUS_NAME_500 = "服务器错误";

    // ========================================================= //
    // ****************** 字符字 ****************** //
    // ========================================================= //

    /** 空文字列 */
    public static final String BLANK = "";

    /** 半角空格 */
    public static final String HALF_SPACE = " ";

    /** 全角空格 */
    public static final String FULL_SPACE = "　";

    // ========================================================= //
    // ************************** 符号 ************************* //
    // ========================================================= //

    /** 符号[ = ] */
    public static final String EQUAL = "=";

    /** 符号[ , ] */
    public static final String KAMA = ",";

    /** 符号[ . ] */
    public static final String POINT = ".";

    /** 符号[ : ] */
    public static final String COLON = ":";

    /** 符号[ ; ] */
    public static final String SEMI_COLON = ";";

    /** 引用符[ " ] */
    public static final String DOUBLE_QUOTES = "\"";

    /** 引用符[ ' ] */
    public static final String SINGLE_QUOTES = "'";

    /** 符号[ - ] */
    public static final String DASH = "-";

    /** 符号[ + ] */
    public static final String PLUS = "+";

    /** 日付分割符[ / ] */
    public static final String DATE_SEP = "/";

    /** 符号[ / ] / */
    public static final String SLASH = "/";

    /** 符号[ _ ] */
    public static final String UNDER_LINE = "_";

    /** 符号[ ( ] */
    public static final String LEFT_KAKO = "(";

    /** 符号[ ) ] */
    public static final String RIGHT_KAKO = ")";

    /** 符号[ ? ] */
    public static final String QUESTION_MARK = "?";

    /** 符合[ % ] */
    public static final String PERCENT = "%";

    /** 分割符号[ \t ] */
    public static final String SEPARATOR_T = "\t";

    /** 行分割符号[ mac: \r ] */
    public static final String SEPARATOR_R = "\r";

    /** 行分割符号[ linux: \n ] */
    public static final String SEPARATOR_N = "\n";

    /** 行分割符号[ windows: \r\n ] */
    public static final String SEPARATOR_RN = "\r\n";

    /** 行分割符号 */
    public static final String LINE_SEPARATOR = System
            .getProperty("line.separator");

    /** 文件行分割符号 */
    public static final String FILE_SEPARATOR = System
            .getProperty("file.separator");

    // ========================================================= //
    // ************************ 时间格式 *********************** //
    // ========================================================= //

    /** 日期格式(yyyy年MM月dd日 HH時mm分) */
    public static final String HEADER_DATE_FORMAT = "yyyy年MM月dd日 HH時mm分";

    /** 日期格式(yyyy/MM/dd HH:mm) */
    public static final String DATE_FORMAT_YEAR_MINUTE_SLASH = "yyyy/MM/dd HH:mm";

    /** 日期格式(yyyy/MM/dd HH24:mm) */
    public static final String DATE_FORMAT_YEAR_MINUTE_SLASH_DB = "yyyy/MM/dd HH24:mi";

    /** 日期格式(yyyy/MM/dd HH:mm:ss) */
    public static final String DATE_FORMAT_DATETIME_SLASH = "yyyy/MM/dd HH:mm:ss";

    /** 日期格式(yyyy/MM/dd HH24:mm:ss) */
    public static final String DATE_FORMAT_DATETIME_SLASH_DB = "yyyy/MM/dd HH24:mi:ss";

    /** 日期格式(yyyy/MM/dd HH:mm:ss.FFF) */
    public static final String DATE_FORMAT_DATETIME_FFF_SLASH = "yyyy/MM/dd HH:mm:ss.FFF";

    /** 日期格式(yyyy/MM/dd HH:mm:ss.FFF) */
    public static final String DATE_FORMAT_DATETIME_FFF_SLASH_DB = "yyyy/MM/dd HH24:mi:ss.FFF";

    /** 日期格式(yyyy/MM/dd) */
    public static final String DATE_FORMAT_DATE_SLASH = "yyyy/MM/dd";

    /** 日期格式(yyyyMMddHHmmss) */
    public static final String DATE_FORMAT_DATETIME = "yyyyMMddHHmmss";

    /** 日期格式(yyyyMMddHHmm) */
    public static final String DATE_FORMAT_DATETIMENOSS = "yyyyMMddHHmm";

    /** 日期格式(yyyyMMddHHmmss) */
    public static final String DATE_FORMAT_DATETIME_FFF = "yyyyMMddHHmmssFFF";

    /** 日期格式(yyyyMMddHHmmssSSSSSS) */
    public static final String DATE_FORMAT_DATETIME_SSS = "yyyyMMddHHmmssSSSSSS";

    /** 日期格式(yyyyMMddHHmmssFFFFFF) */
    public static final String DATE_FORMAT_DATETIME_FFFFFF = "yyyymmddhh24missff6";

    /** 日期格式(yyyymmddhh24miss) */
    public static final String DATE_FORMAT_DATETIME_HH24 = "yyyymmddhh24miss";

    /** 日期格式(yyMMddHHmm) */
    public static final String DATE_FORMAT_SHORT_YEAR = "yyMMddHHmm";

    /** 日期格式(yy/MM/dd HH:mm) */
    public static final String DATE_FORMAT_SHORT_YEAR_HOUR = "yy/MM/dd HH:mm";

    /** 日期格式(yy/MM/dd HH:mm) */
    public static final String DATE_FORMAT_SHORT_YEAR_HOUR_DB = "yy/MM/dd HH24:mi";

    /** 日期格式(yyyyMMddHH) */
    public static final String DATE_FORMAT_DATETIME_HOUR = "yyyyMMddHH";

    /** 日期格式(yyyy/MM/dd HH) */
    public static final String DATE_FORMAT_DATETIME_HOUR_SLASH = "yyyy/MM/dd HH";

    /** 日期格式(yyyy/MM/dd HH) */
    public static final String DATE_FORMAT_DATETIME_HOUR_SLASH_DB = "yyyy/MM/dd HH24";

    /** 日期格式(yyMMddHH) */
    public static final String DATE_FORMAT_SHORT_DATE_HOUR = "yyMMddHH";

    /** 日期格式(yy/MM/dd HH) */
    public static final String DATE_FORMAT_SHORT_DATE_HOUR_SLASH = "yy/MM/dd HH";

    /** 日期格式(yy/MM/dd HH) */
    public static final String DATE_FORMAT_SHORT_DATE_HOUR_SLASH_DB = "yy/MM/dd HH24";

    /** 日期格式(yyyy-MM-dd HH:mm:ss) */
    public static final String DATE_FORMAT_DATETIME_HYPHEN = "yyyy-MM-dd HH:mm:ss";

    /** 日期格式(yyyy-MM-dd HH:mm:ss) */
    public static final String DATE_FORMAT_DATETIME_HYPHEN_F9 = "yyyy-mm-dd hh24:mi:ss.ff9";

    /** 日期格式(yyyy-MM-dd HH:mm:ss) */
    public static final String DATE_FORMAT_DATETIME_HYPHEN_F6 = "yyyy-mm-dd hh24:mi:ss.ff6";

    /** 日期格式(dd-MMM-yyyy HH:mm:ss.SS) */
    public static final String DATE_FORMAT_DATETIME_ENGISH_F2 = "dd-MMM-yyyy HH:mm:ss.SS";

    /** 日期格式(yyMMdd) */
    public static final String DATE_FORMAT_SHORT_DATE = "yyMMdd";

    /** 日期格式(yy/MM/dd) */
    public static final String DATE_FORMAT_SHORT_DATE_SLASH = "yy/MM/dd";

    /** 日期格式(yyyy/MM) */
    public static final String DATE_FORMAT_DATE_YM_SLASH = "yyyy/MM";

    /** 日期格式(yyyyMMdd) */
    public static final String DATE_FORMAT_DATE_NO_SLASH = "yyyyMMdd";

    /** 日期格式(yyyy年MM月dd日) */
    public static final String DATE_FORMAT_DATE_NO_KANJI = "yyyy年MM月dd日";

    /** 日期格式(yyyyMMdd) */
    public static final String DATE_DEFAULT_FORMAT = "yyyyMMdd";

    /** 日期格式(yyMMdd) */
    public static final String DATE_SHORT_FORMAT = "yyMMdd";

    /** 日期格式(yyyy/MM/dd) */
    public static final String DATE_SLASH_FORMAT = "yyyy/MM/dd";

    /** 日期格式(yyyyMM) */
    public static final String DEFAULT_YM_FORMAT = "yyyyMM";

    /** 日期格式(yyyy/MM) */
    public static final String SLASH_YM_FORMAT = "yyyy/MM";

    /** Date日期格式(yyyy-MM-dd) */
    public static final String DB_DATE_FORMAT = "yyyy-MM-dd";

    /** Date日期格式(yyyy-MM) */
    public static final String DB_DATE_FORMAT_YM = "yyyy-MM";

    /** Timestamp日期格式(yyyy-MM-dd-HH.mm.ss.SSS) */
    public static final String DB_TIMESTAMP_FORMAT = "yyyy-MM-dd-HH.mm.ss.SSS";

    /** 常用Timestamp日期格式(yyyy-MM-dd HH:mm:ss.SSS) */
    public static final String TIMESTAMP_NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /** 时间格式(HHmmss) */
    public static final String TIME_DEFAULT_FORMAT = "HHmmss";

    /** 时间(HH:mm:ss) */
    public static final String COLON_TIME_FORMAT = "HH:mm:ss";

    /** 时间(HHmm) */
    public static final String DEFAULT_MINUTE_FORMAT = "HHmm";

    /** 时间(HH:mm) */
    public static final String COLON_MINUTE_FORMAT = "HH:mm";

    /** 日期格式(yyyy-MM-dd HH:mm) */
    public static final String DATE_FORMAT_YEAR_MINUTE = "yyyy-MM-dd HH:mm";


    // ========================================================= //
    // ************************ 表字段代码常量 ************************** //
    // ========================================================= //

    /** 0：未上传；1：已上传；2：上传错误 */
    public static final String ISPACKAGE_0="0";

    /** 0：未上传；1：已上传；2：上传错误 */
    public static final String ISPACKAGE_1="1";

    /** 0：未上传；1：已上传；2：上传错误 */
    public static final String ISPACKAGE_2="2";

    /** 0：登录；1：查询；2：新增；3：修改；4：删除 */
    public static final String TYPE_0="0";

    /** 0：登录；1：查询；2：新增；3：修改；4：删除 */
    public static final String TYPE_1="1";

    /** 0：登录；1：查询；2：新增；3：修改；4：删除 */
    public static final String TYPE_2="2";

    /** 0：登录；1：查询；2：新增；3：修改；4：删除 */
    public static final String TYPE_3="3";

    /** 0：登录；1：查询；2：新增；3：修改；4：删除 */
    public static final String TYPE_4="4";

    /** 0：登录；1：查询；2：新增；3：修改；4：删除 */
    public static final String TYPE_NAME_0="登录";

    /** 0：登录；1：查询；2：新增；3：修改；4：删除 */
    public static final String TYPE_NAME_1="查询";

    /** 0：登录；1：查询；2：新增；3：修改；4：删除 */
    public static final String TYPE_NAME_2="新增";

    /** 0：登录；1：查询；2：新增；3：修改；4：删除 */
    public static final String TYPE_NAME_3="修改";

    /** 0：登录；1：查询；2：新增；3：修改；4：删除 */
    public static final String TYPE_NAME_4="删除";

    /** 1：成功；0：失败*/
    public static final String OPERATE_RESULT_0="0";

    /** 1：成功；0：失败*/
    public static final String OPERATE_RESULT_1="1";

    /** 1：成功；0：失败*/
    public static final String OPERATE_RESULT_NAME_0="失败";

    /** 1：成功；0：失败*/
    public static final String OPERATE_RESULT_NAME_1="成功";

    /** 0：未删除；1：删除 */
    public static final String DEL_FLAG_0="0";

    /** 0：未删除；1：删除 */
    public static final String DEL_FLAG_1="1";

    /** 0：未读；1：已读*/
    public static final String READED_0="0";
    /** 0：未读；1：已读*/
    public static final String READED_1="1";
}
