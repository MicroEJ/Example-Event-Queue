/*
 * Java
 *
 * Copyright 2023-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example;

import java.util.logging.Logger;

import ej.event.EventDataReader;
import ej.event.EventQueueListener;

/**
 * This default listener will print some information when the event type is not handled by any registered listener.
 */
public class DefaultListener implements EventQueueListener {
	/**
	 * Private logger.
	 */
	private static final Logger LOGGER = Logger.getLogger("[DefaultListener] "); //$NON-NLS-1$

	@Override
	public void handleEvent(int type, int data) {
		LOGGER.info("Basic event received, type = " + type + ", data = " + data + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	@Override
	public void handleExtendedEvent(int type, EventDataReader eventDataReader) {
		LOGGER.info("Extended event received, type = " + type + " there is " + eventDataReader.available() //$NON-NLS-1$ //$NON-NLS-2$
				+ " bytes available."); //$NON-NLS-1$
	}

}
