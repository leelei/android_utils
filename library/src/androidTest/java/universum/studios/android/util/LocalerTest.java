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

import android.content.res.Resources;
import android.test.ApplicationTestCase;

import java.util.Locale;

import universum.studios.android.util.test.R;
import universum.studios.android.util.inner.TestLocalerApplication;

/**
 * @author Martin Albedinsky
 */
public final class LocalerTest extends ApplicationTestCase<TestLocalerApplication> {

	@SuppressWarnings("unused")
	private static final String TAG = "LocalerTest";

	private static final int LOCALIZED_TEXT_RES = R.string.test_localer_message;
	private static final int LOCALIZED_PLURAL_RES = R.plurals.test_localer_plural;

	private static final Locale LOCALE_SLOVAK = new Locale("sk");

	private TestLocalerApplication mApplication;
	private Resources mResources;

	public LocalerTest() {
		super(TestLocalerApplication.class);
	}

	public void testInstantiation() {
		Localer localer = new Localer();
		assertEquals(Locale.ENGLISH, localer.getLocale());

		localer = new Localer(Locale.FRENCH);
		assertEquals(Locale.FRENCH, localer.getLocale());
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		createApplication();
		this.mApplication = getApplication();
		this.mResources = mApplication.getResources();
	}

	public void testLocalizedText() {
		assertEquals("Hello Localer test!", mResources.getString(LOCALIZED_TEXT_RES));

		// Change locale to SLOVAK.
		mApplication.changeLocale(LOCALE_SLOVAK);
		assertEquals("Ahoj Localer test!", mResources.getString(LOCALIZED_TEXT_RES));

		// Change locale to GERMAN.
		mApplication.changeLocale(Locale.GERMAN);
		assertEquals("Hallo Localer Test!", mResources.getString(LOCALIZED_TEXT_RES));

		// Change locale to FRENCH.
		mApplication.changeLocale(Locale.FRENCH);
		assertEquals("Bonjour Localer essai!", mResources.getString(LOCALIZED_TEXT_RES));
	}

	public void testLocalizedPlurals() {
		assertEquals("0 items", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 0, 0));
		assertEquals("one item", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 1, 1));
		assertEquals("2 items", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 2, 2));
		assertEquals("100 items", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 100, 100));

		// Change locale to SLOVAK.
		mApplication.changeLocale(LOCALE_SLOVAK);
		assertEquals("0 položiek", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 0, 0));
		assertEquals("jedna položka", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 1, 1));
		assertEquals("2 položky", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 2, 2));
		assertEquals("3 položky", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 3, 3));
		assertEquals("4 položky", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 4, 4));
		assertEquals("5 položiek", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 5, 5));
		assertEquals("100 položiek", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 100, 100));

		// Change locale to GERMAN.
		mApplication.changeLocale(Locale.GERMAN);
		assertEquals("ein Einzelteil", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 1, 1));
		assertEquals("2 Gegenstände", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 2, 2));
		assertEquals("3 Gegenstände", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 3, 3));

		// Change locale to FRENCH.
		mApplication.changeLocale(Locale.FRENCH);
		assertEquals("0 article", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 0, 0));
		assertEquals("1 article", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 1, 1));
		assertEquals("2 articles", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 2, 2));
		assertEquals("100 articles", mResources.getQuantityString(LOCALIZED_PLURAL_RES, 100, 100));
	}
}