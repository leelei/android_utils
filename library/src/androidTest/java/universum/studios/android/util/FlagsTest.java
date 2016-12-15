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
public final class FlagsTest extends TestCase {

	@SuppressWarnings("unused")
	private static final String TAG = "FlagsTest";

	private Flags mFlags;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.mFlags = new Flags();
	}

	public void testInstantiation() {
		final Flags flags = new Flags();
		assertEquals(0, flags.get());
	}

	public void testInstantiationWithInitialValue() {
		final Flags flags = new Flags(6);
		assertEquals(6, flags.get());
	}

	public void testHas() {
		assertFalse(mFlags.has(2));
	}

	public void testHasWithAdd() {
		mFlags.add(2);
		assertTrue(mFlags.has(2));
	}

	public void testHasWithRemove() {
		mFlags.add(2);
		mFlags.add(4);
		assertTrue(mFlags.has(4));
		mFlags.remove(2);
		assertTrue(mFlags.has(4));
	}
}