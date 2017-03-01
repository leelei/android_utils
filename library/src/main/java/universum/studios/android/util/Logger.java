/*
 * =================================================================================================
 *                             Copyright (C) 2017 Universum Studios
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

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Interface for console loggers.
 *
 * @author Martin Albedinsky
 */
public interface Logger {

	/**
	 * Defines an annotation for available log levels.
	 */
	@IntDef({
			Log.VERBOSE,
			Log.DEBUG,
			Log.INFO,
			Log.WARN,
			Log.ERROR,
			Log.ASSERT
	})
	@Retention(RetentionPolicy.SOURCE)
	@SuppressWarnings("UnnecessaryInterfaceModifier")
	public @interface Level {
	}

	/**
	 * Sets a logging level for this logger.
	 *
	 * @param level The desired log level. Should be one of log levels defined in {@link Log} class.
	 * @see #getLogLevel()
	 * @see #isLoggable(String, int)
	 */
	void setLogLevel(@Level int level);

	/**
	 * Returns the logging level specified for this logger.
	 *
	 * @return This logger's logging level.
	 * @see #setLogLevel(int)
	 * @see #isLoggable(String, int)
	 */
	@Level
	int getLogLevel();

	/**
	 * Checks whether a log output for the specified <var>level</var> may be logged or not.
	 *
	 * @return {@code True} if the specified level is loggable, {@code false} otherwise.
	 */
	boolean isLoggable(@NonNull String tag, @Level int level);

	/**
	 * Delegates to {@link Log#d(String, String)} if {@link Log#DEBUG} level is loggable at the time.
	 */
	void d(@NonNull String tag, @NonNull String msg);

	/**
	 * Delegates to {@link Log#d(String, String, Throwable)} if {@link Log#DEBUG} level is loggable
	 * at the time.
	 */
	void d(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr);

	/**
	 * Delegates to {@link Log#v(String, String)} if {@link Log#VERBOSE} level is loggable at the time.
	 */
	void v(@NonNull String tag, @NonNull String msg);

	/**
	 * Delegates to {@link Log#v(String, String, Throwable)} if {@link Log#VERBOSE} level is loggable
	 * at the time.
	 */
	void v(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr);

	/**
	 * Delegates to {@link Log#i(String, String)} if {@link Log#INFO} level is loggable at the time.
	 */
	void i(@NonNull String tag, @NonNull String msg);

	/**
	 * Delegates to {@link Log#i(String, String, Throwable)} if {@link Log#INFO} level is loggable
	 * at the time.
	 */
	void i(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr);

	/**
	 * Delegates to {@link Log#w(String, String)} if {@link Log#WARN} level is loggable at the time.
	 */
	void w(@NonNull String tag, @NonNull String msg);

	/**
	 * Delegates to {@link Log#w(String, Throwable)} if {@link Log#WARN} level is loggable at the time.
	 */
	void w(@NonNull String tag, @Nullable Throwable tr);

	/**
	 * Delegates to {@link Log#w(String, String, Throwable)} if {@link Log#WARN} level is loggable
	 * at the time.
	 */
	void w(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr);

	/**
	 * Delegates to {@link Log#e(String, String)} if {@link Log#ERROR} level is loggable at the time.
	 */
	void e(@NonNull String tag, @NonNull String msg);

	/**
	 * Delegates to {@link Log#e(String, String, Throwable)} if {@link Log#ERROR} level is loggable
	 * at the time.
	 */
	void e(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr);

	/**
	 * Delegates to {@link Log#wtf(String, String)}.
	 */
	void wtf(@NonNull String tag, @NonNull String msg);

	/**
	 * Delegates to {@link Log#wtf(String, Throwable)}.
	 */
	void wtf(@NonNull String tag, @Nullable Throwable tr);

	/**
	 * Delegates to {@link Log#wtf(String, String, Throwable)}.
	 */
	void wtf(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr);

	/**
	 * Delegates to {@link #forceLog(int, String, String)} if the specified <var>level</var> is
	 * loggable at the time.
	 */
	void log(@Level int level, @NonNull String tag, @NonNull String msg);

	/**
	 * Delegates to {@link Log#println(int, String, String)} with the specified parameters.
	 */
	void forceLog(@Level int level, @NonNull String tag, @NonNull String msg);

	/**
	 * Delegates to {@link Log#getStackTraceString(Throwable)}.
	 */
	@Nullable
	String getStackTraceString(@Nullable Throwable tr);
}
