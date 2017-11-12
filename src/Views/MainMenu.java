package Views;

import Models.Budget;
import Models.UserAccount;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Takuya Tokunaga
 */
public class MainMenu extends ViewPane {

    private JButton createBudget;
    private JButton manage;
    private JButton logout;
    private JButton export;
    private JTable budgetTable;
    private DefaultTableModel budgetModel;
    private JScrollPane scrollPane;

    private BufferedImage profilePic;
    private TexturePaint texture;
    
    private UserAccount account;
    private ArrayList<Budget> budgets;


    public MainMenu() {
        super();
    }
    
    public void updateAccount(UserAccount inf_account)
    {
        account = inf_account;
        budgets = account.getBudgets();
        try {
            profilePic = resize(ImageIO.read(new URL(account.getPicUrl())), 150,150);
            texture = new TexturePaint(profilePic, new Rectangle(50,50, profilePic.getWidth(), profilePic.getHeight()));
        } catch (Exception ze) {

        }
        init();
        setAttributes();
        addComponents();
    }

    public void tempSetPic(BufferedImage value) {
        texture = new TexturePaint(value, new Rectangle(0, 0, profilePic.getWidth(), profilePic.getHeight()));
        
    }
    
    private BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
}  
    
    private void init() {

        setCreateBudget(new JButton("Create Budget"));
        setManage(new JButton("Manage"));
        setLogout(new JButton("Logout"));
        setExport(new JButton("Export"));        
        setBudgetTable(new JTable());
        budgetModel = new DefaultTableModel(new Object[]{"ID","Budget Name","Budget Health"},0){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int column) {
                if (getRowCount() > 0) {
                    return getValueAt(0, column).getClass();
                }

                return super.getColumnClass(column);
            }
       
        };
        setScrollPane(new JScrollPane(getBudgetTable()));
        


    }
    

    private void setAttributes() {

        setLayout(null);

        manage.setBounds(660, 230, 80, 25);
        logout.setBounds(660, 290, 80, 25);
        createBudget.setBounds(175, 450, 120, 30);
        export.setBounds(520, 430, 120, 60);
        export.setFont(new Font("SansSerif", Font.BOLD, 22));
        
        budgetTable.setFillsViewportHeight(true);
        budgetTable.setModel(budgetModel);
        budgetTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        scrollPane.setBounds(81, 201, 298, 198);


    }

    private void addComponents() {

        add(createBudget);
        add(manage);
        add(logout);
        add(export);
        add(scrollPane);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        super.paintComponent(g);

        g2d.setPaint(texture);
        g2d.fillRoundRect(500, 200, 150, 150, 75,50);

        
        g.setColor(Color.white);
        // Top Label/Title
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("Main Menu", 350, 90);

        g.setFont(new Font("SansSerif", Font.BOLD, 12));
        g.drawString("Signed in as:", 420, 275);

        // add drawString username
        g.setFont(new Font("SansSerif", Font.BOLD, 17));
        g.drawString("Manage a Budget", 170, 130);
        g.drawString("Click to Edit",188,170);
        g.drawString("My Profile", 540, 130);
        g.drawString("Export Budget Data", 495, 410);
        g.drawRect(80, 200, 300, 200);
        //(x,y,w,h)
        
        
        refreshTable();
    }
    
    private void refreshTable(){
        budgetModel.setRowCount(0);
        budgets.stream().forEach(new DrawTable());
    }

    /**
     * @return the budgetModel
     */
    public DefaultTableModel getBudgetModel() {
        return budgetModel;
    }

    /**
     * @param budgetModel the budgetModel to set
     */
    public void setBudgetModel(DefaultTableModel budgetModel) {
        this.budgetModel = budgetModel;
    }
    
    private class DrawTable implements Consumer<Budget> {

        public DrawTable() {
        }

        @Override
        public void accept(Budget b) {
            int width = budgetTable.getColumnModel().getColumn(2).getWidth();
            int height = budgetTable.getRowHeight(b.getId());
            Random r = new Random();
            BufferedImage cellimage = new BufferedImage(width,
                    height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graph = cellimage.createGraphics();
            FontMetrics metrics = graph.getFontMetrics(graph.getFont());
            graph.setColor(new Color(34,139,34));
            graph.fillRect(0, 0, (int)(width * (b.getBudgetCurrent()/b.getBudgetAllowance())), height);
            graph.setColor(Color.WHITE);
            
            
            String text = "$"+String.format("%.2f", b.getBudgetCurrent()) + " / " + "$"+String.format("%.2f", b.getBudgetAllowance());
            graph.drawString(text,(int)(width- metrics.stringWidth(text))/2,(int)(height - metrics.getHeight()/2)+metrics.getAscent()/2);
            graph.dispose();
            budgetModel.addRow(new Object[]{b.getId(), b.getBudgetName(), new ImageIcon(cellimage)});
        }
    }

    public TexturePaint getTexture() {
        return texture;
    }

    public JButton getCreateBudget() {
        return createBudget;
    }

    public void setCreateBudget(JButton createBudget) {
        this.createBudget = createBudget;
    }

    public JButton getManage() {
        return manage;
    }

    public void setManage(JButton manage) {
        this.manage = manage;
    }

    public JButton getLogout() {
        return logout;
    }

    public void setLogout(JButton logout) {
        this.logout = logout;
    }

    public JButton getExport() {
        return export;
    }

    public void setExport(JButton export) {
        this.export = export;
    }

    public JTable getBudgetTable() {
        return budgetTable;
    }

    public void setBudgetTable(JTable budgetTable) {
        this.budgetTable = budgetTable;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }


    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }


}
