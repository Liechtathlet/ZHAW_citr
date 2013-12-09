package ch.zhaw.mdp.lhb.citr.util;

import android.content.Context;
import android.content.SharedPreferences;
import junit.extensions.TestSetup;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class SharedPreferencHelperTest {

	private final String aPreference = "preference";
	private final String aKey = "key";
	private final String aValue = "value";

	private Context ctx;
	private SharedPreferences sp;

	@Before
	public void Setup() {
		ctx = mock(Context.class);
		sp = mock(SharedPreferences.class);

		when(ctx.getSharedPreferences(aPreference, 0)).thenReturn(sp);
	}

	@Test
	public void testStoreString() throws Exception {

		// Mock
		SharedPreferences.Editor e = mock(SharedPreferences.Editor.class);

		// Stubbing
		when(sp.edit()).thenReturn(e);

		// Calling code.
		SharedPreferencHelper sut = new SharedPreferencHelper(ctx);
		sut.storeString(aPreference, aKey, aValue);

		// Verification.
		verify(e, times(1)).putString(aKey, aValue);
		verify(e, times(1)).commit();
	}

	@Test
	public void testGetString() throws Exception {

		// Stubbing
		when(sp.getString(aKey, null)).thenReturn(aValue);

		// Calling code.
		SharedPreferencHelper sut = new SharedPreferencHelper(ctx);

		// Verification.
		org.junit.Assert.assertEquals(aValue, sut.getString(aPreference, aKey));
	}

	@Test
	public void testDelete() throws Exception {

		// Mock
		SharedPreferences.Editor e = mock(SharedPreferences.Editor.class);

		// Stubbing
		when(sp.edit()).thenReturn(e);

		// Calling code.
		SharedPreferencHelper sut = new SharedPreferencHelper(ctx);
		sut.delete(aPreference, aKey);

		// Verification.
		verify(e, times(1)).remove(aKey);
		verify(e, times(1)).commit();
	}
}
