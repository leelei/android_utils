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

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Size;

/**
 * Helper class that can be used for permissions checking. Check for a single permission can be done
 * via {@link #has(Context, String)} and for set of permissions can be used {@link #hasAllOf(Context, String...)}
 * or {@link #hasAnyOf(Context, String...)} methods.
 *
 * @author Martin Albedinsky
 */
public final class Permissions {

	/**
	 * Checks whether any of the specified <var>permissions</var> is granted for the current process
	 * and user id.
	 *
	 * @param context     Context used to check permission.
	 * @param permissions The desired set of permissions to check if they are granted.
	 * @return {@code True} if at least one of the specified permissions is granted, {@code false}
	 * otherwise.
	 * @see #has(Context, String)
	 * @see #hasAllOf(Context, String...)
	 */
	@CheckResult
	public static boolean hasAnyOf(@NonNull Context context, @NonNull @Size(min = 1) String... permissions) {
		for (String permission : permissions) {
			if (has(context, permission)) return true;
		}
		return false;
	}

	/**
	 * Checks whether all of the specified <var>permissions</var> are granted for the current process
	 * and user id.
	 *
	 * @param context     Context used to check permission.
	 * @param permissions The desired set of permissions to check if they are granted.
	 * @return {@code True} if all of the specified permissions are granted, {@code false} otherwise.
	 * @see #has(Context, String)
	 * @see #hasAnyOf(Context, String...)
	 */
	@CheckResult
	public static boolean hasAllOf(@NonNull Context context, @NonNull @Size(min = 1) String... permissions) {
		for (String permission : permissions) {
			if (!has(context, permission)) return false;
		}
		return true;
	}

	/**
	 * Checks whether the specified <var>permission</var> is granted for the current process and
	 * user id.
	 *
	 * @param context    Context used to check permission.
	 * @param permission The desired permission to check if it is granted.
	 * @return {@code True} if the specified permission is granted, {@code false} otherwise.
	 * @see Context#checkPermission(String, int, int)
	 * @see Manifest.permission
	 */
	@CheckResult
	public static boolean has(@NonNull Context context, @NonNull String permission) {
		return context.checkPermission(permission, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED;
	}
}
