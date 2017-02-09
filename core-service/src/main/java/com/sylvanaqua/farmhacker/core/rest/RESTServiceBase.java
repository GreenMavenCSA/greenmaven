package com.sylvanaqua.farmhacker.core.rest;

public class RESTServiceBase {

	/**
	 * Logs exceptions to stream. Currently System.err. This needs to
	 * change to log4j log.
	 * 
	 * @param e
	 */
	protected void logException(Exception e) {
		System.err.println("Exception thrown getting catalog entries: ");
		System.err.println(e.toString());
		System.err.println(e.getMessage());
	}
}
