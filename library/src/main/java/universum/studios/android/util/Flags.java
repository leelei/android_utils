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

import android.support.annotation.IntRange;

/**
 * This class represents a crate to store a set of integer flags instead of a boolean flags to improve
 * memory efficiency of an Android application.
 * <p>
 * <b>Note</b>, that usage of this class to store {@code int} flags instead of boolean flags is
 * efficient (in case of memory usage) only if you need to store and handle more than <b>4</b> boolean
 * flags. It's because, the size of this object will be "always" <b>16 bytes</b> and at the other hand
 * the size of each of boolean flag is <b>4 bytes</b> so if you have for example <b>8</b> booleans
 * to handle some states, these boolean fields will together use <b>24 bytes</b> of an available
 * memory.
 * <p>
 * Of course, significant memory savings can be visible only in case when this class is used within
 * the context of a class of which instances are in an android application used and instantiated in
 * large amount, like hundreds or thousands of instances and more.
 *
 * @author Martin Albedinsky
 */
public class Flags {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "Flags";

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * The int value holding a set of flags.
	 */
	private int mFlags;

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * Creates a new instance of Flags with current flags initialized to {@code 0}.
	 */
	public Flags() {
		this(0);
	}

	/**
	 * Creates a new instance of Flags with the initial <var>flags</var> value.
	 *
	 * @param flags The initial value for flags.
	 */
	public Flags(@IntRange(from = 0, to = Integer.MAX_VALUE) int flags) {
		this.mFlags = flags;
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Adds the specified <var>flag</var> to the current ones (if not presented yet).
	 *
	 * @param flag The desired flag to add.
	 * @see #has(int)
	 * @see #get()
	 */
	public void add(@IntRange(from = 1, to = Integer.MAX_VALUE) int flag) {
		this.mFlags |= flag;
	}

	/**
	 * Removes the specified <var>flag</var> from the current ones (if presented).
	 *
	 * @param flag The desired flag to remove.
	 * @see #has(int)
	 * @see #get()
	 */
	public void remove(@IntRange(from = 1, to = Integer.MAX_VALUE) int flag) {
		this.mFlags &= ~flag;
	}

	/**
	 * Checks whether the requested <var>flag</var> is presented within the current flags or not.
	 *
	 * @param flag The desired flag to check.
	 * @return {@code True} if flag is presented, {@code false} otherwise.
	 * @see #add(int)
	 * @see #remove(int)
	 * @see #get()
	 */
	public boolean has(@IntRange(from = 1, to = Integer.MAX_VALUE) int flag) {
		return (mFlags & flag) != 0;
	}

	/**
	 * Returns a value of the current flags.
	 *
	 * @return The current flags value.
	 */
	@IntRange(from = 0, to = Integer.MAX_VALUE)
	public int get() {
		return mFlags;
	}

	/**
	 * Resets value of this flags to {@code 0}.
	 */
	public void reset() {
		this.mFlags = 0;
	}

	/**
	 * Inner classes ===============================================================================
	 */
}
