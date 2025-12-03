.. figure::
   https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/sdk_6.0.json
   :alt: SDK 6 Badge

.. figure::
   https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/arch_8.1.json
   :alt: Archi 8.1 Badge

Overview
========

The following project illustrates the use of `MicroEJ Event
Queue <https://docs.microej.com/en/latest/ApplicationDeveloperGuide/eventQueue.html>`__
by sending events through the Event Queue and handling them with
listeners.

Application Flow
----------------

The application first shows how to start the Event Queue and register
listeners with it. The listeners used for this example are a
`GPIOEventListener <event-queue-example/src/main/java/com/microej/example/GPIOEventListener.java>`_,
an `AccelerometerEventListener <event-queue-example/src/main/java/com/microej/example/AccelerometerEventListener.java>`_,
a `WeatherEventListener <event-queue-example/src/main/java/com/microej/example/WeatherEventListener.java>`_ and
a `DefaultEventListener <event-queue-example/src/main/java/com/microej/example/DefaultListener.java.java>`_.

Then it will use the Java API of the Event Queue to offer different
events:

- Offer a basic event (GPIO data).
- Offer an extended event composed of integers (Accelerometer data).
- Offer an extended event composed of a float, a double, and a boolean
  (Weather data).
- Offer basic and extended events with an event type that is not
  registered (trigger the default listener).

To finish, it will send the same events using the Event Queue’s Native
API:

- For the simulator part, these events will be created and sent in the
  Java implementation of the native methods located in
  `EventQueueExampleNatives.java <event-queue-example-mock/src/main/java/com/microej/example/EventQueueExampleNatives.java>`_
  file.
- For the embedded part, these events will be created and sent in the C
  implementation of the native methods located in
  `EventQueueExampleNatives.c <event-queue-example/src/main/c/src/EventQueueExampleNatives.c>`_
  file.

The Event Queue cannot be stopped, so we call ``System.exit(0)`` at the
end of the example to stop the application.

Requirements
============

- MicroEJ SDK 6.
- A MicroEJ VEE Port with (at least):

  - EDC-1.3.3
  - EVENT-2.0.0

This example has been tested on `MIMXRT1170-EVK VEE Port version 3.1.0 <https://github.com/MicroEJ/nxp-vee-imxrt1170-evk.git>`__.

Usage
=====

Open the project in the MicroEJ SDK 6 and follow instructions to `import
a
project <https://docs.microej.com/en/latest/SDK6UserGuide/importProject.html#import-a-project>`__.

Modify the `build.gradle.kts <event-queue-example/build.gradle.kts>`_ to add the dependency
to your VEE Port. Refer to the `Select a VEE
Port <https://docs.microej.com/en/latest/SDK6UserGuide/selectVeePort.html#select-a-vee-port>`__
documentation for more information.

Running the Application on Simulator
------------------------------------

Add event-queue-example Mock to the VEE Port
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

First, build and publish the mock. In IntelliJ IDEA or Android Studio:

- Open the Gradle tool window by clicking on the elephant icon on the
  right side,
- Expand the ``Tasks`` list,
- From the ``Tasks`` list, expand the ``microej`` list and double-click
  on ``buildMockRip`` to build the mock,
- From the ``Tasks`` list, expand the ``publishing`` list and
  double-click on ``publish`` to publish the mock. It will also build
  the mock.

Then, add the dependency to the mock in your VEE Port, following `this
guide <https://docs.microej.com/en/latest/VEEPortingGuide/mock.html#in-a-vee-port>`__.

.. note:: If the VEE Port is included in this project via an ``includeBuild`` directive
   in `<settings.gradle.kts>`_, there is no need to publish the mock.
   E.g. ``includeBuild("path/to/nxpvee-mimxrt1170-evk")``

Run on Simulator
~~~~~~~~~~~~~~~~

In IntelliJ IDEA or Android Studio:

- Open the Gradle tool window by clicking on the elephant icon on the
  right side,
- Expand the ``Tasks`` list,
- From the ``Tasks`` list, expand the ``microej`` list,
- Double-click on ``runOnSimulator``,
- The application starts, the traces are visible in the Run view.

Alternative ways to run in simulation are described in the `Run on
Simulator <https://docs.microej.com/en/latest/SDK6UserGuide/runOnSimulator.html>`__
documentation.

Running the Application on Device
---------------------------------

Adding C Sources to the BSP Project
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Some C source files need to be added to the BSP project to use the event
queue example. Those sources are located in the
`event-queue-example/src/main/c <event-queue-example/src/main/c>`_ folder of the repository.

The
`EventQueueExampleNatives.c <event-queue-example/src/main/c/src/EventQueueExampleNatives.c>`_
file is the implementation of the example native methods that use the
Native API of the Event Queue.

Run on Device
~~~~~~~~~~~~~

Make sure to properly setup the VEE Port environment before going
further. Refer to the VEE Port README for more information.

In IntelliJ IDEA or Android Studio:

- Open the Gradle tool window by clicking on the elephant on the right
  side,
- Expand the ``Tasks`` list,
- From the ``Tasks`` list, expand the ``microej`` list,
- Double-Click on ``runOnDevice``.
- The device is flashed. Use the appropriate tool to retrieve the
  execution traces.

Alternative ways to run on device are described in the `Run on
Device <https://docs.microej.com/en/latest/SDK6UserGuide/runOnDevice.html>`__
documentation.

Dependencies
============

*All dependencies are retrieved transitively by MicroEJ Module Manager*.

Source
======

N/A.

Restrictions
============

None.

--------------

.. ReStructuredText
.. Copyright 2023-2025 MicroEJ Corp. All rights reserved.
.. Use of this source code is governed by a BSD-style license that can be found with this software.
