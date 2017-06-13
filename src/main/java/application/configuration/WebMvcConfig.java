package application.configuration;

import application.interceptor.performance.PerformanceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * User: militer
 * Date: 02.06.2017.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private final PerformanceInterceptor performanceInterceptor;

    @Autowired
    public WebMvcConfig(PerformanceInterceptor performanceInterceptor) {
        this.performanceInterceptor = performanceInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(performanceInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:8083");
    }
}
