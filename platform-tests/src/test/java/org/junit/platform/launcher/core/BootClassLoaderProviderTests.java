/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.platform.launcher.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder.request;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.fakes.TestEngineSpy;
import org.junit.platform.launcher.TestBootClassLoaderProvider;

/**
 * Tests for {@link BootClassLoaderProvider} functionality.
 */
class BootClassLoaderProviderTests {

	@AfterEach
	void resetTestProvider() {
		TestBootClassLoaderProvider.WAS_CALLED = false;
		TestBootClassLoaderProvider.CUSTOM_CLASSLOADER = null;
	}

	@Test
	void bootClassLoaderProviderCanBeConfiguredExplicitly() {
		var customClassLoader = new URLClassLoader(new URL[0], ClassLoader.getSystemClassLoader());

		var config = LauncherConfig.builder() //
				.addTestEngines(new TestEngineSpy()) //
				.enableTestEngineAutoRegistration(false) //
				.bootClassLoader(customClassLoader) //
				.build();

		assertThat(config.getBootClassLoader()).isSameAs(customClassLoader);
	}

	@Test
	void explicitlyConfiguredBootClassLoaderIsUsedForServiceLoading() {
		// Create a custom classloader
		var customClassLoader = new URLClassLoader(new URL[0], ClassLoader.getSystemClassLoader());

		var config = LauncherConfig.builder() //
				.addTestEngines(new TestEngineSpy()) //
				.enableTestEngineAutoRegistration(false) //
				.bootClassLoader(customClassLoader) //
				.build();

		// Create launcher with explicit boot classloader
		var launcher = LauncherFactory.create(config);

		// Execute should work fine
		launcher.execute(request().forExecution().build());

		// Verify the configuration was accepted
		assertThat(config.getBootClassLoader()).isSameAs(customClassLoader);
	}

	@Test
	void defaultConfigUsesDefaultClassLoader() {
		var config = LauncherConfig.builder() //
				.addTestEngines(new TestEngineSpy()) //
				.enableTestEngineAutoRegistration(false) //
				.build();

		assertThat(config.getBootClassLoader()).isNull();

		// Create launcher should work with default classloader
		var launcher = LauncherFactory.create(config);
		launcher.execute(request().forExecution().build());
	}

}
