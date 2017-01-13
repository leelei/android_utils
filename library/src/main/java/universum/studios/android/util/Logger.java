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

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Logging utility class. Uses all log methods from {@link Log}. To enable or disable
 * specific logging you can call {@link Logger#enable(int)}, {@link Logger#disable(int)}, like:
 * <pre>
 * // ...
 *
 * // Enable only DEBUG and INFO logging.
 * Logger.disableAll();
 * Logger.enable(Logger.DEBUG | Logger.INFO);
 *
 * // ...
 * </pre>
 * to match your specific logging requirements.
 *
 * <h3>Default enabled logs</h3>
 * {@link Logger#DEBUG}, {@link Logger#INFO}, {@link Logger#WARN}, {@link Logger#ERROR}
 *
 * @author Martin Albedinsky
 */
public class Logger {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Defines an annotation for determining set of allowed log flags for {@code enable(...)},
	 * {@code disable(...)} methods.
	 */
	@Retention(RetentionPolicy.SOURCE)
	@IntDef(flag = true, value = {VERBOSE, DEBUG, INFO, ERROR, WARN, WTF})
	public @interface LogType {
	}

	/**
	 * Private flag indicating no enabled log output.
	 */
	private static final int NONE = 0;

	/**
	 * The flag to enable/disable log output for the VERBOSE logging. See {@link #v(String, String)},
	 * {@link #v(String, String, Throwable)} for more info.
	 */
	public static final int VERBOSE = 0x00000001;

	/**
	 * The flag to enable/disable log output for the DEBUG logging. See {@link #d(String, String)},
	 * {@link #d(String, String, Throwable)} for more info.
	 */
	public static final int DEBUG = 0x00000001 << 1;

	/**
	 * The flag to enable/disable log output for the INFO logging. See {@link #i(String, String)},
	 * {@link #i(String, String, Throwable)} for more info.
	 */
	public static final int INFO = 0x00000001 << 2;

	/**
	 * The flag to enable/disable log output for the WARN logging. See {@link #w(String, String)},
	 * {@link #w(String, Throwable)}, {@link #w(String, String, Throwable)} for more info.
	 */
	public static final int WARN = 0x00000001 << 3;

	/**
	 * The flag to enable/disable log output for the ERROR logging. See {@link #e(String, String)},
	 * {@link #e(String, String, Throwable)} for more info.
	 */
	public static final int ERROR = 0x00000001 << 4;

	/**
	 * The flag to enable/disable log output for the WTF logging. See {@link #wtf(String, String)},
	 * {@link #wtf(String, Throwable)}, {@link #wtf(String, String, Throwable)} for more info.
	 */
	public static final int WTF = 0x00000001 << 5;

	/**
	 * Private flag containing all log output flags.
	 */
	private static final int ALL = VERBOSE | DEBUG | INFO | WARN | ERROR | WTF;

	/**
	 * Private flag containing log output flags to enable logs specific for the DEBUG mode of an
	 * android application.
	 */
	private static final int DEBUG_MODE = (ALL & ~WTF);

	/**
	 * Private flag containing log output flags to enable logs specific for the RELEASE mode of an
	 * android application.
	 */
	private static final int RELEASE_MODE = INFO | WARN | ERROR;

	/**
	 * Flag indicating whether the WTF logging is supported by the current Android version or not.
	 */
	private static final boolean WTF_SUPPORTED = Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Set of output flags determining which log outputs are enabled.
	 */
	public static int sEnabledLogs;

	static {
		reset();
	}

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * Creating of instances of this class is not allowed.
	 */
	private Logger() {
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Resets the current state of Logger to the default one.
	 */
	public static void reset() {
		sEnabledLogs = DEBUG | INFO | WARN | ERROR;
	}

	/**
	 * Enables the messages output in the log cat for the requested log output type/-s.
	 *
	 * @param logFlag The flag of the desired log output. One of {@link #VERBOSE}, {@link #DEBUG},
	 *                {@link #INFO}, {@link #WARN}, {@link #ERROR}, {@link #WTF} or theirs combination.
	 */
	public static void enable(@LogType int logFlag) {
		sEnabledLogs |= logFlag;
	}

	/**
	 * Disables the messages output in the log cat for the requested log output type/-s.
	 *
	 * @param logFlag The flag of the desired log output. One of {@link #VERBOSE}, {@link #DEBUG},
	 *                {@link #INFO}, {@link #WARN}, {@link #ERROR}, {@link #WTF} or theirs combination.
	 */
	public static void disable(@LogType int logFlag) {
		sEnabledLogs &= ~logFlag;
	}

	/**
	 * Enables the messages output in the log cat for the all log output types.
	 *
	 * @see #disableAll()
	 * @see #isEnabled(int)
	 */
	public static void enableAll() {
		sEnabledLogs = ALL;
	}

	/**
	 * Disables the messages output in the log cat for all log output types.
	 *
	 * @see #disableAll()
	 * @see #isEnabled(int)
	 */
	public static void disableAll() {
		sEnabledLogs = NONE;
	}

	/**
	 * Returns flag indicating whether the log output for the specified <var>logFlag</var> is currently
	 * enabled or not.
	 *
	 * @param logFlag The flag of the desired log output. One of the {@link #VERBOSE}, {@link #DEBUG},
	 *                {@link #INFO}, {@link #WARN}, {@link #ERROR}, {@link #WTF} or theirs combination.
	 * @return {@code True} if the specified log flag is currently presented, {@code false}
	 * otherwise.
	 */
	public static boolean isEnabled(@LogType int logFlag) {
		return (sEnabledLogs & logFlag) != 0;
	}

	/**
	 * Enables all log outputs except {@link #WTF}. All currently enabled logs will be disabled.
	 *
	 * @see #switchToReleaseMode()
	 */
	public static void switchToDebugMode() {
		sEnabledLogs = DEBUG_MODE;
	}

	/**
	 * Enables log output for {@link #INFO}, {@link #WARN}, {@link #ERROR}. All currently enabled
	 * logs will be disabled.
	 *
	 * @see #switchToDebugMode()
	 */
	public static void switchToReleaseMode() {
		sEnabledLogs = RELEASE_MODE;
	}

	/**
	 * Wrapped {@link Log#d(String, String)}.
	 * <p>
	 * Message will be outputted only, if the {@link #DEBUG} flag is set.
	 */
	public static void d(@NonNull String tag, @NonNull String msg) {
		if ((sEnabledLogs & DEBUG) != 0) Log.d(tag, msg);
	}

	/**
	 * Wrapped {@link Log#d(String, String, Throwable)}.
	 * <p>
	 * Message will be outputted only, if the {@link #DEBUG} flag is set.
	 */
	public static void d(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if ((sEnabledLogs & DEBUG) != 0) Log.d(tag, msg, tr);
	}

	/**
	 * Wrapped {@link Log#v(String, String)}.
	 * <p>
	 * Message will be outputted only, if the {@link #VERBOSE} flag is set.
	 */
	public static void v(@NonNull String tag, @NonNull String msg) {
		if ((sEnabledLogs & VERBOSE) != 0) Log.v(tag, msg);
	}

	/**
	 * Wrapped {@link Log#v(String, String, Throwable)}.
	 * <p>
	 * Message will be outputted only, if the {@link #VERBOSE} flag is set.
	 */
	public static void v(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if ((sEnabledLogs & VERBOSE) != 0) Log.v(tag, msg, tr);
	}

	/**
	 * Wrapped {@link Log#i(String, String)}.
	 * <p>
	 * Message will be outputted only, if the {@link #INFO} flag is set.
	 */
	public static void i(@NonNull String tag, @NonNull String msg) {
		if ((sEnabledLogs & INFO) != 0) Log.i(tag, msg);
	}

	/**
	 * Wrapped {@link Log#i(String, String, Throwable)}.
	 * <p>
	 * Message will be outputted only, if the {@link #INFO} flag is set.
	 */
	public static void i(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if ((sEnabledLogs & INFO) != 0) Log.i(tag, msg, tr);
	}

	/**
	 * Wrapped {@link Log#w(String, String)}.
	 * <p>
	 * Message will be outputted only, if the {@link #WARN} flag is set.
	 */
	public static void w(@NonNull String tag, @NonNull String msg) {
		if ((sEnabledLogs & WARN) != 0) Log.w(tag, msg);
	}

	/**
	 * Wrapped {@link Log#w(String, Throwable)}.
	 * <p>
	 * Message will be outputted only, if the {@link #WARN} flag is set.
	 */
	public static void w(@NonNull String tag, @Nullable Throwable tr) {
		if ((sEnabledLogs & WARN) != 0) Log.w(tag, tr);
	}

	/**
	 * Wrapped {@link Log#w(String, String, Throwable)}.
	 * <p>
	 * Message will be outputted only, if the {@link #WARN} flag is set.
	 */
	public static void w(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if ((sEnabledLogs & WARN) != 0) Log.w(tag, msg, tr);
	}

	/**
	 * Wrapped {@link Log#e(String, String)}.
	 * <p>
	 * Message will be outputted only, if the {@link #ERROR} flag is set.
	 */
	public static void e(@NonNull String tag, @NonNull String msg) {
		if ((sEnabledLogs & ERROR) != 0) Log.e(tag, msg);
	}

	/**
	 * Wrapped {@link Log#e(String, String, Throwable)}.
	 * <p>
	 * Message will be outputted only, if the {@link #ERROR} flag is set.
	 */
	public static void e(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if ((sEnabledLogs & ERROR) != 0) Log.e(tag, msg, tr);
	}

	/**
	 * Wrapped {@link Log#wtf(String, String)}.
	 * <p>
	 * Message will be outputted only, if the {@link #WTF} flag is set.
	 */
	@SuppressLint("NewApi")
	public static void wtf(@NonNull String tag, @NonNull String msg) {
		if ((sEnabledLogs & WTF) != 0 && WTF_SUPPORTED) Log.wtf(tag, msg);
	}

	/**
	 * Wrapped {@link Log#wtf(String, Throwable)}.
	 * <p>
	 * Message will be outputted only, if the {@link #WTF} flag is set.
	 */
	@SuppressLint("NewApi")
	public static void wtf(@NonNull String tag, @Nullable Throwable tr) {
		if ((sEnabledLogs & WTF) != 0 && WTF_SUPPORTED) Log.wtf(tag, tr);
	}

	/**
	 * Wrapped {@link Log#wtf(String, String, Throwable)}.
	 * <p>
	 * Message will be outputted only, if the {@link #WTF} flag is set.
	 */
	@SuppressLint("NewApi")
	public static void wtf(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if ((sEnabledLogs & WTF) != 0 && WTF_SUPPORTED) Log.wtf(tag, msg, tr);
	}

	/**
	 * Wrapped {@link Log#isLoggable(String, int)}.
	 */
	public static boolean isLoggable(@NonNull String tag, int level) {
		return Log.isLoggable(tag, level);
	}

	/**
	 * Wrapped {@link Log#getStackTraceString(Throwable)}.
	 */
	public static String getStackTraceString(@Nullable Throwable tr) {
		return Log.getStackTraceString(tr);
	}

	/**
	 * Inner classes ===============================================================================
	 */
}
