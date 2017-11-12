/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financebudgetapp.Database;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Paul
 */
public class SQLDBConnectTest {
    
    public SQLDBConnectTest() {
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
     * Test of connect method, of class SQLDBConnect.
     * Throws exception with DB connection
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("Testing connect");
        SQLDBConnect dbConnect = new SQLDBConnect();
        Connection connection = dbConnect.connect();
        boolean result = connection.isClosed();
        boolean expResult = false;
        assertEquals(expResult, result);
        
    }

    /**
     * Test of close method, of class SQLDBConnect.
     */
    @Test
    public void testClose() throws Exception{
        System.out.println("Testing close connection");
        SQLDBConnect dbConnect = new SQLDBConnect();
        Connection connection = dbConnect.connect();
        //dbConnect.close();
        
        boolean result = connection.isClosed();
        boolean expResult = true;
        assertEquals(expResult, result);

    }
    
}
