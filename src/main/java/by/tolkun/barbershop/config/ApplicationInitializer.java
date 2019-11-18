package by.tolkun.barbershop.config;

import by.tolkun.barbershop.filter.SecurityFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {
    private String TMP_FOLDER = "/";
    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;


    @Override
    public void onStartup(ServletContext context) {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.scan("by.tolkun.barbershop");
        context.addListener(new ContextLoaderListener(root));

        FilterRegistration.Dynamic encodingFilter = context.addFilter("encodingFilter",
                new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");

        context.addFilter("securityFilter", new SecurityFilter()).addMappingForUrlPatterns(null, true, "/*" );

        DispatcherServlet dispatcherServlet = new DispatcherServlet(
                new GenericWebApplicationContext());
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        ServletRegistration.Dynamic appServlet =
                context.addServlet("mvc", dispatcherServlet);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER,
                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
        appServlet.setMultipartConfig(multipartConfigElement);
    }
}
