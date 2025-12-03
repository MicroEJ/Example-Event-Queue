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
 * This listener is used to handle extended data received from the accelerometer sensor.
 *
 * The extended data are composed of three Integers corresponding to the X, Y and Z values of an accelerometer.
 */
public class AccelerometerEventListener implements EventQueueListener {
	private static final Logger LOGGER = Logger.getLogger("[AccelerometerEventListener] "); //$NON-NLS-1$

	@Override
	public void handleEvent(int type, int data) {
		// Nothing to do.

	}

	@Override
	public void handleExtendedEvent(int type, EventDataReader eventDataReader) {

		// X value of the accelerometer.
		int x = 0;
		// Y value of the accelerometer.
		int y = 0;
		// Z value of the accelerometer.
		int z = 0;

		// Read the accelerometer values from the eventDataReader.
		try {
			x = eventDataReader.readInt();
			y = eventDataReader.readInt();
			z = eventDataReader.readInt();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IOException while reading accelerometer values from the EventDataReader.", e); //$NON-NLS-1$
		}

		// Log the accelerometer values.
		LOGGER.info("Extended event received, type = " + type + ", Accelerometer values: X = " + x + ", Y = " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ y + ", Z = " + z + "."); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
