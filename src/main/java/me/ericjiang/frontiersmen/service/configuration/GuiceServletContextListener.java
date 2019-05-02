package me.ericjiang.frontiersmen.service.configuration;

import javax.servlet.annotation.WebListener;

import com.google.inject.Guice;
import com.google.inject.Injector;

@WebListener
public class GuiceServletContextListener extends com.google.inject.servlet.GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule());
    }
}
