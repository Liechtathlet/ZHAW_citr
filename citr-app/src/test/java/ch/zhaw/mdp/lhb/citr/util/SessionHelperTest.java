package ch.zhaw.mdp.lhb.citr.util;

import android.content.SharedPreferences;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import android.content.Context;
import static org.mockito.Mockito.*;

public class SessionHelperTest {
	@Test
	public void testSetPreference() throws Exception {
		// Testdata
		String key = "key";
		String value = "value";

		// Mocks
		Context ctx = mock(Context.class);
		SharedPreferences sp = mock(SharedPreferences.class);
		SharedPreferences.Editor e = mock(SharedPreferences.Editor.class);

		// Stubbing
		when(ctx.getSharedPreferences("citr-prefs", 0)).thenReturn(sp);
		when(sp.edit()).thenReturn(e);

		// Calling code.
		SessionHelper sut = new SessionHelper(ctx);
		sut.setPreference(key, value);

		// Verification.
		verify(e, times(1)).putString(key, value);
	}

	@Test
	public void testGetPreferenceDefaultNull() throws Exception {
		// Testdata
		String key = "key";
		String value = "value";

		// Mocks
		Context ctx = mock(Context.class);
		SharedPreferences sp = mock(SharedPreferences.class);

		// Stubbing
		when(ctx.getSharedPreferences("citr-prefs", 0)).thenReturn(sp);
		when(sp.getString(key, null)).thenReturn(value);

		// Calling code.
		SessionHelper sut = new SessionHelper(ctx);

		// Verification.
		org.junit.Assert.assertEquals(value, sut.getPreferenceDefaultNull(key));
	}
}
