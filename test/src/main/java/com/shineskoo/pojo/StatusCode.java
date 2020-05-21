package com.shineskoo.pojo;


/**
 * Date: 2020/5/20
 * Author: ShinesKoo
 * Desc: 存放后端返回给前端返回码的常量类
 */
public class StatusCode {

    public static final Integer OK = 20000;
    public static final Integer ERROR = 20001;
    public static final Integer USER_PASS_ERROR = 20002;
    public static final Integer ACCESS_ERROR = 20003;
    public static final Integer REMOTE_ERROR = 20004;
    public static final Integer REPEAT_ERROR = 20005;
    public static final Integer NOTLOGIN_ERROR = 20006;//未登陆状态

    public static final Integer TOKEN_ERROR = 40014;//不合法的token

    public static final Integer EI_ERROR = 40015;//不合法得加密数据

    public static final Integer CODE_ERROR = 40029;//code无效
    //parameters
    public static final Integer PARAMETERS_ERROR = 40035;//不合法的参数
    public static final Integer FM_ERROR = 45011;//微信api调用频率限制

    public static final Integer GET_ERROR = 43001;//需要GET请求
    public static final Integer POST_ERROR = 43002;//需要POST请求
    public static final Integer SIGN_ERROR = 63002;//无效的签名


    public static final Integer VERIFICATION_CODE_ERROR = 40030;//验证码错误

    public static final Integer MOBILE_ERROR = 40031;//手机号格式有误


}
