package pb.guestbook.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pb.guestbook.configs.resolvers.ResourceNotFoundException;
import pb.guestbook.configs.resolvers.ResourceNotFoundResolver;

import java.util.List;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/**")
            .addResourceLocations("classpath:/ui/")
            .resourceChain(true)
            .addResolver(new ResourceNotFoundResolver());
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(((request, response, handler, ex) -> {
            if (ex instanceof ResourceNotFoundException) {
                ModelAndView index = new ModelAndView("index");
                index.setStatus(HttpStatus.OK);
                return index;
            }
            return null;
        }));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}
