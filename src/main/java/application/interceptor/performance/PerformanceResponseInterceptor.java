package application.interceptor.performance;

import application.model.RequestData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * User: militer
 * Date: 21.06.2017.
 */
@ControllerAdvice
public class PerformanceResponseInterceptor implements ResponseBodyAdvice<Object> {
    private static final List<RequestData> requests = new ArrayList<>();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        RequestData requestData = (RequestData) ((ServletServerHttpRequest) request).getServletRequest().getAttribute("carServiceRequestData");
        if (requestData != null) {
            requestData.setEndTime(System.currentTimeMillis());
            synchronized (this) {
                requests.add(requestData);
            }
        }
        return body;
    }

    public static List<RequestData> getRequests() {
        return requests;
    }
}
