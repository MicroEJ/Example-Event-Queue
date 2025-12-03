/*
 * C
 *
 * Copyright 2023-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

#include "EventQueueExampleNatives.h"
#include "LLEVENT.h"
#include "LLEVENT_impl.h"
#include "stdlib.h"
#include "stdio.h"

#ifdef __cplusplus
	extern "C" {
#endif

#define ACCELEROMETER_MAX_VALUE 1000
#define WEATHER_MAX_VALUE 100

struct accelerometer_data {
	jint x;
	jint y;
	jint z;
};

struct weather_data {
	jfloat temperature;
	jdouble wind;
	jboolean rain;
};

jint Java_com_microej_example_EventQueueExampleNatives_offerGpioEvent(jint type)
{
	uint32_t gpio_id = rand()%0xFF;
	uint32_t gpio_state = rand()%0xFFFF;

	(void) printf("GPIO data sent through the Queue: Id= %u, state= %u \n", gpio_id, gpio_state);
	uint32_t data = (gpio_id << (uint32_t) 16) | gpio_state;

	return LLEVENT_offerEvent(type, data);
}

jint Java_com_microej_example_EventQueueExampleNatives_offerAccelerometerEvent(jint type)
{
	struct accelerometer_data ptr;
	ptr.x = rand()%ACCELEROMETER_MAX_VALUE;
	ptr.y = rand()%ACCELEROMETER_MAX_VALUE;
	ptr.z = rand()%ACCELEROMETER_MAX_VALUE;

	(void) printf("Accelerometer data sent through the Queue: x = %d, y= %d, z= %d \n", ptr.x, ptr.y ,ptr.z);

	return LLEVENT_offerExtendedEvent(type, (void*)&ptr, sizeof(ptr));
}

jint Java_com_microej_example_EventQueueExampleNatives_offerWeatherEvent(jint type)
{
	struct weather_data ptr;
	ptr.temperature = (float) WEATHER_MAX_VALUE * (float)(rand()/(float)RAND_MAX);
	ptr.wind = (double) WEATHER_MAX_VALUE * (double)(rand()/(double)RAND_MAX);
	ptr.rain = rand()%2;

	(void) printf("Weather data sent through the Queue: temperature = %f, y= %f, z= %c \n", ptr.temperature, ptr.wind ,ptr.rain);

	return LLEVENT_offerExtendedEvent(type, (void*)&ptr, sizeof(ptr));
}

jint Java_com_microej_example_EventQueueExampleNatives_offerDefaultBasicEvent(jint type)
{
	return LLEVENT_offerEvent(type, rand()%100);
}

jint Java_com_microej_example_EventQueueExampleNatives_offerDefaultExtendedEvent(jint type)
{
	int32_t byte_length = rand()%10;
	jbyte myArray[10] = {0};
	return LLEVENT_offerExtendedEvent(type, (void*)myArray, byte_length);
}




#ifdef __cplusplus
	}
#endif
