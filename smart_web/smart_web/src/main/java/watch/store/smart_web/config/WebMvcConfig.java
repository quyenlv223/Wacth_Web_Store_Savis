package watch.store.smart_web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import watch.store.smart_web.intercepter.CartIntercepter;


@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final CartIntercepter categoryIntercepter;

     @Override
     public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(categoryIntercepter)
              .addPathPatterns("/product/**")
              .addPathPatterns("/cart/**")
              .addPathPatterns("/home/**")
              .addPathPatterns("/order")
              .addPathPatterns("/check-out/**")
              .excludePathPatterns("");
     }
}
