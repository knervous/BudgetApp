/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author Paul Johnson
 */
public class Budget {
    
    private String budgetName;
    private String frequency;
    private int id;
    private float budgetAllowance;
    private float budgetCurrent;
    private ArrayList<String[]> purchases;
    
    public Budget()
    {
        
    }
    
    public Budget(String inf_budgetName, float inf_budgetAllowance)
    {
        budgetName = inf_budgetName;
        budgetCurrent = budgetAllowance = inf_budgetAllowance;
        purchases = new ArrayList<>();
        
    }
    /**
     * @return the budgetName
     */
    public String getBudgetName() {
        return budgetName;
    }

    /**
     * @param budgetName the budgetName to set
     */
    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    /**
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the budgetAllowance
     */
    public float getBudgetAllowance() {
        return budgetAllowance;
    }

    /**
     * @param budgetAllowance the budgetAllowance to set
     */
    public void setBudgetAllowance(float budgetAllowance) {
        this.budgetAllowance = budgetAllowance;
    }

    /**
     * @return the budgetCurrent
     */
    public float getBudgetCurrent() {
        return budgetCurrent;
    }

    /**
     * @param budgetCurrent the budgetCurrent to set
     */
    public void setBudgetCurrent(float budgetCurrent) {
        this.budgetCurrent = budgetCurrent;
    }

    /**
     * @return the purchases
     */
    public ArrayList<String[]> getPurchases() {
        return purchases;
    }

    /**
     * @param purchases the purchases to set
     */
    public void setPurchases(ArrayList<String[]> purchases) {
        this.purchases = purchases;
    }
    
    public void addPurchase(String[] purchase)
    {
        purchases.add(purchase);
    }
    
    public void removePurchase(Object[] purchase)
    {
        purchases.remove(purchase);
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
