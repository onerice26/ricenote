package priv.onerice.ricenote.base;

import com.alibaba.fastjson.JSONObject;
import priv.onerice.ricenote.handler.ex.ResultCode;
import lombok.Data;

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
    private String msg;
    /**
     * 响应结果
     */
    private T data;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
        this.code = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.msg = success ? ResultCode.SUCCESS.getMsg() : ResultCode.COMMON_FAIL.getMsg();
    }

    public Result(boolean success, ResultCode resultEnum) {
        this.success = success;
        this.code = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.msg = success ? ResultCode.SUCCESS.getMsg() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMsg() : resultEnum.getMsg());
    }

    public Result(boolean success, T data) {
        this.success = success;
        this.code = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.msg = success ? ResultCode.SUCCESS.getMsg() : ResultCode.COMMON_FAIL.getMsg();
        this.data = data;
    }

    public Result(boolean success, ResultCode resultEnum, T data) {
        this.success = success;
        this.code = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.msg = success ? ResultCode.SUCCESS.getMsg() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMsg() : resultEnum.getMsg());
        this.data = data;
    }

    public Result(boolean success, Integer code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     *
     * @return
     */
    public static Result success() {
        return new Result(true);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return new Result(true, data);
    }

    /**
     * 失败
     */
    public static Result error() {
        return new Result(false);
    }

    /**
     * 失败
     */
    public static Result error(String resultMessage) {
        return new Result(false, 999, resultMessage, null);
    }

    /**
     * 失败
     */
    public static Result error(ResultCode resultCode) {
        return new Result(false, resultCode);
    }

    /**
     * 失败
     */
    public static Result error(Integer resultCode, String resultMessage) {
        return new Result(false, resultCode, resultMessage, null);
    }


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
