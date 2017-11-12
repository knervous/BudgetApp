package Views;

import Models.Budget;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.table.DatePickerCellEditor;

public class AddEditItem extends ViewPane {

    private Budget budget;

    private DefaultTableModel purchaseModel;
    private JTable purchaseTable;
    private JScrollPane scrollPane;
    private JTextField budgetName;
    private JButton add;

    private JSpinner frequency;
    private SpinnerModel frequencyModel;

    private JButton cancel;
    private JButton save;

    public AddEditItem() {
        super("Images/chalkbackground.jpg", "");
    }

    public void init(Budget inf_budget) {
        setLayout(null);
        setPreferredSize(new Dimension(640, 480));

        budget = inf_budget;

        frequencyModel = new SpinnerListModel(new String[]{"Weekly", "Bi-Weekly", "Monthly"});
        frequency = new JSpinner(frequencyModel);
        frequency.setValue(budget.getFrequency());

        budgetName = new JTextField();
        budgetName.setText(budget.getBudgetName());

        cancel = new JButton("Cancel");
        save = new JButton("Save");

        add = new JButton();
        add.setBorderPainted(false);
        add.setOpaque(false);
        add.setContentAreaFilled(false);

        purchaseModel = new DefaultTableModel(new String[]{"ID", "Purchase Description", "Cost", "Date of Purchase"}, 0);
        purchaseTable = new JTable();

        purchaseTable.setRowHeight(20);
        purchaseTable.setFillsViewportHeight(true);
        purchaseTable.setModel(purchaseModel);
        purchaseTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        purchaseTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        purchaseTable.getColumnModel().getColumn(3).setCellEditor(new DatePickerCellEditor());
      

        scrollPane = new JScrollPane(purchaseTable);

    }

    public void addComponents() {
        add(frequency);
        add(budgetName);
        add(scrollPane);
        add(cancel);
        add(save);
        add(add);
        frequency.setBounds(500, 75, 70, 25);
        budgetName.setBounds(140, 75, 100, 25);
        scrollPane.setBounds(60, 160, 500, 200);
        cancel.setBounds(100, 380, 75, 30);
        save.setBounds(420, 380, 75, 30);
        add.setBounds(565, 235, 50, 50);

        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                purchaseModel.addRow(new String[]{Integer.toString(purchaseTable.getRowCount() + 1), "", ""});
            }
        });
    }

    public void populateTable(Budget b) {
        System.out.println("dfgfd " + b.getPurchases());
        System.out.println("name: " + b.getBudgetName());
        System.out.println("freq: " + b.getFrequency());
        b.getPurchases().stream().forEach(purchaseModel::addRow);
    }

    public static Double addItem(Object[] item, String g) {
        double temp = 0.0;
        try {
            temp = Double.parseDouble(g);
        } catch (Exception ze) {
        }
        return temp;
    }

    public DefaultTableModel getPurchaseModel() {
        return purchaseModel;
    }

    public void setPurchaseModel(DefaultTableModel purchaseModel) {
        this.purchaseModel = purchaseModel;
    }

    public JSpinner getFrequency() {
        return frequency;
    }

    public void setFrequency(JSpinner frequency) {
        this.frequency = frequency;
    }

    public JTextField getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(JTextField budgetName) {
        this.budgetName = budgetName;
    }

    interface FunctionTest<One, Two, Three> {

        public Three apply(One one, Two two);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        super.paintComponent(g);

        g.setColor(Color.white);

        //Add button
        g.drawImage(new ImageIcon("Images/addButton.png").getImage(), 565, 235, 50, 50, null);

        // Top Label/Title
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        if (budget != null) {
            g.drawString("Managing Budget: " + budget.getBudgetName(), 180, 45);
        }

        g.setFont(new Font("SansSerif", Font.BOLD, 17));
        g.drawString("Name", 80, 90);
        g.drawString("Frequency", 400, 90);

        g.drawString("Add or Edit Purchases", 230, 135);

        //(x,y,w,h)
        refreshTable();
    }

    private void refreshTable() {
    }

    public JButton getCancel() {
        return cancel;
    }

    public void setCancel(JButton cancel) {
        this.cancel = cancel;
    }

    public JButton getSave() {
        return save;
    }

    public void setSave(JButton save) {
        this.save = save;
    }

}
