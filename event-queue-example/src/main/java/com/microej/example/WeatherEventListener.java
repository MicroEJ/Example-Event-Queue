/*
 * Java
 *
 * Copyright 2023-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ej.event.EventDataReader;
import ej.event.EventQueueListener;

/**
 * This listener is used to handle weather extended data received.
 *
 * The weather extended data are composed of:
 *
 * - The temperature (float value).
 *
 * - The wind (double value).
 *
 * - The rain (boolean value).
 */
public class WeatherEventListener implements EventQueueListener {
	private static final Logger LOGGER = Logger.getLogger("[WeatherEventListener] "); //$NON-NLS-1$

	@Override
	public void handleEvent(int type, int data) {
		// Nothing to do.
	}

	@Override
	public void handleExtendedEvent(int type, EventDataReader eventDataReader) {
		// Temperature information.
		float temperature = 0;
		// Wind information.
		double wind = 0;
		// Rain information.
		boolean rain = false;

		try {
			temperature = eventDataReader.readFloat();
			wind = eventDataReader.readDouble();
			rain = eventDataReader.readBoolean();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IOException while reading weather values from the EventDataReader.", e); //$NON-NLS-1$
		}

		String isItRaining = rain ? "Yes" : "No"; //$NON-NLS-1$ //$NON-NLS-2$
		// Log the weather values.
		LOGGER.info("Extended event received, type = " + type + " Weather values: temperature = " + temperature //$NON-NLS-1$ //$NON-NLS-2$
				+ " degrees, wind = " + wind + " km/h, is it raining? " + isItRaining + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	}

}
