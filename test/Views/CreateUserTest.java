/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Natasha Bhave
 */
public class CreateUserTest {
    
    public CreateUserTest() {
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

    
    @Test
    public void testAddComponents()
    {
        CreateUser instance = new CreateUser();
        instance.addComponents();
        
        assertTrue(instance.getComponentCount() > 0);
        
    }
    
    @Test
    public void testSetAttributes()
    {
        CreateUser instance = new CreateUser();
        instance.setAttributes();
        assertEquals(instance.getCancel().getText(), "Cancel");
        
        assertTrue(instance.getPassword().getX() == 515);
        assertTrue(instance.getPassword().getY() == 91);

    }
    
    @Test
    public void testInit()
    {
        CreateUser instance = new CreateUser();
        instance.init();
        
        assertTrue(instance.getCancel() != null);
        
    }

}
