package cn.ylapl.controller.errorHandler;

import cn.ylapl.exception.ValueIsNullException;
import cn.ylapl.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常统一处理类
 * Created by Angle on 2017/3/13.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 统一异常类返回值
     * @param e 异常
     * @return 错误信息JSON字符串
     */
    @ExceptionHandler(Exception.class)
    public Result valueIsNullException(ValueIsNullException e) {
        Result result = new Result();

        result.setCode(0)
                .setError(e.getMessage())
                .setException(e);

        return result;
    }
}
