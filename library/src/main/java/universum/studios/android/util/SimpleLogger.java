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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * A simple implementation of {@link Logger} which delegates all its calls to {@link Log} class.
 *
 * @author Martin Albedinsky
 */
public class SimpleLogger implements Logger {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Flag indicating whether the WTF logging is supported by the current Android version or not.
	 */
	private static final boolean WTF_SUPPORTED = Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Logging level for this logger.
	 */
	@Level
	private int mLogLevel;

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * Creates a new instance of SimpleLogger with the specified log level.
	 *
	 * @param level The initial logging level for the logger.
	 * @see #setLogLevel(int)
	 * @see #getLogLevel()
	 */
	public SimpleLogger(@Level int level) {
		this.mLogLevel = level;
	}

	/**
	 * Methods =====================================================================================
	 */

	@Override
	public void setLogLevel(@Level int level) {
		this.mLogLevel = level;
	}

	/**
	 */
	@Level
	@Override
	public int getLogLevel() {
		return mLogLevel;
	}

	/**
	 */
	@Override
	public boolean isLoggable(@NonNull String tag, int level) {
		return mLogLevel <= level;
	}

	/**
	 */
	@Override
	public void d(@NonNull String tag, @NonNull String msg) {
		if (isLoggable(tag, Log.DEBUG)) Log.d(tag, msg);
	}

	/**
	 */
	@Override
	public void d(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if (isLoggable(tag, Log.DEBUG)) Log.d(tag, msg, tr);
	}

	/**
	 */
	@Override
	public void v(@NonNull String tag, @NonNull String msg) {
		if (isLoggable(tag, Log.VERBOSE)) Log.v(tag, msg);
	}

	/**
	 */
	@Override
	public void v(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if (isLoggable(tag, Log.VERBOSE)) Log.v(tag, msg, tr);
	}

	/**
	 */
	@Override
	public void i(@NonNull String tag, @NonNull String msg) {
		if (isLoggable(tag, Log.INFO)) Log.i(tag, msg);
	}

	/**
	 */
	@Override
	public void i(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if (isLoggable(tag, Log.INFO)) Log.i(tag, msg, tr);
	}

	/**
	 */
	@Override
	public void w(@NonNull String tag, @NonNull String msg) {
		if (isLoggable(tag, Log.WARN)) Log.w(tag, msg);
	}

	/**
	 */
	@Override
	public void w(@NonNull String tag, @Nullable Throwable tr) {
		if (isLoggable(tag, Log.WARN)) Log.w(tag, tr);
	}

	/**
	 */
	@Override
	public void w(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if (isLoggable(tag, Log.WARN)) Log.w(tag, msg, tr);
	}

	/**
	 */
	@Override
	public void e(@NonNull String tag, @NonNull String msg) {
		if (isLoggable(tag, Log.ERROR)) Log.e(tag, msg);
	}

	/**
	 */
	@Override
	public void e(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if (isLoggable(tag, Log.ERROR)) Log.e(tag, msg, tr);
	}

	/**
	 */
	@Override
	@SuppressLint("NewApi")
	public void wtf(@NonNull String tag, @NonNull String msg) {
		if (WTF_SUPPORTED) Log.wtf(tag, msg);
	}

	/**
	 */
	@Override
	@SuppressLint("NewApi")
	public void wtf(@NonNull String tag, @Nullable Throwable tr) {
		if (WTF_SUPPORTED) Log.wtf(tag, tr);
	}

	/**
	 */
	@Override
	@SuppressLint("NewApi")
	public void wtf(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {
		if (WTF_SUPPORTED) Log.wtf(tag, msg, tr);
	}

	/**
	 */
	@Override
	public void log(@Level int level, @NonNull String tag, @NonNull String msg) {
		if (isLoggable(tag, level)) forceLog(level, tag, msg);
	}

	/**
	 */
	@Override
	public void forceLog(@Level int level, @NonNull String tag, @NonNull String msg) {
		Log.println(level, tag, msg);
	}

	/**
	 */
	@Override
	@Nullable
	public String getStackTraceString(@Nullable Throwable tr) {
		return Log.getStackTraceString(tr);
	}

	/**
	 * Inner classes ===============================================================================
	 */
}
