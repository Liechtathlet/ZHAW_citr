/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.zhaw.lhb.citr.helper.UserTestFactory;
import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBUserService;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;

/**
 * @author Daniel Brun
 * 
 *         Unit-Test for {@link UserServicesRestImplTest}.
 */
// @RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "application-test-context.xml" })
public class UserServiceRestImplTest {

    @Mock
    private IDBUserService mockDBUserService;

    @InjectMocks
    @Autowired
    private UserServiceRestImpl userServiceRest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	when(mockDBUserService.getById(1)).thenReturn(
		UserTestFactory.createUserDVO(1, "hamu", "hamu@gmail.com"));
    }

    /**
     * Test method for {@link ch.zhaw.mdp.lhb.citr.rest.impl.UserServiceRestImpl#loginUser(java.lang.String)}.
     */
    @Test
    public void testLoginUser() {
	ResponseObject<UserDTO> resp = userServiceRest.loginUser("hamu");

	assertNotNull(resp);
	assertTrue(resp.isSuccessfull());
	assertEquals(UserTestFactory.createUserDTO("hamu", "hamu@gmail.com"),
		resp);
    }

    /**
     * Test method for {@link ch.zhaw.mdp.lhb.citr.rest.impl.UserServiceRestImpl#registerUser(ch.zhaw.mdp.lhb.citr.dto.UserDTO)}.
     */
    @Test
    public void testRegisterUser() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link ch.zhaw.mdp.lhb.citr.rest.impl.UserServiceRestImpl#getGroups()}.
     */
    @Test
    public void testGetGroups() {
	fail("Not yet implemented"); // TODO
    }

}
