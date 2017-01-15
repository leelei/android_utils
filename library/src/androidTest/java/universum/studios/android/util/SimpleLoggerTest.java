/*
 * =================================================================================================
 *                             Copyright (C) 2016 Universum Studios
 * =================================================================================================
 *         Licensed under the Apache License, Version 2.0 or later (further "License" only).
 * -------------------------------------------------------------------------------------------------
 * You may use this file only in compliance with the License. More details and copy of this License
 * you may obtain at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * You can redistribute, modify or publish any part of the code written within this file but as it
 * is described in the License, the software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES or CONDITIONS OF ANY KIND.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 * =================================================================================================
 */
package universum.studios.android.util;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import universum.studios.android.util.inner.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Martin Albedinsky
 */
@RunWith(AndroidJUnit4.class)
public final class SimpleLoggerTest extends BaseTest {

	@SuppressWarnings("unused")
	private static final String TAG = "SimpleLogger";

	private SimpleLogger logger;

	@Override
	public void beforeTest() throws Exception {
		super.beforeTest();
		this.logger = new SimpleLogger(Log.VERBOSE);
	}

	@Test
	public void testSetLogLevel() {
		assertThat(logger.getLogLevel(), is(Log.VERBOSE));
		logger.setLogLevel(Log.ERROR);
		assertThat(logger.getLogLevel(), is(Log.ERROR));
	}

	@Test
	public void testIsLoggable() {
		logger.setLogLevel(Log.ASSERT);
		assertThat(logger.isLoggable(TAG, Log.VERBOSE), is(false));
		assertThat(logger.isLoggable(TAG, Log.DEBUG), is(false));
		assertThat(logger.isLoggable(TAG, Log.INFO), is(false));
		assertThat(logger.isLoggable(TAG, Log.WARN), is(false));
		assertThat(logger.isLoggable(TAG, Log.ERROR), is(false));
		assertThat(logger.isLoggable(TAG, Log.ASSERT), is(true));

		logger.setLogLevel(Log.ERROR);
		assertThat(logger.isLoggable(TAG, Log.VERBOSE), is(false));
		assertThat(logger.isLoggable(TAG, Log.DEBUG), is(false));
		assertThat(logger.isLoggable(TAG, Log.INFO), is(false));
		assertThat(logger.isLoggable(TAG, Log.WARN), is(false));
		assertThat(logger.isLoggable(TAG, Log.ERROR), is(true));
		assertThat(logger.isLoggable(TAG, Log.ASSERT), is(true));

		logger.setLogLevel(Log.WARN);
		assertThat(logger.isLoggable(TAG, Log.VERBOSE), is(false));
		assertThat(logger.isLoggable(TAG, Log.DEBUG), is(false));
		assertThat(logger.isLoggable(TAG, Log.INFO), is(false));
		assertThat(logger.isLoggable(TAG, Log.WARN), is(true));
		assertThat(logger.isLoggable(TAG, Log.ERROR), is(true));
		assertThat(logger.isLoggable(TAG, Log.ASSERT), is(true));

		logger.setLogLevel(Log.INFO);
		assertThat(logger.isLoggable(TAG, Log.VERBOSE), is(false));
		assertThat(logger.isLoggable(TAG, Log.DEBUG), is(false));
		assertThat(logger.isLoggable(TAG, Log.INFO), is(true));
		assertThat(logger.isLoggable(TAG, Log.WARN), is(true));
		assertThat(logger.isLoggable(TAG, Log.ERROR), is(true));
		assertThat(logger.isLoggable(TAG, Log.ASSERT), is(true));

		logger.setLogLevel(Log.DEBUG);
		assertThat(logger.isLoggable(TAG, Log.VERBOSE), is(false));
		assertThat(logger.isLoggable(TAG, Log.DEBUG), is(true));
		assertThat(logger.isLoggable(TAG, Log.INFO), is(true));
		assertThat(logger.isLoggable(TAG, Log.WARN), is(true));
		assertThat(logger.isLoggable(TAG, Log.ERROR), is(true));
		assertThat(logger.isLoggable(TAG, Log.ASSERT), is(true));

		logger.setLogLevel(Log.VERBOSE);
		assertThat(logger.isLoggable(TAG, Log.VERBOSE), is(true));
		assertThat(logger.isLoggable(TAG, Log.DEBUG), is(true));
		assertThat(logger.isLoggable(TAG, Log.INFO), is(true));
		assertThat(logger.isLoggable(TAG, Log.WARN), is(true));
		assertThat(logger.isLoggable(TAG, Log.ERROR), is(true));
		assertThat(logger.isLoggable(TAG, Log.ASSERT), is(true));
	}
}