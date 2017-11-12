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
 * @author Michael De Giorgis
 */
public class CreateBudgetTest {
    
    public CreateBudgetTest() {
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
        CreateBudget instance = new CreateBudget();
        instance.addComponents();
        
        assertTrue(instance.getComponentCount() > 0);
        
    }
    
    @Test
    public void testSetAttributes()
    {
        CreateBudget instance = new CreateBudget();
        instance.setAttributes();
        Dimension d = new Dimension(0,0);
        assertEquals(instance.getBack().getText(), "Back");
        
        assertTrue(instance.getCreate().getX() == 530);
        assertTrue(instance.getCreate().getY() == 500);

    }
    
    @Test
    public void testInit()
    {
        CreateBudget instance = new CreateBudget();
        instance.init();
        
        assertTrue(instance.getBack() != null);
        
    }
    
}
