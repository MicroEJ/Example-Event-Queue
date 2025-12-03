/*
 * C
 *
 * Copyright 2023 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

#ifndef EventQueueExampleNatives_H
#define EventQueueExampleNatives_H

#include <sni.h>

#ifdef __cplusplus
	extern "C" {
#endif


/**
* Offer a GPIO event to the Event Queue.
*
* @param type
*            the type of the event.
* @return 0 -> success, -1 -> failed: illegal arguments, -2 -> failed: the FIFO is full
*/
jint Java_com_microej_example_EventQueueExampleNatives_offerGpioEvent(jint type);

/**
* Offer an Accelerometer event to the Event Queue.
*
* @param type
*            the type of the event.
* @return 0 -> success, -1 -> failed: illegal arguments, -2 -> failed: the FIFO is full
*/
jint Java_com_microej_example_EventQueueExampleNatives_offerAccelerometerEvent(jint type);

/**
* Offer an Weather event to the Event Queue.
*
* @param type
*            the type of the event.
* @return 0 -> success, -1 -> failed: illegal arguments, -2 -> failed: the FIFO is full
*/
jint Java_com_microej_example_EventQueueExampleNatives_offerWeatherEvent(jint type);

/**
* Offer a default basic event to the Event Queue.
*
* @param type
*            the type of the event.
* @return 0 -> success, -1 -> failed: illegal arguments, -2 -> failed: the FIFO is full
*/
jint Java_com_microej_example_EventQueueExampleNatives_offerDefaultBasicEvent(jint type);

/**
* Offer a default extended event to the Event Queue.
*
* @param type
*            the type of the event.
* @return 0 -> success, -1 -> failed: illegal arguments, -2 -> failed: the FIFO is full
*/
jint Java_com_microej_example_EventQueueExampleNatives_offerDefaultExtendedEvent(jint type);

#ifdef __cplusplus
	}
#endif

#endif
