package application.controller;

import application.interceptor.performance.PerformanceInterceptor;
import application.model.RequestData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: militer
 * Date: 02.06.2017.
 */
@RestController
@RequestMapping("/request-monitor")
public class RequestMonitorController {
    @GetMapping
    public List<RequestData> getRequestsData() {
        return PerformanceInterceptor.getRequests();
    }
}
