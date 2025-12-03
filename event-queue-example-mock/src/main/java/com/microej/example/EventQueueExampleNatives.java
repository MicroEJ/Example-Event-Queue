/*
 * Java
 *
 * Copyright 2023 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example;

import java.nio.ByteOrder;
import java.util.Random;

import ej.event.EventQueueMock;

/**
 * Native methods of the Event Queue Example.
 */
public class EventQueueExampleNatives {
	private static final Random random = new Random();

	private static final int GPIO_ID_MASK = 0xFF;
	private static final int GPIO_ID_SHIFT = 16;
	private static final int GPIO_STATE_MASK = 0xFFFF;

	private static final int ACCELEROMETER_MAX_VALUE = 1000;
	private static final int ACCELEROMETER_ARRAY_DATA_SIZE = 12;
	private static final int ACCELEROMETER_ARRAY_Y_INDEX = 4;
	private static final int ACCELEROMETER_ARRAY_Z_INDEX = 8;

	private static final int WEATHER_ARRAY_DATA_SIZE = 17;
	private static final int WEATHER_MAX_VALUE = 100;
	private static final int WEATHER_WIND_INDEX = 8;
	private static final int WEATHER_RAIN_INDEX = 16;

	private static final int DOUBLE_ARRAY_SIZE = 8;
	private static final int FLOAT_ARRAY_SIZE = 4;
	private static final int INTEGER_ARRAY_SIZE = 4;

	private static final int DEFAULT_EVENT_MAX_VALUE = 100;
	private static final int DEFAULT_EXTENDED_EVENT_DATA_LENGTH = 10;

	private static final int SHIFT_8 = 8;
	private static final int SHIFT_16 = 16;
	private static final int SHIFT_24 = 24;
	private static final int SHIFT_32 = 32;
	private static final int SHIFT_40 = 40;
	private static final int SHIFT_48 = 48;
	private static final int SHIFT_56 = 56;

	/**
	 * Private constructor.
	 */
	private EventQueueExampleNatives() {

	}

	/**
	 * Offer an GPIO event to the Event Queue.
	 *
	 * @param type
	 *            the type of the event.
	 * @return 0 success, -1 failed: illegal arguments, -2 failed: the FIFO is full
	 */
	public static int offerGpioEvent(int type) {
		return EventQueueMock.offerEvent(type, getGpioData());
	}

	/**
	 * Offer an accelerometer event to the Event Queue.
	 *
	 * @param type
	 *            the type of the event.
	 * @return 0 success, -1 failed: illegal arguments, -2 failed: the FIFO is full
	 */
	public static int offerAccelerometerEvent(int type) {
		byte[] accelerometerData = getAccelerometerData();
		return EventQueueMock.offerExtendedEvent(type, accelerometerData, accelerometerData.length);
	}

	/**
	 * Offer a weather event to the Event Queue.
	 *
	 * @param type
	 *            the type of the event.
	 * @return 0 success, -1 failed: illegal arguments, -2 failed: the FIFO is full
	 */
	public static int offerWeatherEvent(int type) {
		byte[] weatherData = getWeatherData();
		return EventQueueMock.offerExtendedEvent(type, weatherData, weatherData.length);
	}

	/**
	 * Offer a default basic event to the Event Queue.
	 *
	 * @param type
	 *            the type of the event.
	 * @return 0 success, -1 failed: illegal arguments, -2 failed: the FIFO is full
	 */
	public static int offerDefaultBasicEvent(int type) {
		return EventQueueMock.offerEvent(type, random.nextInt(DEFAULT_EVENT_MAX_VALUE));
	}

	/**
	 * Offer a default extended event to the Event Queue.
	 *
	 * @param type
	 *            the type of the event.
	 * @return 0 success, -1 failed: illegal arguments, -2 failed: the FIFO is full
	 */
	public static int offerDefaultExtendedEvent(int type) {
		int dataSize = random.nextInt(DEFAULT_EXTENDED_EVENT_DATA_LENGTH);
		return EventQueueMock.offerExtendedEvent(type, new byte[dataSize], dataSize);
	}

	/**
	 * The GPIO data are composed of the Id and the State of the GPIO:
	 *
	 * ID (8 bits) | STATE (16 bits)
	 *
	 * @return the randomized GPIO data.
	 */
	private static int getGpioData() {
		int gpioId = random.nextInt(GPIO_ID_MASK);
		int gpioState = random.nextInt(GPIO_STATE_MASK);

		System.out.println( // NOSONAR Use System.out for the readability of the example.
				"[EventQueueExampleNatives] GPIO data sent through the Queue: Id=" + gpioId + ", state=" + gpioState); //$NON-NLS-1$ //$NON-NLS-2$
		return ((gpioId & GPIO_ID_MASK) << GPIO_ID_SHIFT) | (gpioState & GPIO_STATE_MASK);
	}

	/**
	 * Accelerometer data are composed of three Integers corresponding to the X, Y and Z values of an accelerometer.
	 *
	 * @return the randomized accelerometer data.
	 */
	private static byte[] getAccelerometerData() {
		int x = random.nextInt(ACCELEROMETER_MAX_VALUE);
		int y = random.nextInt(ACCELEROMETER_MAX_VALUE);
		int z = random.nextInt(ACCELEROMETER_MAX_VALUE);

		System.out.println( // NOSONAR Use System.out for the readability of the example.
				"[EventQueueExampleNatives] Accelerometer data sent through the Queue: x = " + x + ", y=" + y //$NON-NLS-1$ //$NON-NLS-2$
						+ ", z=" + z); //$NON-NLS-1$

		byte[] accelerometerData = new byte[ACCELEROMETER_ARRAY_DATA_SIZE];

		byte[] xArray = intToByteArray(x);
		byte[] yArray = intToByteArray(y);
		byte[] zArray = intToByteArray(z);

		System.arraycopy(xArray, 0, accelerometerData, 0, INTEGER_ARRAY_SIZE);
		System.arraycopy(yArray, 0, accelerometerData, ACCELEROMETER_ARRAY_Y_INDEX, INTEGER_ARRAY_SIZE);
		System.arraycopy(zArray, 0, accelerometerData, ACCELEROMETER_ARRAY_Z_INDEX, INTEGER_ARRAY_SIZE);

		return accelerometerData;
	}

	/**
	 * This listener is used to handle weather extended data received. The alignment of the data must be the same than
	 * the C structures.
	 *
	 * The weather extended data are composed of:
	 *
	 * - The temperature (float value). -> 4 bytes aligned, data[0:3].
	 *
	 * - The wind (double value). -> 8 bytes aligned, data[8:15].
	 *
	 * - The rain (boolean value). -> 1 byte aligned, data[16];
	 *
	 * @return the randomized weather data.
	 */
	private static byte[] getWeatherData() {
		float temperature = random.nextFloat() * WEATHER_MAX_VALUE;
		double wind = random.nextDouble() * WEATHER_MAX_VALUE;
		byte rain = (byte) random.nextInt(2);

		byte[] weatherValues = new byte[WEATHER_ARRAY_DATA_SIZE];

		// Get the array of bytes from the float value:
		byte[] floatArray = intToByteArray(Float.floatToIntBits(temperature));

		// Get the array of bytes from the double value:
		byte[] doubleArray = longToByteArray(Double.doubleToLongBits(wind));

		System.arraycopy(floatArray, 0, weatherValues, 0, FLOAT_ARRAY_SIZE);// 4 bytes aligned -> byte[0:4]
		System.arraycopy(doubleArray, 0, weatherValues, WEATHER_WIND_INDEX, DOUBLE_ARRAY_SIZE);// 8 bytes aligned ->
																								// byte[8:15]
		weatherValues[WEATHER_RAIN_INDEX] = rain;

		System.out.println( // NOSONAR Use System.out for the readability of the example.
				"[EventQueueExampleNatives] Weather data sent through the Queue: temperature = " //$NON-NLS-1$
						+ temperature + ", wind =" + wind + ", rain =" + rain); //$NON-NLS-1$ //$NON-NLS-2$

		return weatherValues;
	}

	/**
	 * Converts an integer value into a byte array depending on the endianness of the VEE Port.
	 *
	 * @param value
	 *            the integer value to convert.
	 * @return the byte array corresponding to the integer value.
	 */
	public static byte[] intToByteArray(int value) {
		byte[] returnInt;
		if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
			returnInt = new byte[] { (byte) (value), (byte) (value >> SHIFT_8), (byte) (value >> SHIFT_16),
					(byte) (value >> SHIFT_24) };
		} else {
			returnInt = new byte[] { (byte) (value >> SHIFT_24), (byte) (value >> SHIFT_16), (byte) (value >> SHIFT_8),
					(byte) (value) };
		}
		return returnInt;
	}

	/**
	 * Converts a long value into a byte array depending on the endianness of the VEE Port.
	 *
	 * @param value
	 *            the long value to convert.
	 * @return the byte array corresponding to the long value.
	 */
	public static byte[] longToByteArray(long value) {
		byte[] returnDouble;
		if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
			returnDouble = new byte[] { (byte) (value), (byte) (value >> SHIFT_8), (byte) (value >> SHIFT_16),
					(byte) (value >> SHIFT_24), (byte) (value >> SHIFT_32), (byte) (value >> SHIFT_40),
					(byte) (value >> SHIFT_48), (byte) (value >> SHIFT_56) };
		} else {
			returnDouble = new byte[] { (byte) (value >> SHIFT_56), (byte) (value >> SHIFT_48),
					(byte) (value >> SHIFT_40), (byte) (value >> SHIFT_32), (byte) (value >> SHIFT_24),
					(byte) (value >> SHIFT_16), (byte) (value >> SHIFT_8), (byte) (value) };
		}
		return returnDouble;
	}
}
