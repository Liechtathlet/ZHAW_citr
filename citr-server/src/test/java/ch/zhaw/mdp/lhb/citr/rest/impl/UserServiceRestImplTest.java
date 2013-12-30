/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import ch.zhaw.mdp.lhb.citr.jpa.service.UserRepository;

/**
 * @author Daniel Brun
 * 
 *         Unit-Test for {@link UserServicesRestImplTest}.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceRestImplTest {

    @Mock
    private UserRepository mockDBUserService;

    @Mock
    private ReloadableResourceBundleMessageSource messageSource;
    
    @InjectMocks
    private UserServiceRestImpl userServiceRest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	/*when(mockDBUserService.getById(1)).thenReturn(
		UserTestFactory.createUserDVO(1, "hamu", "hamu@gmail.com"));

	when(messageSource.getMessage("msg.user.auth.failed", null, Locale.GERMAN))
		.thenReturn(
			"Die Kombination aus Benutzername und Passwort konnte nicht gefunden werden.");
	when(
		messageSource.getMessage("msg.user.auth.succ",
			new String[] { "hamu" }, null)).thenReturn(
		"Willkomen hamu!");
	when(messageSource.getMessage("msg.user.noPermission", null, null))
		.thenReturn(
			"Sie verfügen nicht über ausreichend Berechtigungen, um diese Benutzer-Daten einzusehen.");*/
    }

    /**
     * Test method for {@link ch.zhaw.mdp.lhb.citr.rest.impl.UserServiceRestImpl#loginUser(java.lang.String)}.
     */
    @Test
    public void testLoginUser() {
	/*ResponseObject<UserDTO> resp = userServiceRest.loginUser("hamu");

	assertNotNull(resp);
	assertTrue(resp.isSuccessfull());
	assertEquals(UserTestFactory.createUserDTO("hamu", "hamu@gmail.com"),
		resp);*/
    }

    /**
     * Test method for {@link ch.zhaw.mdp.lhb.citr.rest.impl.UserServiceRestImpl#registerUser(ch.zhaw.mdp.lhb.citr.dto.UserDTO)}.
     */
    @Test
    public void testRegisterUser() {
	//fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link ch.zhaw.mdp.lhb.citr.rest.impl.UserServiceRestImpl#getGroups()}.
     */
    @Test
    public void testGetGroups() {
	//fail("Not yet implemented"); // TODO
    }

}
