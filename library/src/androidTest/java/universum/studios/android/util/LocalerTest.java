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
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

import universum.studios.android.util.inner.ContextBaseTest;
import universum.studios.android.util.inner.TestApplication;
import universum.studios.android.util.test.R;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Martin Albedinsky
 */
@RunWith(AndroidJUnit4.class)
public final class LocalerTest extends ContextBaseTest {

	@SuppressWarnings("unused")
	private static final String TAG = "LocalerTest";

	private static final int LOCALIZED_TEXT_RES = R.string.test_localer_message;
	private static final int LOCALIZED_PLURAL_RES = R.plurals.test_localer_plural;

	private static final Locale LOCALE_SLOVAK = new Locale("sk");

	private TestApplication mApplication;
	private Resources mResources;

	@Override
	public void beforeTest() throws Exception {
		super.beforeTest();
		this.mApplication = (TestApplication) mContext.getApplicationContext();
		this.mResources = mApplication.getResources();
	}

	@Test
	public void testInstantiation() {
		assertThat(new Localer().getLocale(), is(Locale.ENGLISH));
		assertThat(new Localer(Locale.FRENCH).getLocale(), is(Locale.FRENCH));
	}

	@Test
	public void testLocalizedText() {
		mApplication.changeLocale(Locale.ENGLISH);
		assertThat(mResources.getString(LOCALIZED_TEXT_RES), is("Hello Localer test!"));

		mApplication.changeLocale(LOCALE_SLOVAK);
		assertThat(mResources.getString(LOCALIZED_TEXT_RES), is("Ahoj Localer test!"));

		mApplication.changeLocale(Locale.GERMAN);
		assertThat(mResources.getString(LOCALIZED_TEXT_RES), is("Hallo Localer Test!"));

		mApplication.changeLocale(Locale.FRENCH);
		assertThat(mResources.getString(LOCALIZED_TEXT_RES), is("Bonjour Localer essai!"));
	}

	@Test
	public void testLocalizedPlurals() {
		mApplication.changeLocale(Locale.ENGLISH);
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 0, 0), is("0 items"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 1, 1), is("one item"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 2, 2), is("2 items"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 100, 100), is("100 items"));

		mApplication.changeLocale(LOCALE_SLOVAK);
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 0, 0), is("0 položiek"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 1, 1), is("jedna položka"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 2, 2), is("2 položky"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 5, 5), is("5 položiek"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 100, 100), is("100 položiek"));

		mApplication.changeLocale(Locale.GERMAN);
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 1, 1), is("ein Einzelteil"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 2, 2), is("2 Gegenstände"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 3, 3), is("3 Gegenstände"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 100, 100), is("100 Gegenstände"));

		mApplication.changeLocale(Locale.FRENCH);
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 0, 0), is("0 article"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 1, 1), is("1 article"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 2, 2), is("2 articles"));
		assertThat(mResources.getQuantityString(LOCALIZED_PLURAL_RES, 100, 100), is("100 articles"));
	}
}