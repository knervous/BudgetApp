/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.*;
import Views.*;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Paul Johnson
 */
public final class MainController {

    //SQL Connection
    DBController db = new DBController();

    //Data Models
    private UserAccount account;
    private DBDataModel dbDataModel;

    //Views
    private UIFrame frame;
    private CreateUser createUser;
    private CreateBudget createBudget;
    private MainMenu mainMenu;
    private Visualizer visualizer;
    private LoginSystem loginSystem;
    private ArrayList<AddEditItem> addEditItem;

    public MainController() {
        init();
        setActionListeners();
    }

    public void init() {
        //Data models
        account = new UserAccount();

        //dbDataModel = new DBDataModel();
        //Views
        createBudget = new CreateBudget();
        mainMenu = new MainMenu();
        createUser = new CreateUser();
        visualizer = new Visualizer();
        loginSystem = new LoginSystem();
        addEditItem = new ArrayList<>();
        //Frame
        frame = new UIFrame(loginSystem);

    }

    public void setActionListeners() {

        //ViewPane exit button listener
        //Login System listeners
        loginSystem.getLogin().addActionListener((ActionEvent e) -> {
            
            try{db.localOrRemoteLogin(loginSystem.getLocalLogin().isSelected());}
            catch(Exception z){}
            
           
            JCheckBox localLogin = loginSystem.getLocalLogin();
            
            String state = db.tryLogin(loginSystem.getUsername().getText(), loginSystem.getPassword().getText(), account);

            switch (state) {
                case "Failed":
                    JOptionPane.showMessageDialog(null, "Username or password is incorrect!");
                    break;
                case "UFail":
                    JOptionPane.showMessageDialog(null, "Username is blank");
                    break;
                case "PFail":
                    JOptionPane.showMessageDialog(null, "Password is blank");
                    break;
                case "NoConnection":
                    JOptionPane.showMessageDialog(null, "Connection Error. Is your firewall blocking port 1433?");
                    break;
                case "Success":
                    mainMenu.updateAccount(account);
                    createMainMenuListeners();
                    frame.setViewPane(mainMenu, loginSystem);
                    System.out.println(account.getFirstName() + " NAME ");
                    break;
            }
        });

        loginSystem.getCreateAccount().addActionListener((ActionEvent e) -> {
            {
                frame.setViewPane(createUser, loginSystem);
                try{
                db.localOrRemoteLogin(loginSystem.getLocalLogin().isSelected());}
                catch(Exception z){}
            }
        });

        //Create User Listeners
        createUser.getCancel().addActionListener((ActionEvent e) -> {

            frame.setViewPane(loginSystem, createUser);

        });

        createUser.getCreate().addActionListener((ActionEvent e) -> {

            if (createUser.getProfilePic() != null && !loginSystem.getLocalLogin().isSelected()) {
                try {
                    URL url = new URL(createUser.getPicUrl());

                    BufferedImage temp = ImageIO.read(url);

                    mainMenu.tempSetPic(temp);
                } catch (Exception zez) {

                }
            }

            if (createUser.getUsername().getText().length() > 0 && createUser.getPassword().getText().length() > 0) {
                Timestamp date = new Timestamp(new Date().getTime());
                String state = db.createAccount(createUser.getUsername().getText(),
                        createUser.getPassword().getText(),
                        createUser.getFirstName().getText(),
                        createUser.getLastName().getText(),
                        createUser.getEmail().getText(), date.toString(),
                        createUser.getGender().getText(),
                        createUser.getPicUrl());
                switch (state) {
                    case "Success":

                        createUser.clearFields();
                        JOptionPane.showMessageDialog(null, "Account created! Check your e-mail for details");
                        frame.setViewPane(loginSystem, createUser);
                        break;
                    case "Duplicate":
                        JOptionPane.showMessageDialog(null, "Username already exists!");
                        break;
                    case "Failed":
                        JOptionPane.showMessageDialog(null, "SQL Connection error");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Required fields must be filled!");
            }
        });

        createUser.getUpload().addActionListener((ActionEvent e) -> {
            if(!loginSystem.getLocalLogin().isSelected())
            {
            final JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(createUser);
            try {
                BufferedImage image = null;
                File file = fc.getSelectedFile();
                //read image
                image = ImageIO.read(file);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                ImageIO.write(image, "png", byteArray);
                byte[] byteImage = byteArray.toByteArray();
                String dataImage = Base64.encode(byteImage);
                String data1 = URLEncoder.encode("image", "UTF-8") + "="
                        + URLEncoder.encode(dataImage, "UTF-8");
                URL url;
                url = new URL("https://api.imgur.com/3/image");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "Client-ID " + "731092c858c169a");
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");

                conn.connect();
                StringBuilder stb = new StringBuilder();
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data1);
                wr.flush();

                // Get the response
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    stb.append(line).append("\n");
                }
                wr.close();
                rd.close();
                org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
                org.json.simple.JSONObject data = (org.json.simple.JSONObject) parser.parse(stb.toString());
                String link = data.get("data").toString();
                org.json.simple.JSONObject linkJ = (org.json.simple.JSONObject) parser.parse(link);

                createUser.setPicUrl(linkJ.get("link").toString());

            } catch (Exception ze) {

                try {
                    URL url = new URL(createUser.getPicUrl());
                    Image image = ImageIO.read(url);
                    //BufferedImage temp = ImageIO.read(fc.getSelectedFile());
                    BufferedImage temp = ImageIO.read(url);

                    createUser.setProfilePic(temp);
                    createUser.repaint();

                } catch (Exception ez) {
                    JDialog popup = new JDialog(frame, "File is not a valid image file!");
                }
            }
            
            }
            else
            {
                JDialog popup = new JDialog(frame, "Not available in local version");
            }
        });

        //Create Budget Listeners
        createBudget.getCreate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Budget temp = new Budget(createBudget.getBudgetName().getText(), Float.valueOf(createBudget.getBudgetAllowance().getText()));
                    temp.setId(account.getBudgets().size());
                    if (createBudget.getWeekly().isSelected()) {
                        temp.setFrequency("Weekly");
                    } else if (createBudget.getBiWeekly().isSelected()) {
                        temp.setFrequency("Bi-Weekly");
                    } else {
                        temp.setFrequency("Monthly");
                    }
                    account.getBudgets().add(temp);
                    addEditItem.add(new AddEditItem());
                    AddEditItem temp2 = addEditItem.get(addEditItem.size() - 1);
                    temp2.init(temp);
                    db.updateAccount(account);
                    frame.setViewPane(mainMenu, createBudget);

                } catch (Exception ex) {

                    System.out.println("exception caught");
                    ex.printStackTrace();
                }
            }
        });

        createBudget.getWeekly().addActionListener((ActionEvent e)
                -> {
            createBudget.getBiWeekly().setSelected(false);
            createBudget.getMonthly().setSelected(false);
        });

        createBudget.getBiWeekly().addActionListener((ActionEvent e)
                -> {
            createBudget.getWeekly().setSelected(false);
            createBudget.getMonthly().setSelected(false);
        });
        createBudget.getMonthly().addActionListener((ActionEvent e)
                -> {
            createBudget.getWeekly().setSelected(false);
            createBudget.getBiWeekly().setSelected(false);
        });

        createBudget.getBack().addActionListener((ActionEvent e) -> {
            frame.setViewPane(mainMenu, createBudget);
        });
    }

    private void createMainMenuListeners() {
        // Main Menu Listeners
        mainMenu.getCreateBudget().addActionListener((ActionEvent e) -> {
            frame.setViewPane(createBudget, mainMenu);
        });

        mainMenu.getManage().addActionListener((ActionEvent e) -> {
            frame.setViewPane(createUser, mainMenu);
        });

        mainMenu.getLogout().addActionListener((ActionEvent e) -> {
            
            account.clear();
            frame.setViewPane(loginSystem, mainMenu);
            mainMenu = new MainMenu();
        });

        mainMenu.getExport().addActionListener((ActionEvent e) -> {
            try {
                final JFileChooser fc = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("Text file", new String[] {"txt", "doc", "docx"});
                fc.setFileFilter(filter);
                fc.setSelectedFile(new File("budget-output.txt"));
                int returnVal = fc.showSaveDialog(mainMenu); //parent component to JFileChooser
                if (returnVal == JFileChooser.APPROVE_OPTION) { //OK button pressed by user
                    File file = fc.getSelectedFile(); //get File selected by user
                    BufferedWriter o = new BufferedWriter(new FileWriter(file)); //use its name
                    o.write("Account creation date: "+account.getCreationDate()+"\n");
                    o.write("Name of account holder: "+account.getFirstName() + " " + account.getLastName() +"\n\n");
                    o.write("Total Budgets: "+account.getBudgets().size()+"\n"+"\n");
                    for(Budget b : account.getBudgets())
                    {
                        o.write("Budget Name: "+b.getBudgetName()+"\n");
                        o.write("Budget Frequency: $"+b.getFrequency()+"\n");
                        o.write("Budget Allowance: $"+b.getBudgetAllowance()+"\n");
                        o.write("Budget Current: "+b.getBudgetCurrent()+"\n");
                        o.write("Total Spent: $"+(b.getBudgetAllowance()-b.getBudgetCurrent()));
                        o.write("Purchases: "+"\n");
                        for(String[] purchase : b.getPurchases())
                        {
                            o.write("\tItem bought: "+purchase[1]+"\t"+"Cost: $"+purchase[2]+"\t"+"Date of Purchase: "+purchase[3]+"\n");
                        }
                        o.write("\n");
                    }
                    o.close();
                    JOptionPane.showMessageDialog(mainMenu, "Successfully exported budget",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
                    //your writing code goes here
                }

            } catch (Exception fileExcept) {
                int zxfdfg = 0;
            }
        });
        

        mainMenu.getBudgetTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {

                if (me.getClickCount() == 1) {
                    JTable target = (JTable) me.getSource();
                    int row = target.getSelectedRow();
                    
                    
                    
                    
                    try {
                        int value = (int) target.getValueAt(row, 0);
                        account.getBudgets().stream().filter((b) -> (b.getId() == value)).forEach((Budget b) -> {
                            System.out.println("Clicked " + b.getId());

                            AddEditItem tempPanel = new AddEditItem();
                            tempPanel.init(b);
                            tempPanel.addComponents();
                            tempPanel.populateTable(b);
                            EditBudgetFrame edit = new EditBudgetFrame(tempPanel);

                            //
                            //            Save cancel action listeners
                            //
                            tempPanel.getSave().addActionListener((ActionEvent a) -> {
                                b.getPurchases().clear();
                                b.setBudgetName(tempPanel.getBudgetName().getText());
                                b.setFrequency((String) tempPanel.getFrequency().getValue());
                                boolean isOk = true;
                                for (int i = 0; i < tempPanel.getPurchaseModel().getRowCount(); i++) {

                                    try {
                                        Double.parseDouble((String) tempPanel.getPurchaseModel().getValueAt(i, 2));
                                        tempPanel.getPurchaseModel().getValueAt(i, 1);
                                        isOk = true;
                                    } catch (Exception z) {
                                        JOptionPane.showMessageDialog(null, "Purchases not filled in correctly!");
                                        isOk = false;
                                        break;
                                    }
                                    String dateValue = tempPanel.getPurchaseModel().getValueAt(i, 3) == null ? "No Date Entered" : (String) tempPanel.getPurchaseModel().getValueAt(i, 3).toString();
                                    b.addPurchase(new String[]{
                                        (String) tempPanel.getPurchaseModel().getValueAt(i, 0),
                                        (String) tempPanel.getPurchaseModel().getValueAt(i, 1),
                                        (String) tempPanel.getPurchaseModel().getValueAt(i, 2),
                                        dateValue
                                    });
                                    mainMenu.repaint();
                                }
                                if (isOk) {
                                    edit.dispose();
                                    float tempBudget = 0;
                                    for (Object[] o : b.getPurchases()) {
                                        tempBudget += Float.parseFloat((String) o[2]);
                                    }
                                    System.out.println("BUDGET = " + tempBudget);
                                    b.setBudgetCurrent(b.getBudgetAllowance() - tempBudget);
                                    System.out.println("UPDATED BUDGET = " + b.getBudgetCurrent());
                                    System.out.println("BUDGET ALLOWANCE = " + b.getBudgetAllowance());
                                    db.updateAccount(account);
                                    mainMenu.repaint();
                                }

                            });
                            tempPanel.getCancel().addActionListener((ActionEvent a) -> {

                                edit.dispose();
                            });

                        });
                    } catch (Exception ze) {
                        ze.printStackTrace();
                    }
                }
            }
        });

    }

    /**
     * @return the account
     */
    public UserAccount getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(UserAccount account) {
        this.account = account;
    }

    /**
     * @return the dbDataModel
     */
    public DBDataModel getDbDataModel() {
        return dbDataModel;
    }

    /**
     * @param dbDataModel the dbDataModel to set
     */
    public void setDbDataModel(DBDataModel dbDataModel) {
        this.dbDataModel = dbDataModel;
    }

    /**
     * @return the frame
     */
    public UIFrame getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(UIFrame frame) {
        this.frame = frame;
    }

    /**
     * @return the createUser
     */
    public CreateUser getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser the createUser to set
     */
    public void setCreateUser(CreateUser createUser) {
        this.createUser = createUser;
    }

    /**
     * @return the createBudget
     */
    public CreateBudget getCreateBudget() {
        return createBudget;
    }

    /**
     * @param createBudget the createBudget to set
     */
    public void setCreateBudget(CreateBudget createBudget) {
        this.createBudget = createBudget;
    }

    /**
     * @return the mainMenu
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * @param mainMenu the mainMenu to set
     */
    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * @return the visualizer
     */
    public Visualizer getVisualizer() {
        return visualizer;
    }

    /**
     * @param visualizer the visualizer to set
     */
    public void setVisualizer(Visualizer visualizer) {
        this.visualizer = visualizer;
    }

    /**
     * @return the loginSystem
     */
    public LoginSystem getLoginSystem() {
        return loginSystem;
    }

    /**
     * @param loginSystem the loginSystem to set
     */
    public void setLoginSystem(LoginSystem loginSystem) {
        this.loginSystem = loginSystem;
    }

}
