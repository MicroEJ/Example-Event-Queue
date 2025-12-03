/*
 * Kotlin
 *
 * Copyright 2024-2025 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

plugins {
	id("com.microej.gradle.mock") version libs.versions.microej.sdk
}

dependencies {
	compileOnly(libs.mock.api)

	implementation(libs.api.edc)

	implementation(group = "com.microej.pack.event", name = "event-pack", version = "2.0.1", configuration = "mockAPI")
}
