package ch.zhaw.mdp.lhb.citr.util;

import android.content.Context;
import android.content.SharedPreferences;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class SharedPreferencHelperTest {
	@Test
	public void testStoreString() throws Exception {

		// Testdata
		String aPreference = "preference";
		String aKey = "key";
		String aValue = "value";

		// Mocks
		Context ctx = mock(Context.class);
		SharedPreferences sp = mock(SharedPreferences.class);
		SharedPreferences.Editor e = mock(SharedPreferences.Editor.class);

		// Stubbing
		when(ctx.getSharedPreferences(aPreference, 0)).thenReturn(sp);
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

		// Testdata
		String aPreference = "preference";
		String aKey = "key";
		String aValue = "value";

		// Mocks
		Context ctx = mock(Context.class);
		SharedPreferences sp = mock(SharedPreferences.class);

		// Stubbing
		when(ctx.getSharedPreferences(aPreference, 0)).thenReturn(sp);
		when(sp.getString(aKey, null)).thenReturn(aValue);

		// Calling code.
		SharedPreferencHelper sut = new SharedPreferencHelper(ctx);

		// Verification.
		org.junit.Assert.assertEquals(aValue, sut.getString(aPreference, aKey));
	}

	@Test
	public void testDelete() throws Exception {

		// Testdata
		String aPreference = "preference";
		String aKey = "key";
		String aValue = "value";

		// Mocks
		Context ctx = mock(Context.class);
		SharedPreferences sp = mock(SharedPreferences.class);
		SharedPreferences.Editor e = mock(SharedPreferences.Editor.class);

		// Stubbing
		when(ctx.getSharedPreferences(aPreference, 0)).thenReturn(sp);
		when(sp.edit()).thenReturn(e);

		// Calling code.
		SharedPreferencHelper sut = new SharedPreferencHelper(ctx);
		sut.delete(aPreference, aKey);

		// Verification.
		verify(e, times(1)).remove(aKey);
		verify(e, times(1)).commit();
	}
}
