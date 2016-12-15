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

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import universum.studios.android.util.test.R;
import universum.studios.android.util.inner.TestActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * @author Martin Albedinsky
 */
@RunWith(AndroidJUnit4.class)
public final class ToasterTest extends ActivityInstrumentationTestCase2<TestActivity> {

	@SuppressWarnings("unused")
	private static final String TAG = "ToasterTest";

	private static final String TOAST_TEXT = "Text within toast";

	private Instrumentation mInstrumentation;
	TestActivity mActivity;

	public ToasterTest() {
		super(TestActivity.class);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		injectInstrumentation(InstrumentationRegistry.getInstrumentation());
		this.mInstrumentation = getInstrumentation();
		this.mActivity = getActivity();
	}

	@Test
	public void showToastText() {
		showToastAndAssertThatIsDisplayedWithText(new Runnable() {
			@Override
			public void run() {
				Toaster.showToast(mActivity, TOAST_TEXT);
			}
		}, TOAST_TEXT);
	}

	@Test
	public void showToastTextDuration() {
		showToastAndAssertThatIsDisplayedWithText(new Runnable() {
			@Override
			public void run() {
				Toaster.showToast(mActivity, TOAST_TEXT, Toast.LENGTH_SHORT);
			}
		}, TOAST_TEXT);
	}

	@Test
	public void showToastResId() {
		showToastAndAssertThatIsDisplayedWithText(new Runnable() {
			@Override
			public void run() {
				Toaster.showToast(mActivity, R.string.test_toast_text);
			}
		}, R.string.test_toast_text);
	}

	@Test
	public void showToastResIdWithArgs() {
		showToastAndAssertThatIsDisplayedWithText(new Runnable() {
			@Override
			public void run() {
				Toaster.showToast(mActivity, Toast.LENGTH_SHORT, R.string.test_toast_text_with_args, "Large");
			}
		}, mActivity.getString(R.string.test_toast_text_with_args, "Large"));
	}

	@Test
	public void showToastTextWithArgs() {
		showToastAndAssertThatIsDisplayedWithText(new Runnable() {
			@Override
			public void run() {
				Toaster.showToast(mActivity, Toast.LENGTH_SHORT, "%d items has been removed", 10);
			}
		}, "10 items has been removed");
	}

	private void showToastAndAssertThatIsDisplayedWithText(Runnable toastRunnable, int resId) {
		showToastAndAssertThatIsDisplayedWithText(toastRunnable, mActivity.getString(resId));
	}

	private void showToastAndAssertThatIsDisplayedWithText(Runnable toastRunnable, String text) {
		try {
			// Wait a while so the previous toast is properly dismissed.
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mActivity.runOnUiThread(toastRunnable);
		mInstrumentation.waitForIdleSync();
		assertToastIsDisplayedWithText(text);
	}

	private void assertToastIsDisplayedWithText(String text) {
		onView(withText(text))
				.inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
				.check(matches(isDisplayed()));
	}
}