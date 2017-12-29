package com.pramati.SmartProcessor.bean;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

@Component("nlpProcessorBean")
public class NLPProcessorBean {

	private Logger log = LoggerFactory.getLogger(NLPProcessorBean.class);
	
	private POSTaggerME tagger = null;
	
	@PostConstruct
	private void initialize() {
		 try {
		  InputStream modelStream = getClass().getResourceAsStream("/nlp/en-pos-maxent.bin");
		  POSModel model = new POSModel(modelStream);
		  tagger = new POSTaggerME(model);
		 } catch (IOException e) {
		  System.out.println(e.getMessage());
		 }
		}

    /**
     * This method processes incoming messages and return responses.
     *
     * @param message a message coming from a human user in a chat
     * @return the reply of the bot.
     * @throws Exception if the input is null.
     */
    public String process(String message) throws Exception {
        if (message == null) {
            throw new Exception("Please send some message");
        }
        StringBuilder result = new StringBuilder();

        log.info("Received message: {}", message);
		try {
			if (tagger != null) {
				String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(message);
				String[] tags = tagger.tag(whitespaceTokenizerLine);
				for (int i = 0; i < whitespaceTokenizerLine.length; i++) {
					String word = whitespaceTokenizerLine[i].trim();
					String tag = tags[i].trim();
					System.out.print(tag + ":" + word + "  ");
					result.append(tag + ":" + word + "  ");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}
        
}
