package com.thoughtWork.trains;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by Administrator on 2016/9/17 0017.
 */

@SpringBootApplication
public class Application {
	public static void main(String[] args){
		new SpringApplicationBuilder()
				.web(true)
				.sources(Application.class)
				.build()
				.run(args);
	}
}
