package pl.sda.eventsagregator;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pl.sda.eventsagregator.configurers.WebMvcConfig;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebMvcConfig.class };
    }
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
