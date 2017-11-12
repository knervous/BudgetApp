/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.Dimension;
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
public class LoginSystemTest {
    
    public LoginSystemTest() {
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
        LoginSystem instance = new LoginSystem();
        instance.addComponents();
        
        assertTrue(instance.getComponentCount() > 0);
        
    }
    
    @Test
    public void testSetAttributes()
    {
        LoginSystem instance = new LoginSystem();
        instance.setAttributes();
        Dimension d = new Dimension(200,25);
        assertEquals(instance.getLogin().getText(), "Login");
        
        assertTrue(instance.getPassword().getPreferredSize().height == d.height);
        assertTrue(instance.getPassword().getPreferredSize().width == d.width);

    }
    
    @Test
    public void testInit()
    {
        LoginSystem instance = new LoginSystem();
        instance.init();
        
        assertTrue(instance.getLogin() != null);
        
    }
    
}
