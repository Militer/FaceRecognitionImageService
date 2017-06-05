package application.interceptor.performance;

import application.model.RequestData;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * User: militer
 * Date: 02.06.2017.
 */
public class PerformanceInterceptor implements HandlerInterceptor {
    private static final String requestDataAttribute = "carServiceRequestData";
    private static String id = "0";
    private static final List<RequestData> requests = new ArrayList<>();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        if (!requestURI.equals("/request-monitor")) {
            synchronized (this) {
                long idValue = Long.parseLong(id);
                id = String.valueOf(++idValue);
            }
            httpServletRequest.setAttribute(requestDataAttribute, new RequestData(id, httpServletRequest.getRequestURI(), httpServletRequest.getMethod(), System.currentTimeMillis()));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        RequestData requestData = (RequestData) httpServletRequest.getAttribute(requestDataAttribute);
        if (requestData != null) {
            requestData.setEndTime(System.currentTimeMillis());
            synchronized (this) {
                requests.add(requestData);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public static List<RequestData> getRequests() {
        return requests;
    }
}
