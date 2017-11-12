/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.awt.event.ActionListener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paul
 */
public class MainControllerTest {
    
    public MainControllerTest() {
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
    public void testAddListeners() {
       MainController controller = new MainController();
       
       controller.setActionListeners();
       
       ActionListener[][] al = { controller.getLoginSystem().getLogin().getActionListeners(),
                                 controller.getLoginSystem().getCreateAccount().getActionListeners(),
                                 controller.getCreateUser().getCancel().getActionListeners(),
                                 controller.getCreateUser().getCreate().getActionListeners(),
                                 controller.getCreateUser().getUpload().getActionListeners()};

       for (ActionListener[] temp : al)
       {
           for (ActionListener temp2 : temp)
           {
               System.out.println("Action Listener found: " + temp2.toString());
           }
       }
       
       assertTrue(al.length > 0);
    }
    
}
