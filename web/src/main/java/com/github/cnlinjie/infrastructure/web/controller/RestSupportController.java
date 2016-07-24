package com.github.cnlinjie.infrastructure.web.controller;

import com.github.cnlinjie.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.web.exception.CommonException;
import com.github.cnlinjie.infrastructure.web.model.RetModel;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;


/**
 * Rest 类型的控制器
 * 自动将错误转为json类型
 * @author Linjie
 * @date 2015/10/10.
 */
public class RestSupportController extends SupportController {

    private static final Integer DEFALT_ERROR_CODE = -1;
    private static final Integer DEFALT_SCCUSS_CODE = 0;

    @ExceptionHandler ({
            IllegalArgumentException.class,
            CommonException.class,
            BindException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class
    })
    @ResponseBody
    public RetModel handleException (Exception ex) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(ex.getMessage());
        }
        RetModel data = new RetModel();
        if (ex instanceof CommonException) {
            return handleCommonException(data, (CommonException) ex);
        } else if (ex instanceof IllegalArgumentException) {
            return handleIllegalArgumentException(data, (IllegalArgumentException) ex);
        } else if (ex instanceof BindException) {
            return handleBindException(data, (BindException) ex);
        } else if (ex instanceof MethodArgumentNotValidException) {
            return handleMethodArgumentNotValidException(data, (MethodArgumentNotValidException) ex);
        } else if (ex instanceof ConstraintViolationException) {
            return handleConstraintViolationException(data, (ConstraintViolationException) ex);
        }
        throw  ex;
    }

    protected RetModel handleCommonException (RetModel data, CommonException e) throws NoSuchMethodException {
        return data(data, defaultErrCode(), e.getMessage());
    }

    protected RetModel handleIllegalArgumentException (RetModel data, IllegalArgumentException e) throws NoSuchMethodException {
        return data(data, defaultErrCode(), e.getMessage());
    }

    protected RetModel handleBindException (RetModel data, BindException e) throws NoSuchMethodException {
        List<FieldError> errors = e.getFieldErrors();
        return data(data, defaultErrCode(), errors.get(0).getDefaultMessage());
    }

    protected RetModel handleConstraintViolationException (RetModel data, ConstraintViolationException e) throws NoSuchMethodException {
        ConstraintViolation<?> next = e.getConstraintViolations().iterator().next();   // 第一个错误
        return data(data, defaultErrCode(), next.getMessage());
    }

    protected RetModel handleMethodArgumentNotValidException (RetModel data, MethodArgumentNotValidException e) throws NoSuchMethodException {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        return  data(data, defaultErrCode(), errors.get(0).getDefaultMessage());
    }

    protected RetModel data (RetModel data, Integer errCode, String message) {
        data.setErrCode(errCode);
        data.setMsg(message);
        return data;
    }

    protected RetModel successData () {
        RetModel data = new RetModel(defaultSuccessCode(), "");
        return data;
    }

    protected RetModel newData () {
        return new RetModel();
    }

    protected RetModel assemblyPageData (Page result) {
        RetModel data = new RetModel(
                defaultSuccessCode(),
                "",
                result
        );
        return data;
    }

    protected void throwError (Integer code, String message) {
        throw new CommonException(code, message);
    }

    protected void throwError (Integer code) {
        throwError(code, "");
    }

    protected void throwError (String msg) {
        throwError(defaultErrCode(), msg);
    }

    protected Integer defaultErrCode () {
        return DEFALT_ERROR_CODE;
    }

    protected Integer defaultSuccessCode () {
        return DEFALT_SCCUSS_CODE;
    }

}
