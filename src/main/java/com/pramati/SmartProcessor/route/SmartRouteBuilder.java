package com.pramati.SmartProcessor.route;

import javax.annotation.PostConstruct;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public abstract class SmartRouteBuilder extends RouteBuilder {
	
	@PostConstruct
	private void initConfig()
	{
		restConfiguration().component("restlet").host("localhost").port("9090").bindingMode(RestBindingMode.auto);
	} 
}
