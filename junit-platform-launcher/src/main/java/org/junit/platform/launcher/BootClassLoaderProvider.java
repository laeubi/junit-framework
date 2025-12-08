/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.platform.launcher;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

import org.apiguardian.api.API;

/**
 * SPI for providing a boot/discovery classloader to be used by the JUnit Platform
 * for discovering and loading test engines, extensions, and other SPI implementations.
 *
 * <p>Implementations of this interface are discovered at runtime via Java's
 * {@link java.util.ServiceLoader} mechanism. A single implementation can be
 * registered via the {@code META-INF/services/org.junit.platform.launcher.BootClassLoaderProvider}
 * file.
 *
 * <p>The boot classloader provided by this SPI is used during the discovery phase
 * to load test engines, listeners, and other components via {@link java.util.ServiceLoader}.
 * This allows tools to separate the classloader used for discovery from the classloader
 * used to run tests, preventing tests from inheriting tool-specific classes and dependencies.
 *
 * <p>If multiple implementations are found, the first one returned by the
 * {@link java.util.ServiceLoader} will be used.
 *
 * @since 6.1
 * @see java.util.ServiceLoader
 */
@API(status = EXPERIMENTAL, since = "6.1")
public interface BootClassLoaderProvider {

	/**
	 * Provides the boot classloader to be used for discovering and loading
	 * JUnit Platform components via the ServiceLoader mechanism.
	 *
	 * <p>The returned classloader will be used to discover test engines,
	 * listeners, interceptors, and other SPI implementations during the
	 * launcher initialization phase.
	 *
	 * @return the boot classloader; must not be {@code null}
	 */
	ClassLoader getBootClassLoader();

}
