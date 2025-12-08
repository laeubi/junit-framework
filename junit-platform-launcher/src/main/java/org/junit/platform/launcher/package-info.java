/**
 * Public API for configuring and launching test plans.
 *
 * <p>This API is typically used by IDEs and build tools.
 *
 * <h2>Custom Boot ClassLoader</h2>
 *
 * <p>To provide a custom boot classloader for discovering and loading
 * JUnit Platform components, implement the {@link BootClassLoaderProvider}
 * SPI and register it via the {@code META-INF/services/org.junit.platform.launcher.BootClassLoaderProvider}
 * file.
 */

@NullMarked
package org.junit.platform.launcher;

import org.jspecify.annotations.NullMarked;
