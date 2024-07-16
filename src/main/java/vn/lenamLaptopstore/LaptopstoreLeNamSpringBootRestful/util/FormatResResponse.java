package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletResponse;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.RestResponse;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.annotation.ApiMessage;

// Intervention Response (send to client) (Global Area)
@RestControllerAdvice
public class FormatResResponse implements ResponseBodyAdvice<Object> {

    // Intervention Response(send to client) (if true) (Global Area)
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {

        HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int statusCode = httpServletResponse.getStatus();

        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(statusCode);

        if (body instanceof String) {
            return body;
        }

        if (statusCode >= 400) {
            // Format response in File GlobalException
            return body;
        } else {
            // case success
            // Get message from Custom Annotation
            ApiMessage message = returnType.getMethodAnnotation(ApiMessage.class);
            res.setData(body);
            res.setMessage(message != null ? message.value() + " SUCCESSFUL" : "CALL API SUCCESSFUL");
            return res;
        }

    }

}
