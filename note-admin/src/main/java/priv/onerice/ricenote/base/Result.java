package priv.onerice.ricenote.base;

import lombok.Data;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author onerice
 * @date 2023/4/2
 * @apiNote 自定义数据传输
 */

@Data
public class Result<T> implements Serializable {
    /**
     * 状态
     */
    private Boolean success;
    /**
     * 响应代码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应结果
     */
    private T result;

    public Result() {
    }

    public Result(boolean success, Integer code, String message, T result) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static <T> Result<T> success() {
        return new Result<T>(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功
     *
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<T>(true, ResultCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 失败
     */
    public static <T> Result<T> failed() {
        return new Result<T>(false, ResultCode.COMMON_FAIL.getCode(), ResultCode.COMMON_FAIL.getMessage(), null);
    }

    /**
     * 失败
     */
    public static <T> Result<T> failed(String message) {
        return new Result<T>(false, ResultCode.COMMON_FAIL.getCode(), message, null);
    }

    /**
     * 失败
     */
    public static <T> Result<T> failed(ResultCode errorCode) {
        return new Result<T>(false, errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败
     */
    public static <T> Result<T> failed(Integer errorCode, String message) {
        return new Result<T>(false, errorCode, message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> Result<T> unauthorized(T data) {
        return new Result<T>(false, ResultCode.USER_NOT_LOGIN.getCode(), ResultCode.USER_NOT_LOGIN.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> Result<T> forbidden(T data) {
        return new Result<T>(false, ResultCode.NO_PERMISSION.getCode(), ResultCode.NO_PERMISSION.getMessage(), data);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
