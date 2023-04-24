package priv.onerice.ricenote.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.onerice.ricenote.base.Result;
import priv.onerice.ricenote.handler.ex.ResultCode;
import priv.onerice.ricenote.handler.ex.RiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author onerice
 * @date 2023/4/2
 * @apiNote 全局异常处理器
 */

@ControllerAdvice
@Slf4j
public class RiceExceptionHandler {
    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = RiceException.class)
    @ResponseBody
    public Result bizExceptionHandler(HttpServletRequest req, RiceException e) {
        log.error("发生业务异常！原因是：{}", e.getMessage());
        return Result.failed(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return Result.failed(ResultCode.PARAMS_NULL_BODY);
    }

    /**
     * 处理类型转换异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NumberFormatException e) {
        log.error("发生类型转换异常！原因是:", e);
        return Result.failed(ResultCode.PARAMS_NOT_CONVERT);
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return Result.failed(ResultCode.COMMON_FAIL);
    }
}
