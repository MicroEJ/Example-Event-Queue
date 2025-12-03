/*
 * Java
 *
 * Copyright 2023-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example;

/**
 * Native methods of the Event Queue Example.
 */
public class EventQueueExampleNatives {

	/**
	 * Private constructor.
	 */
	private EventQueueExampleNatives() {

	}

	/**
	 * Offer a GPIO event to the Event Queue.
	 *
	 * @param type
	 *            the type of the event.
	 * @return 0 success, -1 failed: illegal arguments, -2 failed: the FIFO is full
	 */
	public static native int offerGpioEvent(int type);

	/**
	 * Offer an accelerometer event to the Event Queue.
	 *
	 * @param type
	 *            the type of the event.
	 * @return 0 success, -1 failed: illegal arguments, -2 failed: the FIFO is full
	 */
	public static native int offerAccelerometerEvent(int type);

	/**
	 * Offer a weather event to the Event Queue.
	 *
	 * @param type
	 *            the type of the event.
	 * @return 0 success, -1 failed: illegal arguments, -2 failed: the FIFO is full
	 */
	public static native int offerWeatherEvent(int type);

	/**
	 * Offer a default basic event to the Event Queue.
	 *
	 * @param type
	 *            the type of the event.
	 * @return 0 success, -1 failed: illegal arguments, -2 failed: the FIFO is full
	 */
	public static native int offerDefaultBasicEvent(int type);

	/**
	 * Offer a default extended event to the Event Queue.
	 *
	 * @param type
	 *            the type of the event.
	 * @return 0 success, -1 failed: illegal arguments, -2 failed: the FIFO is full
	 */
	public static native int offerDefaultExtendedEvent(int type);
}
