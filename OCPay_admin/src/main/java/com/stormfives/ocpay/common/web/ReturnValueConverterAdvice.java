package com.stormfives.ocpay.common.web;



import com.stormfives.ocpay.common.response.FailResponse;
import com.stormfives.ocpay.common.response.SuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Component
@ControllerAdvice
public class ReturnValueConverterAdvice implements ResponseBodyAdvice {

    /**
     * 日志记录.
     */
    private Logger logger = LoggerFactory.getLogger(ReturnValueConverterAdvice.class);

    /**
     *
     * @param returnType returnType
     * @param converterType converterType
     * @return boolean
     */
    public final boolean supports(final MethodParameter returnType, final Class converterType) {
        return true;
    }

    /**
     * 在返回前进行封装.
     * @param body body
     * @param returnType returnType
     * @param selectedContentType selectedContentType
     * @param selectedConverterType selectedConverterType
     * @param request requestbean
     * @param response responsebean
     * @return Object
     */
    public final Object beforeBodyWrite(final Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof SuccessResponse)
            return body;
        else if(body instanceof FailResponse)
            return body;
        else if(body instanceof Resource)
            return body;
        else
            return new SuccessResponse(body);

    }

}
