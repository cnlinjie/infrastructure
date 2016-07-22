package com.github.cnlinjie.infrastructure.web.resolver;

import com.alibaba.fastjson.JSON;
import com.github.cnlinjie.infrastructure.web.Constant;
import com.github.cnlinjie.infrastructure.web.exception.CommonException;
import com.github.cnlinjie.infrastructure.web.exception.HttpNetException;
import com.github.cnlinjie.infrastructure.web.exception.MyNoHandlerFoundException;
import com.github.cnlinjie.infrastructure.web.model.RetModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 用来拦截错误信息的异常拦截器
 */
public abstract class DefaultHandler2ExceptionResolver extends DefaultHandlerExceptionResolver {

    protected String http400 = "/error/404";
    protected String http404 = "/error/404";
    protected String http405 = "/error/404";
    protected String http500 = "/error/404";
    protected String netError = "/error/net-error";

    protected String getHttp400 () {
        return http400;
    }

    protected String getHttp404 () {
        return http404;
    }


    protected String getHttp405 () {
        return http405;
    }

    protected String getHttp500 () {
        return http500;
    }

    protected String getNetError () {
        return netError;
    }


    private static final Logger logger = LoggerFactory.getLogger(DefaultHandler2ExceptionResolver.class);

    public DefaultHandler2ExceptionResolver () {
        setOrder(1);
    }

    @Override
    public ModelAndView resolveException (HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof CommonException) {

                return handleCommonException((CommonException) ex, request, response, handler);

            } else if (ex instanceof HttpNetException) {

                handleHttpNetException((HttpNetException) ex, request, response, handler);

            } else if (ex instanceof IllegalArgumentException) {

                return handleIllegalArgumentException((IllegalArgumentException) ex, request, response, handler);

            } else if (ex instanceof MyNoHandlerFoundException) {

                return handleMyNoHandlerFoundException((MyNoHandlerFoundException) ex, request, response, handler);

            } else if (ex instanceof NoSuchRequestHandlingMethodException) {

                return handleNoSuchRequestHandlingMethod((NoSuchRequestHandlingMethodException) ex, request, response, handler);

            } else if (ex instanceof HttpRequestMethodNotSupportedException) {

                return handleHttpRequestMethodNotSupported((HttpRequestMethodNotSupportedException) ex, request, response, handler);

            } else if (ex instanceof HttpMediaTypeNotSupportedException) {

                return handleHttpMediaTypeNotSupported((HttpMediaTypeNotSupportedException) ex, request, response, handler);

            } else if (ex instanceof HttpMediaTypeNotAcceptableException) {

                return handleHttpMediaTypeNotAcceptable((HttpMediaTypeNotAcceptableException) ex, request, response, handler);

            } else if (ex instanceof MissingPathVariableException) {

                return handleMissingPathVariable((MissingPathVariableException) ex, request, response, handler);

            } else if (ex instanceof MissingServletRequestParameterException) {

                return handleMissingServletRequestParameter((MissingServletRequestParameterException) ex, request, response, handler);

            } else if (ex instanceof ServletRequestBindingException) {

                return handleServletRequestBindingException((ServletRequestBindingException) ex, request, response, handler);

            } else if (ex instanceof ConversionNotSupportedException) {

                return handleConversionNotSupported((ConversionNotSupportedException) ex, request, response, handler);

            } else if (ex instanceof TypeMismatchException) {

                return handleTypeMismatch((TypeMismatchException) ex, request, response, handler);

            } else if (ex instanceof HttpMessageNotReadableException) {

                return handleHttpMessageNotReadable((HttpMessageNotReadableException) ex, request, response, handler);

            } else if (ex instanceof HttpMessageNotWritableException) {

                return handleHttpMessageNotWritable((HttpMessageNotWritableException) ex, request, response, handler);

            } else if (ex instanceof MethodArgumentNotValidException) {

                return handleMethodArgumentNotValidException((MethodArgumentNotValidException) ex, request, response, handler);

            } else if (ex instanceof MissingServletRequestPartException) {

                return handleMissingServletRequestPartException((MissingServletRequestPartException) ex, request, response, handler);

            } else if (ex instanceof BindException) {

                return handleBindException((BindException) ex, request, response, handler);

            } else if (ex instanceof NoHandlerFoundException) {

                return handleNoHandlerFoundException((NoHandlerFoundException) ex, request, response, handler);

            } else {
                logger.error("Handling of [" + ex.getClass().getName() + "] resulted in Exception", ex);
                return handleException(ex, request, response, handler);
            }
        } catch (Exception handlerException) {
            logger.error("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
        }
        return null;
    }

    /**
     * 处理网络连不上
     *
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    protected ModelAndView handleHttpNetException (HttpNetException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        handlerMessage(request, response, ex.getLocalizedMessage(), modelAndView, getNetError());
        modelAndView.addObject("ioMsg", ex.getIoException().getLocalizedMessage());
        return modelAndView;
    }


    protected ModelAndView handleException (Exception ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        handlerMessage(request, response, ex.getLocalizedMessage(), modelAndView, getHttp500());
        return modelAndView;
    }

    protected ModelAndView handleCommonException (CommonException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        outputStreamWrite(response, data(Constant.DEFAULT_ERRCODE_FAIL, ex.getLocalizedMessage()));
        return modelAndView;
    }

    protected ModelAndView handleIllegalArgumentException (IllegalArgumentException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        outputStreamWrite(response, data(Constant.DEFAULT_ERRCODE_FAIL, ex.getLocalizedMessage()));
        return modelAndView;
    }

    protected ModelAndView handleMyNoHandlerFoundException (MyNoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        handlerMessage(request, response, ex.getLocalizedMessage(), modelAndView, getHttp404());
        return modelAndView;
    }


    /**
     * 404
     *
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    @Override
    protected ModelAndView handleNoHandlerFoundException (NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        pageNotFoundLogger.warn(ex.getMessage());
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        handlerMessage(request, response, ex.getLocalizedMessage(), modelAndView, getHttp404());
        return modelAndView;
    }

    /**
     * 404
     *
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    @Override
    protected ModelAndView handleNoSuchRequestHandlingMethod (NoSuchRequestHandlingMethodException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        pageNotFoundLogger.warn(ex.getMessage());
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        handlerMessage(request, response, ex.getLocalizedMessage(), modelAndView, getHttp404());
        return modelAndView;
    }

    /**
     * 405
     *
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    @Override
    protected ModelAndView handleHttpRequestMethodNotSupported (HttpRequestMethodNotSupportedException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        pageNotFoundLogger.warn(ex.getMessage());
        String[] supportedMethods = ex.getSupportedMethods();
        if (supportedMethods != null) {
            response.setHeader("Allow", StringUtils.arrayToDelimitedString(supportedMethods, ", "));
        }
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        ModelAndView modelAndView = new ModelAndView();
        handlerMessage(request, response, ex.getLocalizedMessage(), modelAndView, getHttp405());
        return modelAndView;
    }

    /**
     * Handle the case when a required parameter is missing.
     * <p>The default implementation sends an HTTP 400 error, and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the MissingServletRequestParameterException
     * could be rethrown as-is.
     *
     * @param ex       the MissingServletRequestParameterException to be handled
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleMissingServletRequestParameter (MissingServletRequestParameterException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        pageNotFoundLogger.warn(ex.getMessage());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        handlerMessage(request, response, ex.getLocalizedMessage(), modelAndView, getHttp400());
        return modelAndView;
    }


    protected void handlerMessage (HttpServletRequest request, HttpServletResponse response, String msg, ModelAndView modelAndView, String viewName) {
        String header = request.getHeader("Accept");
        if (MimeTypeUtils.APPLICATION_JSON_VALUE.equals(header)) {
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            outputStreamWrite(response, data(Constant.DEFAULT_ERRCODE_FAIL, msg));
        } else {
            modelAndView.setViewName(viewName);
            modelAndView.addObject("msg", msg);
        }
    }

    protected void outputStreamWrite (HttpServletResponse response, Object data) {
        String msgText = JSON.toJSONString(data);
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(msgText.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            logger.error("outputStreamWrite:", e);
        }
    }

    protected RetModel data (Integer errCode, String message) {
        RetModel errModel = new RetModel(
                errCode, message
        );
        return errModel;
    }

}
