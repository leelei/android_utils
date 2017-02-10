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

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This class wraps {@link Toast} class and its API to provide simpler usage when showing toast messages.
 * A simple message text can be shown via {@link #showToast(Context, CharSequence)}.
 *
 * @author Martin Albedinsky
 */
public final class Toaster {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "Toaster";

	/**
	 * Defines an annotation for determining set of allowed durations for {@code showToast(...)}
	 * methods.
	 */
	@Retention(RetentionPolicy.SOURCE)
	@IntDef({Toast.LENGTH_SHORT, Toast.LENGTH_LONG})
	public @interface Duration {
	}

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Constructors ================================================================================
	 */

	/**
	 */
	private Toaster() {
		// Creation of instances of this class is not publicly allowed.
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Same as {@link #showToast(Context, int, int)} with {@link Toast#LENGTH_SHORT}
	 * duration flag.
	 */
	public static void showToast(@NonNull Context context, @StringRes int resId) {
		showToast(context, Toast.LENGTH_SHORT, resId);
	}

	/**
	 * Shows a default {@link Toast} with the formatted text obtained from an application
	 * resources by the specified <var>resId</var>.
	 *
	 * @param context  Context to use to show the toast.
	 * @param duration The flag to determine, how long should be toast visible. One of the
	 *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
	 * @param resId    The resource id of the desired text to display in the toast.
	 */
	public static void showToast(@NonNull Context context, @Duration int duration, @StringRes int resId) {
		showToast(context, context.getResources().getText(resId), duration);
	}

	/**
	 * Like {@link #showToast(Context, int, String, Object...)}, but the specified
	 * <var>text</var> will be formatted by the given <var>args</var> during of the obtaining process
	 * from an application resources. See {@link android.content.res.Resources#getString(int, Object...)}
	 * for more info.
	 */
	public static void showToast(@NonNull Context context, @Duration int duration, @StringRes int resId, @Nullable Object... args) {
		showToast(context, context.getResources().getString(resId, args), duration);
	}

	/**
	 * Same as {@link #showToast(Context, CharSequence, int)} where the given <var>args</var>
	 * will be used to format the specified <var>text</var>
	 *
	 * @param args The variable arguments to format the specified text.
	 */
	public static void showToast(@NonNull Context context, @Duration int duration, @NonNull String text, @Nullable Object... args) {
		showToast(context, String.format(text, args), duration);
	}

	/**
	 * Same as {@link #showToast(Context, CharSequence, int)} with {@link Toast#LENGTH_SHORT}
	 * duration flag.
	 */
	public static void showToast(@NonNull Context context, @NonNull CharSequence text) {
		showToast(context, text, Toast.LENGTH_SHORT);
	}

	/**
	 * Shows a default {@link Toast} with the formatted <var>text</var>.
	 *
	 * @param context  Context to use to show the toast.
	 * @param text     The desired text to display in the toast.
	 * @param duration The flag to determine, how long should be toast visible. One of the
	 *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
	 */
	public static void showToast(@NonNull Context context, @NonNull CharSequence text, @Duration int duration) {
		showToastInner(context, text, duration);
	}

	/**
	 * Shows a default {@link Toast}.
	 *
	 * @param context  Context to use to show the toast.
	 * @param text     The desired text to display in the toast.
	 * @param duration The flag to determine, how long should be toast visible. One of the
	 *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
	 */
	private static void showToastInner(Context context, CharSequence text, int duration) {
		switch (duration) {
			case Toast.LENGTH_LONG:
				Toast.makeText(context, text, Toast.LENGTH_LONG).show();
				break;
			case Toast.LENGTH_SHORT:
			default:
				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
				break;
		}
	}

	/**
	 * Inner classes ===============================================================================
	 */
}
