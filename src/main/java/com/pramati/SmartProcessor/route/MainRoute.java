package com.pramati.SmartProcessor.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.pramati.SmartProcessor.bean.InputProcessorBean;

@Configuration
public class MainRoute extends SmartRouteBuilder {
	
	@Autowired
	private InputProcessorBean inputProcessorBean;

	@Override
	public void configure() throws Exception {
		rest("/messages/sendmessage").post().route().bean(inputProcessorBean);	
	}

}
