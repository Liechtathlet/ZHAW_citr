package ch.zhaw.mdp.lhb.citr.util;

import android.content.Context;
import android.content.SharedPreferences;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class SessionHelperTest {

	private final String key = "key";
	private final String value = "value";
	private Context ctx;
	private SharedPreferences sp;

	@Before
	public void Setup() {
		ctx = mock(Context.class);
		sp = mock(SharedPreferences.class);

		when(ctx.getSharedPreferences("citr-prefs", 0)).thenReturn(sp);
	}

	@Test
	public void testSetPreference() throws Exception {

		// Mocks
		SharedPreferences.Editor e = mock(SharedPreferences.Editor.class);

		// Stubbing
		when(sp.edit()).thenReturn(e);

		// Calling code.
		SessionHelper sut = new SessionHelper(ctx);
		sut.setPreference(key, value);

		// Verification.
		verify(e, times(1)).putString(key, value);
		verify(e, times(1)).commit();
	}

	@Test
	public void testGetPreferenceDefaultNull() throws Exception {

		// Stubbing
		when(sp.getString(key, null)).thenReturn(value);

		// Calling code.
		SessionHelper sut = new SessionHelper(ctx);

		// Verification.
		org.junit.Assert.assertEquals(value, sut.getPreferenceDefaultNull(key));
	}
}
