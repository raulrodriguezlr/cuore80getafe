package es.cuore80getafe.cuore80getafe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.*;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Cuore80getafeApplication {
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(Cuore80getafeApplication.class);
	  }
	public static void main(String[] args) {
		SpringApplication.run(Cuore80getafeApplication.class, args);
		String so = System.getProperty("os.name");
		String separador = System.getProperty("file.separator");
		String ruta= ".."+separador+"Desktop"+separador;
		System.out.println(ruta);
	}
	
	
	@Bean
	public ServletWebServerFactory servletContainer() {
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	        @Override
	        protected void postProcessContext(Context context) {
	            SecurityConstraint securityConstraint = new SecurityConstraint();
	            securityConstraint.setUserConstraint("CONFIDENTIAL");
	            SecurityCollection collection = new SecurityCollection();
	            collection.addPattern("/*");
	            securityConstraint.addCollection(collection);
	            context.addConstraint(securityConstraint);
	        }
	    };
	    tomcat.addAdditionalTomcatConnectors(redirectConnector());
	    return tomcat;
	}
	

	private Connector redirectConnector() {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    connector.setScheme("http");
	    connector.setPort(80);
	    connector.setSecure(false);
	    connector.setRedirectPort(443);
	    return connector;
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(4000000);
	    return multipartResolver;
	}
	
	/*
	private String TMP_FOLDER = "/tmp"; 
    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024; 
    
    public void onStartup(ServletContext sc) throws ServletException {
        
        ServletRegistration.Dynamic appServlet = sc.addServlet("mvc", new DispatcherServlet(
          new GenericWebApplicationContext()));

        appServlet.setLoadOnStartup(1);
        
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, 
          MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
        
        appServlet.setMultipartConfig(multipartConfigElement);
    }*/
}
