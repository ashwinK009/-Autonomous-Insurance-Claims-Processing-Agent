package com.aicp.agent;
import com.aicp.agent.parser.AICPParser;
import com.aicp.agent.model.FinalResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
public class Main {

		
		public static void main(String[] args) throws Exception {

	        File pdf = new File("sample-files/readfile.pdf");

	        AICPParser parser = new AICPParser();
	        FinalResponse response = parser.process(pdf);

	        ObjectMapper mapper = new ObjectMapper();
	        System.out.println(
	            mapper.writerWithDefaultPrettyPrinter()
	                  .writeValueAsString(response)
	        );
	    }
	}


