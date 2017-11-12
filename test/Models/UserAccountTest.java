
package Models;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Takuya Tokunaga
 */
public class UserAccountTest {
    
    public UserAccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of emailProfileData method, of class UserAccount.
     */
    @Test
    public void testEmailProfileData() {
        System.out.println("Testing method emailProfileData");
        //U//serAccount account = new UserAccount("username", "password", "Natasha", "Bhave", "natasha@psu.edu", "Female", new Date());
        //boolean result = account.emailProfileData();
        
        boolean expResult = false;
        //assertEquals(expResult, result);
        
    }
    
    @Test
    public void testCreationDate() throws Exception
    {
        System.out.println("Testing creation date");
        //UserAccount account = new UserAccount("username", "password", "Natasha", "Bhave", "natasha@psu.edu", "Female", new Date());
        
        Thread.sleep((1000));
        Date testDate = new Date();
        
        //Testing to see if the new date is a larger long value than the date of creation in user account
        //assertNotEquals(account.getCreationDate().getTime(), testDate.getTime());
       // System.out.println("Account creation Time: "+account.getCreationDate());
        System.out.println("Test Time: "+testDate);
        
    }

    
}
