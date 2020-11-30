package com.mot.utils;
import com.mot.constant.CommonConstants;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    // ========================================================= //
    // ******************** 常用的判定方法 *********************** //
    // ========================================================= //

    /**
     * <p>
     * 字符串格式判断。<br>
     * 判断输入的字符串是否符合输入的正则表达式。
     * </p>
     *
     * @param strVal
     *            字符串
     * @param regex
     *            正则表达式
     * @return True:符合
     *
     */
    public static boolean chkRegex(String strVal, String regex) {

        if (CommonUtil.isEmpty(strVal) || CommonUtil.isEmpty(regex)) {
            return false;
        }

        return Pattern.matches(regex, strVal);
    }

    /**
     * <p>
     * 半角数字判断。<br>
     * 判断输入的字符串是否是半角数字，可以带【+/-】号。
     * </p>
     *
     * @param strVal
     *            字符串
     * @return True:半角数字
     */
    public static boolean chkNumeric(String strVal) {

        return chkRegex(strVal, "[+-]?\\d*");
    }

    /**
     * 电话格式判断
     *
     * @param strVal
     *            字符串
     * @return true:电话号码
     */
    public static boolean chkPhone(String strVal) {
        return chkRegex(strVal, "1[3|4|5|7|8|9]\\d{9}");
    }

    /**
     * 电子邮箱格式判断
     *
     * @param strVal
     *            字符串
     * @return true:电子邮箱
     */
    public static boolean chkMail(String strVal) {
        return chkRegex(strVal, "(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)");
    }

    /**
     * 浮点型数字判断
     *
     * @param strVal
     *            字符串
     * @return true:浮点型数字
     */
    public static boolean chkDouble(String strVal) {
        return chkRegex(strVal, "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$");
    }

    /**
     * 浮点型数字判断
     *
     * @param strVal
     *            字符串
     * @return true:浮点型数字
     */
    public static boolean chkYmd(String strVal) {
        return chkRegex(strVal, "((20[0-9]{2})-((0[0-9])|(1[0-2])))");
    }

    /**
     * 日期判断
     *
     * @param strVal
     *            字符串
     * @return true:日期
     */
    public static boolean chkDate(String strVal) {
        return chkRegex(
                strVal,
                "(((20[1-9]{2})-(0[13-9]|1[0-2])-([0-2]{1}[1-9]|30|10|20))|((20[0-9]{2})-(0[13578]|1[02])-31)|((20[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))|(((20([13579][26]|[2468][048]|0[48]))|((2000)-02))-29))");
    }

    /**
     * 日期时间判断
     *
     * @param strVal
     *            字符串
     * @return true:日期时间
     */
    public static boolean chkDateTime(String strVal) {
        return chkRegex(
                strVal,
                "((((20[1-9]{2})-(0[13-9]|1[0-2])-([0-2]{1}[1-9]|30|10|20))|((20[0-9]{2})-(0[13578]|1[02])-31)|((20[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))|(((20([13579][26]|[2468][048]|0[48]))|((2000)-02))-29)) (([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])))");
    }

    /**
     * 日期时间判断
     *
     * @param strVal
     *            字符串
     * @return true:日期时间
     */
    public static boolean chkTime(String strVal) {
        return chkRegex(strVal, " (([01][0-9]|2[0-3]):([0-5][0-9]))");
    }

    /**
     * <p>
     * 空白字符串判断。<br>
     * 判断时，会去除首尾的空格。
     * </p>
     *
     * @param strVal
     *            字符串
     * @return True:空白字符串
     */
    public static boolean isEmpty(String strVal) {
        return (null == strVal) || (0 == strVal.trim().length());
    }

    /**
     * <p>
     * 全角文字判断。
     * </p>
     *
     * @param strVal
     *            字符串
     * @return True:全角文字
     */
    public static boolean isFullSize(String strVal) {
        byte[] bytes = strVal.getBytes();
        int beams = strVal.length() / 2;
        return (beams == bytes.length);
    }

    /**
     * <p>
     * 半角英字判断。
     * </p>
     *
     * @param strVal
     *            字符串
     * @return True:半角英字
     */
    public static boolean isHalfAlpha(String strVal) {

        return chkRegex(strVal, "^[a-zA-Z]*$");
    }

    /**
     * <p>
     * 半角英数字判断。
     * </p>
     *
     * @param strVal
     *            字符串
     * @return True:半角英数字
     */
    public static boolean isHalfAlphaNumeric(String strVal) {

        return chkRegex(strVal, "^[a-zA-Z0-9]*$");
    }

    /**
     * <p>
     * 半角数字判断。
     * </p>
     *
     * @param strVal
     *            字符串
     * @return True:半角数字
     */
    public static boolean isHalfDigit(String strVal) {

        return chkRegex(strVal, "\\d*");
    }

    /**
     * <p>
     * 半角文字判断。
     * </p>
     *
     * @param strVal
     *            字符串
     * @return True：半角文字
     */
    public static boolean isHalfSize(String strVal) {

        byte[] bytes = strVal.getBytes();
        int beams = strVal.length();
        return (beams == bytes.length);
    }

    /**
     * <p>
     * 半角文字判断。
     * </p>
     *
     * @param strVal
     *            字符串
     * @return True：半角文字
     */
    public static boolean isHalfString(String strVal) {

        return chkRegex(strVal, "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$");
    }

    /**
     * 对象是否非Null的判断。<br>
     * <br>
     * 检查对象非Null时、Ture返回。<br>
     * 检查对象是以下情况时，也作为非Null。
     * <UL>
     * <LI>字符串长度不是0。
     * <LI>字符串不是空白字符串。
     * <LI>引用对象数组长度大于0。
     * <LI>集合元素数大于0。
     * </UL>
     *
     * @param pobjObject
     *            检查对象
     * @return 检查结果：
     *         <table>
     *         <tr>
     *         <td>True</td>
     *         <td>非Null。</td>
     *         </tr>
     *         <tr>
     *         <td>False</td>
     *         <td>Null。</td>
     *         </tr>
     *         </table>
     */
    public static boolean isNotNull(Object pobjObject) {
        return !CommonUtil.isNull(pobjObject);
    }

    /**
     * 对象是否Null的判断。<br>
     * <br>
     * 检查的对象是Null时，True返回。<br>
     * 检查的对象是以下情况时，也作为Null判定。
     * <UL>
     * <LI>字符串长度为0。
     * <LI>字符串为空白字符串。
     * <LI>引用对象数组长度为0。
     * <LI>集合元素数为0。
     * </UL>
     *
     * @param pobjObject
     *            检查对象
     * @return 检查结果：
     *         <table>
     *         <tr>
     *         <td>True</td>
     *         <td>Null。</td>
     *         </tr>
     *         <tr>
     *         <td>False</td>
     *         <td>非Null。</td>
     *         </tr>
     *         </table>
     */
    public static boolean isNull(Object pobjObject) {

        if (pobjObject == null) {
            return true;
        } else if (pobjObject instanceof String) {

            String str = (String) pobjObject;
            if (str.trim().length() == 0) {
                return true;
            }
        } else if (pobjObject instanceof Object[]) {

            Object[] objAry = (Object[]) pobjObject;
            if (objAry.length == 0) {
                return true;
            }
        } else if (pobjObject instanceof List<?>) {
            List<?> lst = (List<?>) pobjObject;
            if (lst.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    // ========================================================= //
    // ****************** 字符串的相关处理方法 ******************** //
    // ========================================================= //

    /**
     * 集合中全部元素拼接成字符串。<br>
     * <br>
     * 字符串拼接时，每个元素间拼接【,】字符。<br>
     * <br>
     * 集合中Null值的元素不拼接，元素数为0的场合，空白返回。
     *
     * @param plstList
     *            拼接的集合
     * @return 拼接后的字符串
     */
    public static String concatList(List<?> plstList) {
        return CommonUtil.concatList(plstList, CommonConstants.KAMA);
    }

    /**
     * 按指定段落字符串拼接集合中全部元素。<br>
     * <br>
     * 字符串拼接时，每个元素间拼接指定段落字符串。<br>
     * <br>
     * 集合中Null值的元素不拼接，元素数为0的场合，空白返回。
     *
     * @param plstList
     *            拼接的集合
     * @param pstrSep
     *            段落字符串
     * @return 拼接后的字符串
     */
    public static String concatList(List<?> plstList, String pstrSep) {

        int intMax = plstList.size(); // 元素数

        if (intMax <= 0) { // 元素数为0的场合
            return CommonConstants.BLANK;
        }

        StringBuffer buf = new StringBuffer(CommonConstants.BLANK);
        String str1Data;

        // 第一个元素取出
        str1Data = (String) CommonUtil.getListElement(plstList, 0);
        if (str1Data != null) {
            buf.append(str1Data);
        }

        // 第二个及以下元素取出
        // 各个元素间拼接上指定段落字符串
        for (int i = 1; i < intMax; i++) {
            buf.append(pstrSep);
            str1Data = (String) CommonUtil.getListElement(plstList, i);
            if (str1Data != null) {
                buf.append(str1Data);
            }
        }

        return buf.toString();
    }

    /**
     * <p>
     * 字符串链接。 <br>
     * 将指定数组中的字符串拼接，如果字符串是Null，则不拼接。
     * </p>
     *
     * @param strings
     *            字符串数组
     * @return 链接完的字符串
     */
    public static String concatStr(String... strings) {

        StringBuilder buf = new StringBuilder();

        for (String str : strings) {
            if (str != null) {
                buf.append(str);
            }
        }

        if (buf.length() == 0) {
            return null;
        }

        return buf.toString();
    }

    /**
     * 数组中全部元素拼接成字符串。<br>
     * <br>
     * 字符串拼接时，每个元素间拼接【,】字符。<br>
     * <br>
     * 数组中Null的元素不拼接，数组元素数为0的场合，空白返回。
     *
     * @param pstrStrings
     *            拼接的字符串数组
     * @return 拼接后字符串
     */
    public static String concatString(String[] pstrStrings) {
        return CommonUtil.concatString(pstrStrings, CommonConstants.KAMA);
    }

    /**
     * 按指定段落字符串拼接数组中的全部元素。<br>
     * <br>
     * 字符串拼接时，每个元素间拼接指定段落字符串。<br>
     * <br>
     * 数组中Null的元素不拼接，数组元素数为0的场合，空白返回。
     *
     * @param pstrStrings
     *            拼接的字符串数组
     * @param pstrSep
     *            指定段落字符串
     * @return 拼接后字符串
     */
    public static String concatString(String[] pstrStrings, String pstrSep) {

        int intMax = pstrStrings.length; // 元素数

        if (intMax <= 0) { // 数组元素数0的场合
            return CommonConstants.BLANK;
        }

        StringBuffer buf = new StringBuffer(CommonConstants.BLANK);
        if (pstrStrings[0] != null) {
            buf.append(pstrStrings[0]);
        }

        for (int i = 1; i < intMax; i++) {
            buf.append(pstrSep);
            if (pstrStrings[i] != null) {
                buf.append(pstrStrings[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <p>
     * 字符串第一位字符切除。<br>
     * 字符串第一位字符为指定字符时切除。
     * </p>
     *
     * @param input
     *            字符串
     * @param deleteChar
     *            指定字符
     * @return 切除后字符串
     */
    public static String deleteFirstChar(String input, String deleteChar) {

        if ((input == null) || (input.length() == 0)
                || !input.startsWith(deleteChar)) {
            return input;
        }

        return input.substring(1);
    }

    /**
     * <p>
     * 字符串第一位字符切除。
     * </p>
     *
     * @param input
     *            字符串
     * @return 切除后字符串
     */
    public static String deleteFirstChar(String input) {

        if ((input == null) || (input.length() == 0)) {
            return input;
        }

        return input.substring(1);
    }

    /**
     * <p>
     * 空白字符串Null转换。
     * </p>
     *
     * @param pstrString
     *            字符串
     * @return 转换后字符串
     */
    public static String emptyToNull(String pstrString) {

        if (CommonUtil.isEmpty(pstrString)) {
            return null;
        }

        return pstrString;
    }

    /**
     * <p>
     * 指定字符串比较。
     * </p>
     * <p>
     * 比较指定字符串是否相等。
     * </p>
     *
     * @param str1
     *            字符串1
     * @param str2
     *            字符串2
     * @return 字符串相等时：True (双方都为Null时：True)
     */
    public static boolean isSameString(String str1, String str2) {

        if (str1 != null) {
            return str1.equals(str2);
        } else if (str2 == null) {
            return true;
        }

        return false;
    }

    /**
     * <p>
     * 字符串左侧截取。<br>
     * 截取字符串左侧指定长度的子字符串。
     * </p>
     *
     * @param inputStr
     *            字符串
     * @param len
     *            长度
     * @return 子字符串
     */
    public static String leftStr(String inputStr, int len) {

        if (CommonUtil.isEmpty(inputStr) || inputStr.length() <= len) {
            return inputStr;
        }

        return inputStr.substring(0, len);
    }

    /**
     * <p>
     * Null字符串空白转换。
     * </p>
     *
     * @param pstrString
     *            字符串
     * @return 转换后字符串
     */
    public static String nullToEmpty(String pstrString) {

        if (CommonUtil.isEmpty(pstrString)) {
            return CommonConstants.BLANK;
        }

        return pstrString;
    }

    /**
     * <p>
     * 字符串数组中重复元素去除。
     * </p>
     *
     * @param array
     *            字符串数组
     * @return 处理后的数组
     */
    public static String[] removeSameStr(String[] array) {

        if (CommonUtil.isNull(array)) {
            return array;
        }

        List<String> tempList = new ArrayList<String>();

        for (String strVal : array) {

            if (!tempList.contains(strVal)) {
                tempList.add(strVal);
            }
        }

        return tempList.toArray(new String[tempList.size()]);
    }

    /**
     * <p>
     * 字符串右侧截取。<br>
     * 截取字符串右侧指定长度的子字符串。
     * </p>
     *
     * @param inputStr
     *            字符串
     * @param len
     *            长度
     * @return 子字符串
     */
    public static String rightStr(String inputStr, int len) {

        if (CommonUtil.isEmpty(inputStr) || inputStr.length() <= len) {
            return inputStr;
        }

        return inputStr.substring(inputStr.length() - len);
    }

    /**
     * 按","分割字符串为数组。<br>
     * <br>
     * 字符串Null时<b>数组的元素数为0。
     *
     * @param pstrString
     *            分割字符串
     * @return 分割后取得的数组
     */
    public static String[] splitStringToArray(String pstrString) {

        if (CommonUtil.isEmpty(pstrString)) {
            return new String[0];
        }

        return pstrString.split(CommonConstants.KAMA);
    }

    /**
     * 按","分割字符串为集合。<br>
     * <br>
     * 字符串Null时<b>集合的元素数为0。
     *
     * @param pstrString
     *            分割字符串
     * @return 分割后取得的集合
     */
    public static List<String> splitStringToList(String pstrString) {

        return CommonUtil.splitStringToList(pstrString, CommonConstants.KAMA);
    }

    /**
     * 按指定段落字符分割字符串为集合。<br>
     * <br>
     * 分割字符串或者指定段落字符是Null的场合，<b>集合的元素数为1，值是Null</b>。
     *
     * @param pstrString
     *            分割字符串
     * @param pstrSep
     *            指定段落字符
     * @return 分割后取得的集合
     */
    public static List<String> splitStringToList(String pstrString,
                                                 String pstrSep) {

        if (pstrString == null || pstrSep == null) {
            return new ArrayList<String>();
        }

        return new ArrayList<String>(Arrays.asList(pstrString.split(pstrSep)));
    }

    /**
     * <p>
     * 字符串Trim处理。<br>
     * 字符串前后的全半角空格去除(以\x0000 - \x0020 String#trim为基准)。
     * </p>
     *
     * @param value
     *            字符串
     * @return Trim后的字符串
     */
    public static String trim(String value) {
        if (value == null) {
            return null;
        }
        return value.replaceAll("\\A[\\u0000-\\u0020　]+", "").replaceAll(
                "[\\u0000-\\u0020　]+\\z", "");
    }

    /**
     * 字符串Trim处理。<br>
     * 字符串前后的全角空格去除(以\x0000 - \x0020 String#trim为基准)。<br>
     * ※字符串前后空行去除。 <br>
     * 字符串前空行→去除、字符串中空行→不去除的场合
     * ToolUtil.trim(ToolUtil.trimMulti(value,false))这样使用。<br>
     * 字符串前空行→去除、字符串中空行→去除的场合 ToolUtil.trimMulti(value,true)这样使用。<br>
     * 字符串前空行→不去除、字符串中空行→不去除的场合 ToolUtil.trimMulti(value,false)这样使用。<br>
     *
     * @param value
     *            字符串
     * @param removeBlankLine
     *            true： 空行去除,false: 空行不去除。
     * @return Trim后的字符串
     */
    public static String trimMulti(String value, boolean removeBlankLine) {
        if (value == null) {
            return null;
        }
        String excludeLineFeed = "&&[^\\n\\r]";
        if (removeBlankLine) {
            excludeLineFeed = "";
        }
        return value.replaceAll(
                "(?m)^[\\u0000-\\u0020　" + excludeLineFeed + "]+", "")
                .replaceAll("(?m)[\\u0000-\\u0020　" + excludeLineFeed + "]+$",
                        "");
    }

    /**
     * 字符串Trim处理。<br>
     * 去除字符串中的换行符(\r\n、\r、\n)。<br>
     *
     * @param value
     *            字符串
     * @return 处理后的字符串
     */
    public static String trimNewline(String value) {

        Pattern pattern = Pattern.compile("\\|\t|\r|\n|\r\n");
        Matcher matcher = pattern.matcher(value);

        return matcher.replaceAll("");
    }

    /**
     * 集合中指定位置的元素取得。<br>
     *
     * @param plstList
     *            集合
     * @param pintIndex
     *            指定位置
     * @return 指定元素
     */
    private static Object getListElement(List<?> plstList, int pintIndex) {
        Object objData = plstList.get(pintIndex);
        if (objData == null) {
            return null;
        }
        return objData.toString();
    }

    // ========================================================= //
    // ****************** 日期型的相关处理方法 ******************** //
    // ========================================================= //

    /**
     * <p>
     * 日期的日子变换。
     * </p>
     *
     * @param date
     *            日期
     * @param format
     *            输入日期的格式
     * @param day
     *            变化值(可以为负值)
     * @return 变换后的日期
     */
    public static String addDay(String date, String format, int day) {
        return addDate(date, format, day, 3);
    }

    /**
     * <p>
     * 日期的月份变换。
     * </p>
     *
     * @param date
     *            日期
     * @param format
     *            输入日期的格式
     * @return 变换后的日期
     */
    public static String addMonth(String date, String format, int month) {
        return addDate(date, format, month, 2);
    }

    /**
     * <p>
     * 日期的年份变换。
     * </p>
     *
     * @param date
     *            日期
     * @param format
     *            输入日期的格式
     * @return 变换后的日期
     */
    public static String addYear(String date, String format, int year) {
        return addDate(date, format, year, 1);
    }

    /**
     * <p>
     * 日期格式转换。
     * </p>
     *
     * @param dateValue
     *            日期
     * @param srcFormat
     *            输入日期的格式
     * @param outFormat
     *            输出日期的格式
     * @return 转换后的日期
     */
    public static String convertDate(String dateValue, String srcFormat,
                                     String outFormat) {

        if (dateValue == null || outFormat == null
                || outFormat.equals(srcFormat)) {
            return dateValue;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setLenient(false);
        boolean existsMicroSec = false;
        String microSec = null;

        if (srcFormat != null) {
            existsMicroSec = srcFormat.endsWith("SSSSSS");
            if (existsMicroSec && dateValue.matches(".*[\\d]{6,6}$")) {
                microSec = dateValue.substring(dateValue.length() - 3);
                dateValue = dateValue.substring(0, dateValue.length() - 3);
            }
            simpleDateFormat.applyPattern(srcFormat);
        }

        ParsePosition pos = new ParsePosition(0);
        Date date = simpleDateFormat.parse(dateValue, pos);

        if (date == null || dateValue.length() != pos.getIndex()) {
            return dateValue;
        }

        boolean existsMicroSecOut = outFormat.endsWith("SSSSSS");

        if (existsMicroSecOut) {
            outFormat = outFormat.replace("SSSSSS", "SSS");
        }

        simpleDateFormat.applyPattern(outFormat);
        String value = simpleDateFormat.format(date);

        if (microSec != null && existsMicroSecOut) {
            value = (new StringBuilder()).append(value).append(microSec)
                    .toString();
        }

        return value;
    }

    /**
     * <p>
     * 时间格式化処理。<br>
     * 时间格式没有指定的场合，默认使用(yyyyMMdd)格式。
     * </p>
     *
     * @param date
     *            时间値
     * @param format
     *            输出字符串的时间格式
     * @return 格式化后的时间
     */
    public static String formatDate(Date date, String format) {

        if (date == null) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        if (format != null) {
            simpleDateFormat.applyPattern(format);
        } else {
            simpleDateFormat.applyPattern(CommonConstants.DATE_DEFAULT_FORMAT);
        }

        simpleDateFormat.setLenient(false);
        return simpleDateFormat.format(date);
    }

    /**
     * <p>
     * 系统时间（yyyy-MM-dd HH:mm:ss）取得。
     * </p>
     *
     * @return 系统时间（yyyy-MM-dd HH:mm:ss）
     */
    public static String getCurrentDate() {
        return CommonUtil.formatDate(new Date(),
                CommonConstants.DATE_FORMAT_DATETIME_HYPHEN);
    }

    /**
     * <p>
     * 某月的最后一天取得。
     * </p>
     *
     * @param year
     *            年
     * @param month
     *            月
     * @return 月的最后一天
     */
    public static String getLastDay(String year, String month) {

        Calendar cal = new GregorianCalendar(Integer.valueOf(year).intValue(),
                Integer.valueOf(month).intValue(), 1);
        cal.add(Calendar.MONTH, -1);

        return String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    /**
     * <p>
     * 系统时间（yyyy-MM-dd）取得。
     * </p>
     *
     * @return 系统时间（yyyy-MM-dd）
     */
    public static String getSysTime4Db() {
        return CommonUtil.formatDate(new Date(), CommonConstants.DB_DATE_FORMAT);
    }

    /**
     * <p>
     * 系统时间（yyyyMMdd）取得。
     * </p>
     *
     * @return 系统时间（yyyyMMdd）
     */
    public static String getSysTime4Def() {
        return CommonUtil.formatDate(new Date(), CommonConstants.DATE_DEFAULT_FORMAT);
    }

    /**
     * <p>
     * 系统时间（yyyy/MM/dd）取得。
     * </p>
     *
     * @return 系统时间（yyyy/MM/dd）
     */
    public static String getSysTime4Slash() {
        return CommonUtil.formatDate(new Date(), CommonConstants.DATE_SLASH_FORMAT);
    }

    /**
     * <p>
     * 时间格式化処理。<br>
     * 时间格式没有指定的场合，默认使用(yyyy-MM-dd)格式。
     * </p>
     *
     * @param format
     *            输入字符串的时间格式
     * @return 格式化后的时间
     */
    public static Date parseToDate(String dateValue, String format) {

        if (dateValue == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        if (format != null) {
            simpleDateFormat.applyPattern(format);
        } else {
            simpleDateFormat.applyPattern(CommonConstants.DB_DATE_FORMAT);
        }

        simpleDateFormat.setLenient(false);
        ParsePosition pos = new ParsePosition(0);
        Date dt = simpleDateFormat.parse(dateValue, pos);

        // if (pos.getIndex() == dateValue.length()) {
        return dt;
        // } else {
        // return null;
        // }
    }

    /**
     * <p>
     * 日期的变换处理。
     * </p>
     *
     * @param strDate
     *            日期
     * @param strFormat
     *            输入日期的格式
     * @param number
     *            变化值(可以为负值)
     * @param pattern
     *            变化区分(1：年,2:月,3:日)
     * @return 变换后的日期
     */
    private static String addDate(String strDate, String strFormat, int number,
                                  int pattern) {

        Date date = CommonUtil.parseToDate(strDate, strFormat);

        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.setTime(date);

        if (pattern == 1) {
            calendar.add(1, number);
        } else {

            if (pattern == 2) {
                calendar.add(2, number);
            } else {
                calendar.add(5, number);
            }
        }

        date = new Date(calendar.getTimeInMillis());

        return formatDate(date, strFormat);
    }

    // ========================================================= //
    // ****************** 数字型的相关处理方法 ******************** //
    // ========================================================= //

    /**
     * <p>
     * BigDecimal转换。<br>
     * 将指定字符串转换成BigDecimal。
     * </p>
     *
     * @param value
     *            转换值
     * @return 转换后值
     */
    public static BigDecimal parseBigDecimal(String value) {

        // 字符串非空场合
        if (!CommonUtil.isEmpty(value)) {
            return new BigDecimal(value.trim());
        }

        return null;
    }

    /**
     * <p>
     * BigDecimal转换。<br>
     * 将指定字符串转换成BigDecimal。
     * </p>
     *
     * @param value
     *            转换值
     * @param defaultValue
     *            默认值(转换值空白时用)
     * @return 转换后值
     */
    public static BigDecimal parseBigDecimal(String value,
                                             BigDecimal defaultValue) {

        // 字符串非空场合
        if (!CommonUtil.isEmpty(value)) {
            return new BigDecimal(value.trim());
        }

        return defaultValue;
    }

    /**
     * <p>
     * 数字の先頭をカットする。
     * </p>
     * <p>
     * 例：<br>
     * <LI>cutHead(12345.67, 10000) → 2345.67
     * </p>
     *
     * @param value
     *            数值
     * @param extremity
     *            极限值
     * @return 转换后的数值
     */
    public static BigDecimal cutHead(BigDecimal value, long extremity) {
        return value.add(new BigDecimal(
                ((value.longValue() / extremity) * extremity)).negate());
    }

    /**
     * <p>
     * 数值首位数字切除。
     * </p>
     * <p>
     * 例：123.456 → 23.456、-789.123 → -89.123
     * </p>
     *
     * @param number
     *            数值
     * @return 切除后数值
     */
    public static String deleteFirstNum(String number) {

        if (!CommonUtil.chkNumeric(number)) {
            return number;
        }

        boolean sign = false;
        BigDecimal bigDecimal = new BigDecimal(number);
        if (bigDecimal.longValue() < 0) {
            sign = true;
        }

        String result = bigDecimal.abs().toString().substring(1);
        if (sign) {
            result = "-" + result;
        }

        return result;
    }

    /**
     * <p>
     * 指定数字格式化。
     * </p>
     * <p>
     * 例：<br>
     * <LI>"#.###"格式的场合<br>
     * 123456.1235 → 123456.124、123456.0 → 123456、-123456.1236 → -123456.124
     * <LI>"#,###.###"格式的场合<br>
     * 1234567.1235 → 1,234,567.123、1234567.0 → 1,234,567、-1234567.1235 →
     * -1,234,567.124
     * <LI>"#,###.000"格式的场合<br>
     * 1234567.1235 → 1,234,567.123、1234567.0 → 1,234,567.000、-1234567.1235 →
     * -1,234,567.124
     * </p>
     *
     * @param dblValue
     *            数值
     * @param format
     *            格式
     * @return 格式化之后的数值
     */
    public static String format(double dblValue, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(dblValue);
    }

    /**
     * <p>
     * 指定数字格式化。
     * </p>
     * <p>
     * 例：<br>
     * <LI>"#.###"格式的场合<br>
     * 123456.1235 → 123456.124、123456.0 → 123456、-123456.1236 → -123456.124
     * <LI>"#,###.###"格式的场合<br>
     * 1234567.1235 → 1,234,567.123、1234567.0 → 1,234,567、-1234567.1235 →
     * -1,234,567.124
     * <LI>"#,###.000"格式的场合<br>
     * 1234567.1235 → 1,234,567.123、1234567.0 → 1,234,567.000、-1234567.1235 →
     * -1,234,567.124
     * </p>
     *
     * @param format
     *            格式
     * @return 格式化之后的数值
     */
    public static String format(String stringValue, String format) {
        DecimalFormat df = new DecimalFormat(format);
        BigDecimal inputValue = new BigDecimal(stringValue);
        return df.format(inputValue);
    }

    /**
     * <p>
     * 数值整数部分位数取得。
     * </p>
     * <p>
     * 例： "000999.99" → 3、"-12345.987" → 5、null → 0
     * </p>
     *
     * @param dblValue
     *            数值
     * @return 整数部分位数 (dblValue是空白的场合：0)
     */
    public static int getLongLength(String dblValue) {

        if (!CommonUtil.chkNumeric(dblValue)) {
            return 0;
        }

        BigDecimal bigDecimal = new BigDecimal(dblValue).setScale(0,
                BigDecimal.ROUND_DOWN);

        return bigDecimal.abs().toString().length();
    }

    /**
     * <p>
     * 数值整数部分最大位数切取。<br>
     * 按指定的整数部分最大位数切取数字。
     * </p>
     * <p>
     * 例：maxLenOfLong为【3】的场合<br>
     * 1234.99 → 234.99、-12345.987 → -345.987、null → null
     * </p>
     *
     * @param number
     *            数值
     * @param maxLenOfLong
     *            整数倍最大位数
     * @return 切取后数值
     */
    public static String getNumByMaxLen(String number, int maxLenOfLong) {

        if (!CommonUtil.chkNumeric(number)) {
            return number;
        }

        BigDecimal bdValue = new BigDecimal(number);

        if (bdValue.abs().setScale(0, BigDecimal.ROUND_DOWN).toString()
                .length() <= maxLenOfLong) {
            return bdValue.toString();
        }

        long div = BigDecimal.TEN.pow(maxLenOfLong).longValue();
        bdValue = bdValue.add(new BigDecimal(
                ((bdValue.longValue() / div) * div)).negate());

        return bdValue.toString();
    }

    /**
     * <p>
     * 数值整数部分・小数部分最大位数切取。<br>
     * 按指定的整数部分最大位数和小数部分最大位数切取数字。
     * </p>
     * <p>
     * 例：maxLenOfLong是【3】，maxLenOfFlt是【2】的场合<br>
     * 1234.999 → 234.99、-12345.987 → -345.98、null → null
     * </p>
     *
     * @param number
     *            数值
     * @param maxLenOfLong
     *            整数部分最大位数
     * @param maxLenOfFlt
     *            小数部分最大位数
     * @return 切取后数值
     */
    public static String getNumByMaxLen(String number, int maxLenOfLong,
                                        int maxLenOfFlt) {

        if (!CommonUtil.chkNumeric(number)) {
            return number;
        }

        BigDecimal bdValue = new BigDecimal(number).setScale(maxLenOfFlt,
                BigDecimal.ROUND_DOWN);

        if (bdValue.abs().setScale(0, BigDecimal.ROUND_DOWN).toString()
                .length() <= maxLenOfLong) {
            return bdValue.toString();
        }

        long div = BigDecimal.TEN.pow(maxLenOfLong).longValue();
        bdValue = bdValue.add(new BigDecimal(
                ((bdValue.longValue() / div) * div)).negate());

        return bdValue.toString();
    }

    /**
     * <p>
     * 指定数值小数位无条件下切。
     * </p>
     * <p>
     * 例：小数点保留位数是2的场合 <br>
     * 123.459 → 123.45、-123.459 → -123.45
     * </p>
     *
     * @param dblValue
     *            数值
     * @param scale
     *            小数点保留位数
     * @return 得出的数值
     */
    public static double roundDown(double dblValue, int scale) {
        BigDecimal bigDecimal = BigDecimal.valueOf(dblValue).setScale(scale,
                BigDecimal.ROUND_DOWN);
        return bigDecimal.doubleValue();
    }

    /**
     * <p>
     * 指定数值小数位四舍五入。
     * </p>
     * <p>
     * 例：小数点保留位数是2的场合 <br>
     * 123.454 → 123.45、-123.454 → -123.45 <br>
     * 123.455 → 123.46、-123.455 → -123.46 <br>
     * </p>
     *
     * @param dblValue
     *            数值
     * @param scale
     *            小数点保留位数
     * @return 得出的数值
     */
    public static double roundHalfUp(double dblValue, int scale) {
        BigDecimal bigDecimal = BigDecimal.valueOf(dblValue).setScale(scale,
                BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

    /**
     * <p>
     * 指定数值小数位无条件上切。
     * </p>
     * <p>
     * 例：小数点保留位数是2的场合 <br>
     * 123.451 → 123.46、-123.451 → -123.46
     * </p>
     *
     * @param dblValue
     *            数值
     * @param scale
     *            小数点保留位数
     * @return 得出的数值
     */
    public static double roundUp(double dblValue, int scale) {
        BigDecimal bigDecimal = BigDecimal.valueOf(dblValue).setScale(scale,
                BigDecimal.ROUND_UP);
        return bigDecimal.doubleValue();
    }

    // ========================================================= //
    // ****************** 文件类的相关处理方法 ******************** //
    // ========================================================= //

    /**
     * <p>
     * 文件备份。
     * </p>
     * <p>
     * 指定文件名在备份路径下存在的场合，同名文件删除。
     * </p>
     *
     * @param sourceFile
     *            存在文件的绝对路径名
     * @param destPath
     *            备份路径名
     * @return 备份文件的绝对路径名
     */
    public static String backupFile(String sourceFile, String destPath) {

        File fileFrom = null;
        File fileBackupPath = null;
        String backupFileName = null;

        if ((sourceFile == null) || (destPath == null)) {
            return null;
        }

        fileFrom = new File(sourceFile);
        if (!fileFrom.exists()) {
            return null;
        }

        // 备份文件作成
        fileBackupPath = new File(destPath);
        if (!fileBackupPath.exists()) {
            fileBackupPath.mkdir();
        }

        // 备份文件的绝对路径名
        backupFileName = CommonUtil.getAbsolutePath(destPath,
                fileFrom.getName());

        // 备份文件名已存在的情况下删除该文件
        CommonUtil.deleteFile(backupFileName);

        // 备份
        CommonUtil.copyFile(sourceFile, backupFileName);

        return backupFileName;
    }

    /**
     * <p>
     * 文件复制。
     * </p>
     * <p>
     * 将存在文件复制成一个新文件。
     * </p>
     *
     * @param sourceFile
     *            存在文件的绝对路径名
     * @param destFile
     *            新生成文件的绝对路径名
     */
    public static void copyFile(String sourceFile, String destFile) {

        if ((sourceFile == null) || (destFile == null)) {
            return;
        }

        int byteRead = 0;
        int bufSize = 10240;

        File fileFrom = new File(sourceFile); // 存在文件
        InputStream inStream = null; // 文件读入流
        FileOutputStream outputStream = null; // 文件写入流

        try {
            if (fileFrom.exists() && fileFrom.isFile()) {

                // 文件存在的场合，文件复制
                inStream = new FileInputStream(fileFrom);
                outputStream = new FileOutputStream(destFile);

                byte[] buffer = new byte[bufSize];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, byteRead);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 结束前处理
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>
     * 指定文件删除。
     * </p>
     *
     * @param pathFileName
     *            文件的绝对路径
     */
    public static void deleteFile(String pathFileName) {

        if (pathFileName == null) {
            return;
        }

        File file = new File(pathFileName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * <p>
     * 文件的绝对路径名取得。
     * </p>
     * <p>
     * 指定路径名和文件名组成文件的绝对路径名。
     * </p>
     *
     * @param path
     *            路径名
     * @param fileName
     *            文件名
     * @return 文件的绝对路径名
     */
    public static String getAbsolutePath(String path, String fileName) {

        if (path == null) {
            return null;
        }

        return (new File(path)).getAbsolutePath() + File.separator + fileName;
    }

    /**
     * <p>
     * 文件名(不含路径的)取得。
     * </p>
     *
     * @param fileNameWithPath
     *            文件的绝对路径名
     * @return 文件名(不含路径的)
     */
    public static String getFileNameWithoutPath(String fileNameWithPath) {
        return (new File(fileNameWithPath)).getName();
    }

    /**
     * <p>
     * 文件夹下全文件名取得。
     * </p>
     * <p>
     * 文件夹下全文件的绝对路径名取得(按文件的最终更新日期升序排列)。
     * </p>
     *
     * @param path
     *            路径
     * @return 文件名的集合
     */
    public static List<String> getFiles(String path) {

        List<String> listFile = new ArrayList<String>();
        File[] files = new File(path).listFiles();

        if (files == null) {
            return listFile;
        }

        // 文件按最终更新日期排序
        Arrays.sort(files, new Comparator<File>() {

            public int compare(File file1, File file2) {
                if (file1.lastModified() > file2.lastModified()) {
                    return 1;
                } else if (file1.lastModified() == file2.lastModified()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        // 文件名取得
        for (File file : files) {
            if (file.isFile()) {
                listFile.add(file.getAbsolutePath());
            }
        }

        return listFile;
    }

    /**
     * <p>
     * 文件夹下全文件名取得。
     * </p>
     * <p>
     * 文件夹下全文件的绝对路径名取得(按文件名中包含的作成时间升序排列)。
     * </p>
     *
     * @param path
     *            路径
     * @return 文件名的集合
     */
    public static List<String> getFilesSortByFileName(String path) {

        // 文件的绝对路径名取得
        File[] files = new File(path).listFiles();

        if (files == null) {
            return new ArrayList<String>();
        }

        // 按文件名中包含的作成时间升序排列
        Arrays.sort(files, new Comparator<File>() {

            public int compare(File file1, File file2) {
                if (file1.isFile() && file2.isFile()) {
                    String f1 = file1.getName().substring(
                            file1.getName().length() - 18,
                            file1.getName().length() - 4);
                    String f2 = file2.getName().substring(
                            file2.getName().length() - 18,
                            file2.getName().length() - 4);
                    return f1.compareTo(f2);
                } else if (file1.isFile()) {
                    return -1;
                } else if (file2.isFile()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // 文件名取得
        List<String> listFile = new ArrayList<String>();
        for (File file : files) {
            if (file.isFile()) {
                listFile.add(file.getAbsolutePath());
            }
        }

        return listFile;
    }

    /**
     * <p>
     * 指定路径下是否有文件存在的判断。
     * </p>
     *
     * @param filePath
     *            文件路径
     * @return True:存在、False:不存在
     */
    public static boolean hasFile(String filePath) {

        // 文件一览取得
        File[] files = new File(filePath).listFiles();

        // 空白的场合
        if (files == null) {
            return false;
        }

        // 文件存在的场合
        for (File file : files) {
            if (file.isFile()) {
                return true;
            }
        }

        return false;
    }

    /**
     * <p>
     * 路径的存在判断。
     * </p>
     *
     * @param path
     *            路径名
     * @return Ture：路径有、False：路径没
     */
    public static boolean isDirectoryExists(String path) {

        if (path == null) {
            return false;
        }

        File file = new File(path);
        return file.exists() && file.isDirectory();
    }

    /**
     * <p>
     * 指定文件中内容空白的判断。
     * </p>
     *
     * @param pathFileName
     *            文件的绝对路径
     * @return True：空白文件、False:非空文件
     */
    public static boolean isEmptyFile(String pathFileName) {
        File file = new File(pathFileName);
        return file.length() == 0;
    }

    /**
     * <p>
     * 文件的存在判断。
     * </p>
     *
     * @param pathFileName
     *            文件的绝对路径
     * @return Ture：文件有、False：文件没
     */
    public static boolean isFileExists(String pathFileName) {

        if (pathFileName == null) {
            return false;
        }

        File file = new File(pathFileName);
        return file.exists() && file.isFile();
    }

    /**
     * <p>
     * 文件移动(指定路径)。
     * </p>
     * <p>
     * 将指定文件向指定文件夹移动，生成一个同名的新文件。
     * </p>
     *
     * @param sourceFile
     *            移动文件的绝对路径名
     * @param destPath
     *            新文件的绝对路径名(只含路径)
     * @return True：移动成功
     */
    public static boolean moveFileToPath(String sourceFile, String destPath) {

        // 移动的文件
        File fileFrom = new File(sourceFile);

        // 新文件
        String fileTo = CommonUtil
                .getAbsolutePath(destPath, fileFrom.getName());

        if (fileFrom.getPath().equals(destPath)) {
            return true;
        }

        return fileFrom.renameTo(new File(fileTo));
    }

    /**
     * <p>
     * 文件移动。
     * </p>
     * <p>
     * 将指定文件向指定文件夹移动，生成一个指定文件名的新文件。
     * </p>
     *
     * @param sourceFile
     *            移动文件的绝对路径名
     * @param destFile
     *            新文件的绝对路径名
     * @return True：移动成功
     */
    public static boolean moveFile(String sourceFile, String destFile) {

        if (sourceFile.equals(destFile)) {
            return true;
        }

        File fileFrom = new File(sourceFile);
        File fileTo = new File(destFile);

        if (!fileFrom.exists()) {
            return false;
        }
        if (fileTo.exists()) {
            fileTo.delete();
        }

        return fileFrom.renameTo(fileTo);
    }

    /**
     * 获取日期年份
     *
     * @param date
     * @return
     */
    public static int getYear(String date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = CommonUtil.formatDate(calendar.getTime(), null);
        }
        calendar.setTime(CommonUtil.parseToDate(date,
                CommonConstants.DATE_FORMAT_DATETIME_HYPHEN));
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取日期月份
     *
     * @param date
     * @return
     */
    public static int getMonth(String date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = CommonUtil.formatDate(calendar.getTime(),
                    CommonConstants.DATE_FORMAT_DATETIME_HYPHEN);
        }
        calendar.setTime(CommonUtil.parseToDate(date, null));
        return (calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 获取日期号
     *
     * @param date
     * @return
     */
    public static int getDay(String date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = CommonUtil.formatDate(calendar.getTime(), null);
        }
        calendar.setTime(CommonUtil.parseToDate(date,
                CommonConstants.DATE_FORMAT_DATETIME_HYPHEN));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取月份起始日期
     *
     * @param date
     * @return
     */
    public static String getMinMonthDate(String date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = CommonUtil.formatDate(calendar.getTime(), null);
        }
        calendar.setTime(CommonUtil.parseToDate(date, null));
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return CommonUtil.formatDate(calendar.getTime(),
                CommonConstants.DATE_FORMAT_DATETIME_HYPHEN);
    }

    /**
     * 获取月份最后日期
     *
     * @param date
     * @return
     */
    public static String getMaxMonthDate(String date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = CommonUtil.formatDate(calendar.getTime(), null);
        }
        calendar.setTime(CommonUtil.parseToDate(date, null));
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return CommonUtil.formatDate(calendar.getTime(),
                CommonConstants.DATE_FORMAT_DATETIME_HYPHEN);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 网页agent检查
     *
     * @param agent
     *            agent
     * @return 结果
     */
    public static boolean checkMicroMessageAgent(String agent) {
        return agent.contains("MicroMessenger");
    }

    /**
     * 手机agent检查
     *
     * @param agent
     *            agent
     * @return 结果
     */
    public static boolean checkMobileAgent(String agent) {
        boolean flag = false;
        String[] keywords = { "Android", "iPhone", "iPod", "iPad",
                "Windows Phone", "MQQBrowser" };

        for (String item : keywords) {
            if (agent.contains(item)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    /**
     * 网页agent检查
     *
     * @param agent
     *            agent
     * @return 结果
     */
    public static boolean checkWebAgent(String agent) {
        return agent.contains("Web");
    }

}
