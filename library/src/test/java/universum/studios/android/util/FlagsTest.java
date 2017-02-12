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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Martin Albedinsky
 */
public final class FlagsTest {

	@SuppressWarnings("unused")
	private static final String TAG = "FlagsTest";

	@Test
	public void testInstantiation() {
		assertThat(new Flags().get(), is(0x00000000));
	}

	@Test
	public void testInstantiationWithInitialValue() {
		assertThat(new Flags(0x00000001 << 2).get(), is(0x00000001 << 2));
	}

	@Test
	public void testHasWithInitialValue() {
		assertThat(new Flags(0x00000001 << 2).has(0x00000001 << 1), is(false));
		assertThat(new Flags(0x00000001 << 2).has(0x00000001 << 2), is(true));
	}

	@Test
	public void testHasWithAdd() {
		final Flags flags = new Flags();
		flags.add(0x00000001 << 2);
		assertThat(flags.has(0x00000001 << 2), is(true));
	}

	@Test
	public void testHasWithRemove() {
		final Flags flags = new Flags();
		flags.add(0x00000001 << 2);
		flags.add(0x00000001 << 4);
		assertThat(flags.has(0x00000001 << 2), is(true));
		assertThat(flags.has(0x00000001 << 4), is(true));
		flags.remove(0x00000001 << 2);
		assertThat(flags.has(0x00000001 << 2), is(false));
		assertThat(flags.has(0x00000001 << 4), is(true));
	}
}