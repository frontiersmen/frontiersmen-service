package me.ericjiang.frontiersmen.service.configuration;

import com.google.inject.name.Names;

public class ServletModule extends com.google.inject.servlet.ServletModule {
    @Override
    protected void configureServlets() {
        requestStaticInjection(GameEndpointConfigurator.class);
        install(new GameEndpointModule());
    }
}
