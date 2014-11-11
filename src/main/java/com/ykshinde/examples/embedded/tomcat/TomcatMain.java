package com.ykshinde.examples.embedded.tomcat;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.ykshinde.examples.servlet.SimpleServlet;

public class TomcatMain {

	/**
	 * @param args
	 */
	public static void main(final String[] args) throws IOException,
			LifecycleException, ServletException {
		
		System.out.println("STARTING EMBEDDED TOMCAT WEB CONTAINER\n");
		new TomcatMain().startServer();

	}
	
	public void startServer() throws LifecycleException, ServletException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		File base = new File("./staticContent");
		
		Context rootCtx = tomcat.addWebapp("/app", base.getAbsolutePath());
		
		Tomcat.addServlet(rootCtx, "home", new SimpleServlet());
		rootCtx.addServletMapping("/home", "home");
		
		tomcat.start();
		tomcat.getServer().await();
	}

}
