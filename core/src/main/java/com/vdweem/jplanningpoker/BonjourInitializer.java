package com.vdweem.jplanningpoker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BonjourInitializer implements ServletContextListener {
	private static final int PORT = 8080; // TODO: How to get the portnumber here?
	private JmDNS jmdns;

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		new Thread() {
			public void run() {
				try {
					Map<String, String> props = new HashMap<String, String>();
					props.put("path", sce.getServletContext().getContextPath());
					jmdns = JmDNS.create();
					jmdns.registerService(ServiceInfo.create("_http._tcp.local.", "jPlanningPoker", PORT, 0, 1, props));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			if (jmdns != null) {
				jmdns.unregisterAllServices();
				jmdns.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
