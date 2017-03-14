package cn.ylapl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 值为空异常
 * Created by Angle on 2017/3/13.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValueIsNullException extends RuntimeException {

    public ValueIsNullException() {
    }

    public ValueIsNullException(String message) {
        super(message);
    }
}
