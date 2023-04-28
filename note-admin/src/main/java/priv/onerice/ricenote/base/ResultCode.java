package priv.onerice.ricenote.base;

/**
 * @author onerice
 * @date 2023/4/2
 * @apiNote 异常枚举类
 */

public enum ResultCode {
    // 成功
    SUCCESS(200, "成功"),
    COMMON_FAIL(999, "未知异常"),

    // 参数错误 1000~1999
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    PARAMS_NOT_CONVERT(1005, "参数类型转换异常"),
    PARAMS_NULL_BODY(1006, "参数类型转换异常"),

    // 用户错误
    USER_NOT_TOKEN(2000, "token失效"),
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_USERNAME_PASSWORD_ERROE(2002, "账号或密码错误"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限");
    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
