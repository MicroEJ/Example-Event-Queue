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
 * This listener is used to handle data received from the GPIO sensors.
 *
 * The GPIO data are composed of the Id and the State of the GPIO:
 *
 * ID (8 bits) | STATE (16 bits)
 */
public class GPIOEventListener implements EventQueueListener {
	private static final Logger LOGGER = Logger.getLogger("[GPIOEventListener] "); //$NON-NLS-1$

	private static final int GPIO_ID_MASK = 0xFF;
	private static final int GPIO_ID_SHIFT = 16;
	private static final int GPIO_STATE_MASK = 0xFFFF;

	@Override
	public void handleEvent(int type, int data) {
		// The id of the GPIO.
		int gpioId = (data >> GPIO_ID_SHIFT) & GPIO_ID_MASK;
		// The state of the GPIO
		int gpioState = data & GPIO_STATE_MASK;

		// Log the GPIO values.
		LOGGER.info("Basic event received, type = " + type + ", GPIO " + gpioId + " has the state value " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ gpioState + "."); //$NON-NLS-1$
	}

	@Override
	public void handleExtendedEvent(int type, EventDataReader eventDataReader) {
		// Nothing to do.
	}

}
