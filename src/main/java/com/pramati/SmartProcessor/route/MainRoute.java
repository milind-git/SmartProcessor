package com.pramati.SmartProcessor.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.pramati.SmartProcessor.bean.InputProcessorBean;
import com.pramati.SmartProcessor.bean.NLPProcessorBean;

@Configuration
public class MainRoute extends SmartRouteBuilder {
	
	@Autowired
	private InputProcessorBean inputProcessorBean;
	
	@Autowired 
	private NLPProcessorBean nlpProcessorBean;

	@Override
	public void configure() throws Exception {
		rest("/messages/sendmessage").post().route().bean(inputProcessorBean);
		rest("/messages/nlp").post().route().bean(nlpProcessorBean);
		
	}

}
