
package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/*

    Author Michael De Giorgis
*/
public class LoginSystem extends ViewPane{
    
    
    private JButton login;
    private JButton createAccount;
    private JLabel lostPassword;
    private JTextField username;
    private JTextField password;
    private JLabel topLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JCheckBox localLogin;
    
    public LoginSystem()
    {
        super();
        init();
        setAttributes();
        addComponents();
    }
    
    public void init()
    {
        setLogin(new JButton());
        setCreateAccount(new JButton());
        setLostPassword(new JLabel());
        setUsername(new JTextField());
        setPassword(new JTextField());
        setTopLabel(new JLabel());
        setUsernameLabel(new JLabel());
        setPasswordLabel(new JLabel());   
        localLogin = new JCheckBox("Local Login (No Internet Required)");
    }

    public void setAttributes() {
        
        
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        constraints.gridx = 0;
        
        //localLogin.setOpaque(false);
        //localLogin.setBackground(Color.GRAY);
        localLogin.setOpaque(true);
        localLogin.setForeground(Color.BLACK);

        setBorder(BorderFactory.createEmptyBorder(40,53,40,53));
        
        getLogin().setSize(new Dimension(130,50));
        getLogin().setText("Login");
       
        getCreateAccount().setSize(new Dimension("Create a new Profile".length() * getCreateAccount().getFont().getSize(),50));
        getCreateAccount().setText("Create a new Profile");
       
        getLostPassword().setSize(new Dimension("Forgot Password?".length() * 15,18));
       
        getLostPassword().setFont(new Font("SansSerif",Font.ITALIC,15));
        getLostPassword().setText("<html><font color='white'>Forgot Password?</font></html>");
       
        getUsername().setPreferredSize(new Dimension(200,25));
       
        getUsernameLabel().setSize(new Dimension("Username".length() * getUsernameLabel().getFont().getSize(),25));
        getUsernameLabel().setText("<html><font color='white'>Username</font></html>");
       
        getPassword().setPreferredSize(new Dimension(200,25));
       
        getPasswordLabel().setSize(new Dimension("Password".length() * getPasswordLabel().getFont().getSize(),25));
        getPasswordLabel().setText("<html><font color='white'>Password</font></html>");
       
        getTopLabel().setSize(new Dimension(250,25));
        getTopLabel().setFont(new Font("SansSerif",Font.BOLD,22));
        getTopLabel().setText("<html><font color='white'>IST 412 Budget App</font></html>");
        
    }

    public void addComponents() {
        add(Box.createRigidArea(new Dimension(0,30)));
        add(getTopLabel(), constraints);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(getUsernameLabel(), constraints);
        constraints.weighty = 3;
        add(getUsername(), constraints);
        constraints.weighty = 1;
        add(getPasswordLabel(), constraints);
        constraints.weighty = 3;
        add(getPassword(), constraints);
        constraints.weighty = 1;
        add(getLogin(), constraints);
        add(localLogin, constraints);
        //add(getLostPassword(), constraints);
        add(getCreateAccount(), constraints);
        add(Box.createRigidArea(new Dimension(10,10)), constraints);
        add(Box.createRigidArea(new Dimension(10,10)), constraints);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        
    }
    
    /**
     * @return the login
     */
    public JButton getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(JButton login) {
        this.login = login;
    }

    /**
     * @return the createAccount
     */
    public JButton getCreateAccount() {
        return createAccount;
    }

    /**
     * @param createAccount the createAccount to set
     */
    public void setCreateAccount(JButton createAccount) {
        this.createAccount = createAccount;
    }

    /**
     * @return the lostPassword
     */
    public JLabel getLostPassword() {
        return lostPassword;
    }

    /**
     * @param lostPassword the lostPassword to set
     */
    public void setLostPassword(JLabel lostPassword) {
        this.lostPassword = lostPassword;
    }

    /**
     * @return the username
     */
    public JTextField getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(JTextField username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public JTextField getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(JTextField password) {
        this.password = password;
    }

    /**
     * @return the topLabel
     */
    public JLabel getTopLabel() {
        return topLabel;
    }

    /**
     * @param topLabel the topLabel to set
     */
    public void setTopLabel(JLabel topLabel) {
        this.topLabel = topLabel;
    }

    /**
     * @return the usernameLabel
     */
    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    /**
     * @param usernameLabel the usernameLabel to set
     */
    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    /**
     * @return the passwordLabel
     */
    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    /**
     * @param passwordLabel the passwordLabel to set
     */
    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    /**
     * @return the localLogin
     */
    public JCheckBox getLocalLogin() {
        return localLogin;
    }

    /**
     * @param localLogin the localLogin to set
     */
    public void setLocalLogin(JCheckBox localLogin) {
        this.localLogin = localLogin;
    }
    
    
    
    
}