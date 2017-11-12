/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Paul
 */
public class EditBudgetFrame extends UIFrame {

    public EditBudgetFrame(JPanel inf_panel)
    {
        super(inf_panel);
 
        setSize(new Dimension(640,480));
        
        this.setLocationRelativeTo(null);
    }

}
