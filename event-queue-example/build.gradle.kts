/*
 * Kotlin
 *
 * Copyright 2024-2025 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

plugins {
	id("com.microej.gradle.application") version libs.versions.microej.sdk
}

microej {
	applicationEntryPoint = "com.microej.example.Main"
	architectureUsage = System.getProperty("com.microej.architecture.usage") ?: "eval" // or "prod"
}

dependencies {
	implementation(libs.api.edc)
	implementation(libs.api.event)

	implementation(libs.library.logging)
	implementation(libs.library.bytebuffer)

	// Uncomment the microejVee dependency to set the VEE Port or Kernel to use
	//microejVee(libs.veeport.nxp.mimxrt1170)
}


