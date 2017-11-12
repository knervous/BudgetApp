/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Michael De Giorgis
 */
public class ViewPaneTest {

    public ViewPaneTest() {
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
     * Test of paintComponent method, of class ViewPane.
     */
    @Test
    public void testRootMove() {

    }

    @Test
    public void testRootMoveListener() {
        System.out.println("rootMove LIstener");
        ViewPane instance = new ViewPane();
        instance.repaint();
        ViewPane.RootMover r = instance.getRootMover();

        MouseEvent tempMouseEvent = new MouseEvent(instance, 10, 10L, 10, 10, 10, 10, true);

        r.mousePressed(tempMouseEvent);
        assertEquals(r.mStart, new Point(10, 10));

    }

    @Test
    public void testMouseReleased() {
        System.out.println("rootMove mousereleased");
        ViewPane instance = new ViewPane();
        instance.repaint();
        ViewPane.RootMover r = instance.getRootMover();

        MouseEvent tempMouseEvent = new MouseEvent(instance, 25, 25, 25, 25, 25, 25, true);

        JFrame frame = new JFrame();
        frame.add(instance);

        r.mouseReleased(tempMouseEvent);
        assertEquals(r.mStart, new Point(25, 25));
        
    }
    
    
    @Test
    public void testFindRoot()
    {
        System.out.println("rootMove findRoot");
        ViewPane instance = new ViewPane();
        instance.repaint();
        ViewPane.RootMover r = instance.getRootMover();
        
        JFrame frame = new JFrame();
        frame.add(instance);
        instance.findRoot(instance);
        
        assertEquals(instance.parent, frame);
    }

}
