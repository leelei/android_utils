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
package universum.studios.android.util.inner;

import android.app.Application;
import android.content.res.Configuration;
import android.support.annotation.NonNull;

import java.util.Locale;

import universum.studios.android.util.Localer;

/**
 * @author Martin Albedinsky
 */
public final class TestApplication extends Application {

	@SuppressWarnings("unused")
	private static final String TAG = "TestApplication";

	private final Localer LOCALER = new Localer(Locale.US);

	@Override
	public void onCreate() {
		super.onCreate();
		LOCALER.dispatchApplicationCreated(getResources());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LOCALER.dispatchConfigurationChanged(newConfig, getResources());
	}

	public void changeLocale(@NonNull Locale locale) {
		LOCALER.setLocale(locale, getResources());
	}
}
