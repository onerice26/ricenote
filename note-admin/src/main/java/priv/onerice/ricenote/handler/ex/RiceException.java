package priv.onerice.ricenote.handler.ex;

import lombok.Getter;

/**
 * @author onerice
 * @date 2023/4/2
 * @apiNote
 */
@Getter
public class RiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public RiceException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public RiceException(String errorMsg) {
        this.errorCode = ResultCode.COMMON_FAIL.getCode();
        this.errorMsg = errorMsg;
    }
}
