/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Views.ViewPane;

/**
 *
 * @author Takuya Tokunaga
 */
public class UIFrameTest {
    
    public UIFrameTest() {
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
     * Test of initFrame method, of class UIFrame.
     */
    @Test
    public void testInitFrame() {
        System.out.println("initFrame");
        UIFrame instance = new UIFrame(new JPanel());
        assertTrue(instance.isVisible());

    }

    /**
     * Test of setViewPane method, of class UIFrame.
     */
    @Test
    public void testSetViewPane() {
        System.out.println("setViewPane");
        JPanel value = new JPanel();
        JPanel remove = new JPanel();
        remove.setPreferredSize(new Dimension(500,500));
        UIFrame instance = new UIFrame(remove);

        Component g = instance.getComponents()[0];
        
        
        System.out.println("temp: "+g.toString());
        instance.setViewPane(value, remove);
        
        assertTrue(g instanceof JRootPane);
        


        
        

    }
    
}
