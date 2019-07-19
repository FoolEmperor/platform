package pers.lance.platform.base.bean;

/**
 * 常用正则
 *
 * @author: lance
 * @create: 2018-11-15 16:32
 */
public class CustomRegexp {

    /**
     * 用户密码：6-20 位，字母、数字、字符
     * String reg = "^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,20}$";
     */
    public static final String PASSWORD = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,20}$";
    public static final String PASSWORD_MESSAGE = "密码格式：6-20 位，字母、数字、字符";

    /**
     * 用户名：必须以字母开头，只能包括 字母 , 下划线 , 数字，长度必须在6 到 10 之间
     * public static final String USERNAME = "[a-zA-Z]\\w{5,9}";
     * public static final String USERNAME_MESSAGE = "用户名：必须以字母开头，只能包括 字母 , 下划线 , 数字，长度必须在6 到 20 之间";
     * 用户名：汉字、字母、数字的组合，长度必须在4 到 20 之间
     */
    public static final String USERNAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
    public static final String USERNAME_MESSAGE = "用户名：汉字、字母、数字的组合，长度必须在4 到 20 之间";

    /**
     * 验证码：6位数字
     */
    public static final String CODE = "[0-9]{6}";
    public static final String CODE_MESSAGE = "验证码：6位数字";
    /**
     * 手机号码：11位数字
     */
    public static final String PHONE_NUMBER = "^1\\d{10}$";
    public static final String PHONE_NUMBER_MESSAGE = "手机号码：11位数字";
    /**
     * 日期：YYYY-MM-DD
     */
    public static final String DATE = "^\\d{4}\\-\\d{1,2}\\-\\d{1,2}$";
    public static final String DATE_MESSAGE = "日期格式：YYYY-MM-DD";
    /**
     * 日期：YYYY-MM-DD HH:mm:dd
     */
    public static final String DATE_TIME = "^(\\d{4})\\-(\\d{2})\\-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})$";
    public static final String DATE_TIME_MESSAGE = "日期格式：YYYY-MM-DD";

}
