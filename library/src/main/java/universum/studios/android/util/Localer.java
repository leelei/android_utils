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

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * The Localer class provides API allowing to set up custom {@link Locale} for an Android
 * application. Which locale should be used can be specified via {@link #setLocale(Locale, Resources)}.
 * The specified locale will be set up as default locale via {@link Locale#setDefault(Locale)}
 * and also will be used to update the configuration of the Android application resources so also
 * strings from resources will be properly obtained based on the specified locale.
 * <p>
 * The best place where to place Localer is within custom {@link android.app.Application} implementation
 * as shown in the following sample:
 * <pre>
 * public final class LocalizedApplication extends Application {
 *
 *      // Localer instance used to update locale of this Android application.
 *      private Localer LOCALER = new Localer(Locale.FRENCH);
 *
 *      &#64;Override
 *      public void onCreate() {
 *          super.onCreate();
 *          LOCALER.dispatchApplicationCreated(getResources());
 *      }
 *
 *      &#64;Override
 *      public void onConfigurationChanged(Configuration newConfig) {
 *          super.onConfigurationChanged(newConfig);
 *          LOCALER.dispatchConfigurationChanged(newConfig, getResources());
 *      }
 *
 *      // Changes the current application locale to the specified one.
 *      public void changeLocale(@NonNull Locale locale) {
 *          LOCALER.setLocale(locale, getResources());
 *          // Note, that the call above will change default locale and also locale of this Android
 *          // application resources, unfortunately, if you want to this change be presented also in
 *          // the currently running UI (activity), such an UI needs to be recreated so a new values
 *          // from resources based on the new locale can be loaded properly.
 *      }
 * }
 * </pre>
 *
 * @author Martin Albedinsky
 */
public class Localer {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = " Localer";

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * An instance of locale used as default locale for this Android application. All locale related
	 * stuffs will depends on this locale.
	 */
	private Locale mLocale;

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * Creates a new instance of Localer to manage custom locale within this Android application with
	 * default Locale set to {@link Locale#ENGLISH}.
	 */
	public Localer() {
		this(Locale.ENGLISH);
	}

	/**
	 * Creates a new instance of Localer to manage custom locale within this Android application with
	 * the given default Locale.
	 *
	 * @param locale The desired locale.
	 * @see #setLocale(Locale, Resources)
	 */
	public Localer(@NonNull Locale locale) {
		this.mLocale = locale;
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Called from application's {@link android.app.Application#onCreate()} to dispatch, that application
	 * was just created.
	 *
	 * @param resources An application resources.
	 * @see #dispatchConfigurationChanged(Configuration, Resources)
	 */
	public void dispatchApplicationCreated(@NonNull Resources resources) {
		this.updateLocale(resources.getConfiguration(), resources);
	}

	/**
	 * Called from application's {@link android.app.Application#onConfigurationChanged(Configuration)}
	 * to dispatch changed configuration.
	 *
	 * @param newConfig An instance of changed configuration.
	 * @param resources An application resources.
	 * @see #dispatchApplicationCreated(Resources)
	 */
	public void dispatchConfigurationChanged(@NonNull Configuration newConfig, @NonNull Resources resources) {
		this.updateLocale(new Configuration(newConfig), resources);
	}

	/**
	 * Sets the given locale to be used as locale for this Android application, so all stuffs related
	 * to locale, like resources will depends on the passed Locale.
	 * <p>
	 * <b>Note, that this does not reload the currently running UI (activity/-ies) so the locale
	 * change will be not visible in the UI until it is re-created so a new values from resources
	 * based on the changed locale can be properly loaded again.</b>
	 *
	 * @param locale    An instance of locale to be used as locale for this Android application.
	 * @param resources An application resources.
	 */
	public void setLocale(@NonNull Locale locale, @NonNull Resources resources) {
		this.mLocale = locale;
		this.updateLocale(resources.getConfiguration(), resources);
	}

	/**
	 * Returns the current locale assigned to this Localer.
	 *
	 * @return An instance of Locale or {@code null} if there is no locale assigned.
	 */
	@NonNull
	public Locale getLocale() {
		return mLocale;
	}

	/**
	 * Updates the given configuration with the current locale instance and also dispatches locale
	 * change to the passed <var>resources</var>.
	 *
	 * @param config    An instance of configuration where should be locale updated to the current one.
	 * @param resources An application resources.
	 */
	private void updateLocale(Configuration config, Resources resources) {
		// Set up default locale just for this application.
		Locale.setDefault(mLocale);
		// Update configuration locale.
		config.locale = mLocale;
		// Dispatch also to resources so any string related stuff will work properly.
		resources.updateConfiguration(config, resources.getDisplayMetrics());
	}

	/**
	 * Inner classes ===============================================================================
	 */
}
