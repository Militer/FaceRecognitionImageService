package application.interceptor.performance;

import application.model.RequestData;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: militer
 * Date: 02.06.2017.
 */
public class PerformanceInterceptor implements HandlerInterceptor {
    public static final String REQUEST_DATA_ATTRIBUTE = "carServiceRequestData";
    private static String id = "0";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        if (!requestURI.equals("/request-monitor")) {
            synchronized (this) {
                long idValue = Long.parseLong(id);
                id = String.valueOf(++idValue);
            }
            httpServletRequest.setAttribute(REQUEST_DATA_ATTRIBUTE, new RequestData(id, httpServletRequest.getRequestURI(), httpServletRequest.getMethod(), System.currentTimeMillis()));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
