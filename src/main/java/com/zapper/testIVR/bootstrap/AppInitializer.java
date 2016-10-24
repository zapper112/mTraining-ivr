package com.zapper.testIVR.bootstrap;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

  public void onStartup(ServletContext servletContext) {
    AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
    webApplicationContext.setConfigLocation("com.zapper.testIVR.configuration");
    servletContext.addListener(new ContextLoaderListener(webApplicationContext));
    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherSerlvet",
        new DispatcherServlet(webApplicationContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/mtrain/*");
  }
}
