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

import org.jspecify.annotations.Nullable;

/**
 * Test implementation of BootClassLoaderProvider for testing purposes.
 */
public class TestBootClassLoaderProvider implements BootClassLoaderProvider {

	public static @Nullable ClassLoader CUSTOM_CLASSLOADER = null;
	public static boolean WAS_CALLED = false;

	@Override
	public ClassLoader getBootClassLoader() {
		WAS_CALLED = true;
		if (CUSTOM_CLASSLOADER != null) {
			return CUSTOM_CLASSLOADER;
		}
		return Thread.currentThread().getContextClassLoader();
	}

}
