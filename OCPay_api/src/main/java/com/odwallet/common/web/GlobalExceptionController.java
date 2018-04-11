package com.odwallet.common.web;


import com.odwallet.common.response.FailResponse;
import com.odwallet.common.util.MessageSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.EOFException;

/**
 * Global exception controller.
 * <p>
 * Handles all exceptions, if the exception have a response status,
 * set the HTTP status to that, else set the HTTP status to 500.
 * <p>
 * Error message displays only if the response status presents,
 * having the first non-empty value of following:
 * <ol>
 * <li>Response status reason</li>
 * <li>Localized message of exception</li>
 * <li>"Unknown error"</li>
 * </ol>
 * <p>
 * This handler also log every exception the generate 5XX error.
 */
@ControllerAdvice
@Component
public class GlobalExceptionController {

    /**
     * 日志记录类.
     */
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    @Autowired
    private MessageSourceUtil messageSource;

    /**
     * 异常处理类.
     * @param e Exception
     * @return ErrorResult
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final FailResponse handleException(HttpServletRequest request, Exception e) {
        int code = ObikeHttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = messageSource.getMessage("error");    //默认消息

        if (e instanceof EOFException){
            return new FailResponse(code, message);
        }

        ObikeResponseStatus status = e.getClass().getAnnotation(ObikeResponseStatus.class);
        StringBuilder messageBuilder = new StringBuilder("Unhandled exception in request:\n");

        if (status != null) {
            code = status.value().value();

            if (!StringUtils.isEmpty(status.reason())) {
                message = status.reason();
            } else if (!StringUtils.isEmpty(e.getLocalizedMessage())) {
                message = e.getLocalizedMessage();
            }

            messageBuilder.append("message");
            messageBuilder.append(": ");
            messageBuilder.append(message);
            messageBuilder.append("\n");
        }

        messageBuilder.append(request.getMethod());
        messageBuilder.append(" ");
        messageBuilder.append(request.getRequestURI());
        messageBuilder.append("\nHeader:");
        EnumerationUtils.toIterable(request::getHeaderNames).forEach(name -> {
            messageBuilder.append("\n");
            String value = request.getHeader(name);
            messageBuilder.append(name);
            messageBuilder.append(": ");
            messageBuilder.append(value);
        });
        messageBuilder.append("\nParameters:");
        EnumerationUtils.toIterable(request::getParameterNames).forEach(name -> {
            messageBuilder.append("\n");
            messageBuilder.append(name);
            messageBuilder.append(": ");
            messageBuilder.append(request.getParameter(name));
        });

        messageBuilder.append("\npostBody:");
        messageBuilder.append(request.getAttribute("postBody"));

        if (code == 500){  //针对服务器异常 打印错误信息栈
            logger.error(messageBuilder.toString(), e);
        }else { //针对自定义异常 不打印错误信息栈  节省流量传输
            logger.warn(messageBuilder.toString());
        }

        return new FailResponse(code, message);
    }
}
