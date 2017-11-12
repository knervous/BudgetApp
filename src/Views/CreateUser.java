/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Natasha Bhave
 */
public class CreateUser extends ViewPane {

    private JLabel leftSide;
    private JLabel rightSide;

    private JTextField username;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField gender;
    private JTextField password;
    
    private String picUrl;
    
    private JButton cancel;
    private JButton create;
    private JButton upload;

    private BufferedImage profilePic;

    public CreateUser() {
        super();
        init();
        setAttributes();
        addComponents();
    }

    public void init() {
        setLeftSide(new JLabel());
        setRightSide(new JLabel());

        setUsername(new JTextField());
        setFirstName(new JTextField());
        setLastName(new JTextField());
        setEmail(new JTextField());
        gender = new JTextField();
        setPassword(new JTextField());

        setCancel(new JButton("Cancel"));
        setCreate(new JButton("Create!"));
        setUpload(new JButton("Upload Picture"));
        
        picUrl = "";
    }
    
    public void clearFields()
    {
        setUsername(new JTextField());
        setFirstName(new JTextField());
        setLastName(new JTextField());
        setEmail(new JTextField());
        gender = new JTextField();
        setPassword(new JTextField());
    }

    public void setAttributes() {

        setLayout(null);

        leftSide.setBounds(70, 0, 250, 500);
        leftSide.setFont(new Font("SansSerif", Font.BOLD, 17));
        
        leftSide.setText("<html><font color='white'>"
                + "*Username: " + "<br><br>"
                + "First Name: " + "<br><br>"
                + "Last Name: " + "<br><br>"
                + "Email: " + "<br><br>"
                + " Gender: " + "<br><br>"
                + "  " + "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
                + " &nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp    <br><br>"
                + " *<i>Required Fields</i>"
                + "</font></html>"
        );

        rightSide.setBounds(410, 63, 250, 75);
        rightSide.setFont(new Font("SansSerif", Font.BOLD, 17));
        rightSide.setText("<html><font color='white'>*Password: </font></html>");

        username.setBounds(215, 91, 150, 25);
        firstName.setBounds(215, 136, 150, 25);
        lastName.setBounds(215, 181, 150, 25);
        email.setBounds(215, 226, 150, 25);
        gender.setBounds(215, 271, 150, 25);
        password.setBounds(515, 91, 150, 25);

        cancel.setBounds(150, 500, 100, 30);
        create.setBounds(350, 470, 100, 30);
        upload.setBounds(550, 500, 120, 30);

    }

    public void addComponents() {
        add(leftSide);
        add(rightSide);
        add(username);
        add(firstName);
        add(lastName);
        add(email);
        add(gender);
        add(password);
        add(cancel);
        add(create);
        add(upload);
    }
    
    
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRoundRect(460, 200, 180, 180, 15, 15);
        if (profilePic != null) {
            g2.drawImage(profilePic, 465, 205, 170, 170, null);

        }
    }

    /**
     * @return the leftSide
     */
    public JLabel getLeftSide() {
        return leftSide;
    }

    /**
     * @param leftSide the leftSide to set
     */
    public void setLeftSide(JLabel leftSide) {
        this.leftSide = leftSide;
    }

    /**
     * @return the rightSide
     */
    public JLabel getRightSide() {
        return rightSide;
    }

    /**
     * @param rightSide the rightSide to set
     */
    public void setRightSide(JLabel rightSide) {
        this.rightSide = rightSide;
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
     * @return the firstName
     */
    public JTextField getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(JTextField firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public JTextField getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(JTextField lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public JTextField getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(JTextField email) {
        this.email = email;
    }

    /**
     * @return the dob
     */
    public JTextField getGender() {
      return gender;
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
     * @return the cancel
     */
    public JButton getCancel() {
        return cancel;
    }

    /**
     * @param cancel the cancel to set
     */
    public void setCancel(JButton cancel) {
        this.cancel = cancel;
    }

    /**
     * @return the create
     */
    public JButton getCreate() {
        return create;
    }

    /**
     * @param create the create to set
     */
    public void setCreate(JButton create) {
        this.create = create;
    }

    /**
     * @return the upload
     */
    public JButton getUpload() {
        return upload;
    }

    /**
     * @param upload the upload to set
     */
    public void setUpload(JButton upload) {
        this.upload = upload;
    }


    /**
     * @return the profilePic
     */
    public BufferedImage getProfilePic() {
        return profilePic;
    }

    /**
     * @param profilePic the profilePic to set
     */
    public void setProfilePic(BufferedImage profilePic) {
        this.profilePic = profilePic;
    }

    /**
     * @return the picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * @param picUrl the picUrl to set
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
