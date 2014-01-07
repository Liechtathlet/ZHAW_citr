package ch.zhaw.mdp.lhb.citr.util;

import android.content.Context;
import android.content.SharedPreferences;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class SharedPreferencHelperTest {

	private final String aPreference = "preference";
	private final String aKey = "key";
	private final String aString = "value";
	private final int anInt = 123;

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
		sut.storeString(aPreference, aKey, aString);

		// Verification.
		verify(e, times(1)).putString(aKey, aString);
		verify(e, times(1)).commit();
	}

	@Test
	public void testGetString() throws Exception {

		// Stubbing
		when(sp.getString(aKey, null)).thenReturn(aString);

		// Calling code.
		SharedPreferencHelper sut = new SharedPreferencHelper(ctx);

		// Verification.
		org.junit.Assert.assertEquals(aString, sut.getString(aPreference, aKey));
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

	@Test
	public void testStoreInt() throws Exception {

		// Mock
		SharedPreferences.Editor e = mock(SharedPreferences.Editor.class);

		// Stubbing
		when(sp.edit()).thenReturn(e);

		// Calling code.
		SharedPreferencHelper sut = new SharedPreferencHelper(ctx);
		sut.storeInt(aPreference, aKey, anInt);

		// Verification.
		verify(e, times(1)).putInt(aKey, anInt);
		verify(e, times(1)).commit();
	}

	@Test
	public void testGetInt() throws Exception {

		// Stubbing
		when(sp.getInt(aKey, -1)).thenReturn(anInt);

		// Calling code.
		SharedPreferencHelper sut = new SharedPreferencHelper(ctx);

		// Verification.
		org.junit.Assert.assertEquals(anInt, sut.getInt(aPreference, aKey));
	}
}
