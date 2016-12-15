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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A {@link RuntimeException} implementation that may be used to dispatch errors occurred during
 * normal execution. A specific error can be identified by its code that can be supplied to each
 * of constructors provided by this exception class. The specified error code can be than obtained
 * via {@link #getCode()}.
 * <p>
 * This exception implementation supports also other common exception related parameters like
 * <b>message</b> and <b>cause</b>.
 *
 * @author Martin Albedinsky
 */
public class ErrorException extends RuntimeException {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	@SuppressWarnings("unused")
	// private static final String TAG = "ErrorException";

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * The error code specified for this error exception.
	 */
	private final int code;

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * Same as {@link #ErrorException(int, String)} with empty <var>message</var>.
	 */
	public ErrorException(int code) {
		this(code, "");
	}

	/**
	 * Same as {@link #ErrorException(int, String, Throwable)} with {@code null} throwable <var>cause</var>.
	 */
	public ErrorException(int code, @Nullable String message) {
		this(code, message, null);
	}

	/**
	 * Same as {@link #ErrorException(int, String, Throwable)} with empty <var>message</var>.
	 */
	public ErrorException(int code, @Nullable Throwable cause) {
		this(code, "", cause);
	}

	/**
	 * Creates a new instance of ErrorException with the specified error <var>code</var>, <var>message</var>
	 * and <var>cause</var>.
	 *
	 * @param code    The desired error code for the error exception.
	 * @param message The desired message for the error exception. May be {@code null}.
	 * @param cause   The desired throwable cause for the error exception. May be {@code null}.
	 */
	public ErrorException(int code, @Nullable String message, @Nullable Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Creates a new instance of ErrorException with the specified error <var>code</var>.
	 *
	 * @param code The desired error code with which to create the error exception.
	 * @return New ErrorException with the specified error code.
	 */
	@NonNull
	public static ErrorException withCode(int code) {
		return new ErrorException(code);
	}

	/**
	 * Returns the error code specified for this error exception.
	 *
	 * @return This exception's error code.
	 */
	public final int getCode() {
		return code;
	}

	/**
	 * Inner classes ===============================================================================
	 */
}
