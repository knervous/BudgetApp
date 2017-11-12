package Views;

import Views.ViewPane;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Takuya Tokunaga
 */

public class CreateBudget extends ViewPane{
   
    
    private JButton create;
    private JButton back; 
    private JRadioButton weekly;
    private JRadioButton biWeekly;
    private JRadioButton monthly;
    private JTextField budgetName;
    private JTextField budgetAllowance;
    private JCheckBox budgetCategory;

    
    
    public CreateBudget()
    {
        super();
        init();
        setAttributes();
        addComponents();
    }
    
    public void init()
    {

        setBack(new JButton("Back"));
        setCreate(new JButton("Create"));
        setWeekly(new JRadioButton());
        setBiWeekly(new JRadioButton());
        setMonthly(new JRadioButton());
        setBudgetName(new JTextField());
        setBudgetAllowance(new JTextField());
        setBudgetCategory(new JCheckBox());
       
    }
    
    public void setAttributes() {

        setLayout(null);
        
        create.setBounds(530,470,100,30);
        weekly.setBounds(500,240,20,20);
        biWeekly.setBounds(500,280,20,20);
        monthly.setBounds(500,320,20,20);
        budgetName.setBounds(150,150,150,25);
        budgetCategory.setBounds(150,280,150,25);
        budgetAllowance.setBounds(500,150,150,25);
        back.setBounds(150,470,120,30);
    }
   
    public void addComponents() {
        
        add(create);
        add(weekly);
        add(biWeekly);
        add(monthly);
        add(budgetName);
        add(budgetCategory);
        add(budgetAllowance);
        add(back);
    }
    
     @Override
    protected void paintComponent(Graphics g)
    {
         super.paintComponent(g);
        // Top Label/Title
        g.setColor(Color.white);
        g.setFont(new Font("SansSerif",Font.BOLD,22));
        g.drawString("Create New Budget",300,90);
        
        g.setFont(new Font("SansSerif", Font.BOLD, 17));
        g.drawString("Budget Allowance:", 500, 130);
        g.drawString("Budget Name:", 150, 130);
        g.drawString("Weekly", 540, 255);
        g.drawString("Biweekly", 540, 295);
        g.drawString("Monthly", 540, 335);
        g.drawString("Import Existing Data From", 150, 250);
 
        //(x,y,w,h)
    }

    public JButton getCreate() {
        return create;
    }


    public void setCreate(JButton create) {
        this.create = create;
    }
    

    public JRadioButton getWeekly() {
        return weekly;
    }

    public void setWeekly(JRadioButton weekly) {
        this.weekly = weekly;
    }
    

    public JRadioButton getBiWeekly() {
        return biWeekly;
    }


    public void setBiWeekly(JRadioButton biWeekly) {
        this.biWeekly = biWeekly;
    }
    

    public JRadioButton getMonthly() {
        return monthly;
    }


    public void setMonthly(JRadioButton monthly) {
        this.monthly = monthly;
    }
    

    public JTextField getBudgetName() {
        return budgetName;
    }


    public void setBudgetName(JTextField budgetName) {
        this.budgetName = budgetName;
    }
    

    public JTextField getBudgetAllowance() {
        return budgetAllowance;
    }


    public void setBudgetAllowance(JTextField budgetAllowance) {
        this.budgetAllowance = budgetAllowance;
    }
    

    public JCheckBox getBudgetCategory() {
        return budgetCategory;
    }


    public void setBudgetCategory(JCheckBox budgetCategory) {
        this.budgetCategory = budgetCategory;
    }
    

    /**
     * @return the back
     */
    public JButton getBack() {
        return back;
    }

    /**
     * @param back the back to set
     */
    public void setBack(JButton back) {
        this.back = back;
    }
}
