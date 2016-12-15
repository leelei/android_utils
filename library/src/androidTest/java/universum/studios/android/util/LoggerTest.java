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

import junit.framework.TestCase;

/**
 * @author Martin Albedinsky
 */
public final class LoggerTest extends TestCase {

	@SuppressWarnings("unused")
	private static final String TAG = "LoggerTest";

	public void testReset() {
		// Default enabled logs: DEBUG | INFO | WARN | ERROR
		Logger.reset();
		assertLogEnabled(Logger.DEBUG);
		assertLogEnabled(Logger.INFO);
		assertLogEnabled(Logger.WARN);
		assertLogEnabled(Logger.ERROR);
		assertLogDisabled(Logger.VERBOSE);
		assertLogDisabled(Logger.WTF);
	}

	public void testEnable() {
		Logger.disableAll();
		testEnableLog(Logger.VERBOSE);
		testEnableLog(Logger.DEBUG);
		testEnableLog(Logger.INFO);
		testEnableLog(Logger.WARN);
		testEnableLog(Logger.ERROR);
		testEnableLog(Logger.WTF);
	}

	private void testEnableLog(int log) {
		Logger.enable(log);
		assertLogEnabled(log);
	}

	public void testDisable() {
		Logger.enableAll();
		testDisableLog(Logger.VERBOSE);
		testDisableLog(Logger.DEBUG);
		testDisableLog(Logger.INFO);
		testDisableLog(Logger.WARN);
		testDisableLog(Logger.ERROR);
		testDisableLog(Logger.WTF);
	}

	private void testDisableLog(int log) {
		Logger.disable(log);
		assertLogDisabled(log);
	}

	public void testEnableAll() {
		Logger.enableAll();
		assertLogEnabled(Logger.VERBOSE | Logger.DEBUG | Logger.INFO | Logger.WARN | Logger.ERROR | Logger.WTF);
	}

	public void testDisableAll() {
		Logger.disableAll();
		assertLogDisabled(Logger.VERBOSE | Logger.DEBUG | Logger.INFO | Logger.WARN | Logger.ERROR | Logger.WTF);
	}

	public void testSwitchToDebugMode() {
		// Debug mode enabled logs: VERBOSE | DEBUG | INFO | WARN | ERROR
		Logger.switchToDebugMode();
		assertLogEnabled(Logger.VERBOSE | Logger.DEBUG | Logger.INFO | Logger.WARN | Logger.ERROR);
		assertLogDisabled(Logger.WTF);
	}

	public void testSwitchToReleaseMode() {
		// Release mode enabled logs: INFO | WARN | ERROR
		Logger.switchToReleaseMode();
		assertLogEnabled(Logger.INFO | Logger.WARN | Logger.ERROR);
		assertLogDisabled(Logger.VERBOSE | Logger.DEBUG | Logger.WTF);
	}

	private static void assertLogEnabled(int log) {
		assertTrue(Logger.isEnabled(log));
	}

	private static void assertLogDisabled(int log) {
		assertFalse(Logger.isEnabled(log));
	}
}