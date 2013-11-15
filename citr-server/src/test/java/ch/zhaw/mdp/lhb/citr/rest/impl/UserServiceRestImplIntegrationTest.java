/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.zhaw.mdp.lhb.citr.rest.IRUserServices;

/**
 * @author Daniel Brun
 * 
 *         Integration-Test for {@link UserServicesRestImplTest}.
 */
//extends AbstractJpaTests 
//Testproject: https://anonsvn.springframework.org/svn/spring-samples/petclinic/branches/michaelGitMigration
//Spring-Testing-Example: http://stackoverflow.com/questions/15437310/spring-data-service-layer-unit-testing
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class UserServiceRestImplIntegrationTest { 

    @InjectMocks
    private IRUserServices userServiceRest;

    /**
     * Test method for {@link ch.zhaw.mdp.lhb.citr.rest.impl.UserServiceRestImpl#loginUser(java.lang.String)}.
     */
    @Test
    public void testLoginUser() {
	//fail("Not yet implemented"); // TODO
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
