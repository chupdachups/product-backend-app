package com.msa.product.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component 
@Profile("local")
@Slf4j 
public class H2Console {
	
	private Server webServer; 
	
	@EventListener(ContextRefreshedEvent.class) 
	public void start() throws java.sql.SQLException { 
		log.info("starting h2 console at port 8171"); 
		this.webServer = org.h2.tools.Server.createWebServer("-webPort", "8171", "-tcpAllowOthers").start(); 
	} 
	
	@EventListener(ContextClosedEvent.class) 
	public void stop() { 
		log.info("stopping h2 console at port 8171"); 
		this.webServer.stop(); 
	}

}
