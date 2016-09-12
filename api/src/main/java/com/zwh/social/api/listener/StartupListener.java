package com.zwh.social.api.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener {
	private static final Log log = LogFactory.getLog(StartupListener.class);

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void contextInitialized(ServletContextEvent event) {
	
	}

	/**
	 * Shutdown servlet context (currently a no-op method).
	 * 
	 * @param servletContextEvent
	 *            The servlet context event
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// LogFactory.release(Thread.currentThread().getContextClassLoader());
		// Commented out the above call to avoid warning when SLF4J in
		// classpath.
		// WARN: The method class
		// org.apache.commons.logging.impl.SLF4JLogFactory#release() was
		// invoked.
		// WARN: Please see http://www.slf4j.org/codes.html for an explanation.
	}
}
