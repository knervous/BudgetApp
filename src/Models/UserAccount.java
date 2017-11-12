package Models;

import Views.CreateUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


/*

    Author Takuya Tokunaga

*/
public class UserAccount {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String picUrl;
    private String creationDate;
    private String gender;
    private ArrayList<Budget> budgets;

    public UserAccount() {
        username = "na";
        password = "na";
        firstName = "na";
        lastName = "na";
        email = "na";
        picUrl = "na";
        creationDate = new Date().toString();
        gender = "na";
        budgets = new ArrayList<>();
    }

    public void clear()
    {
        username = "";
        password = "";
        firstName = "";
        lastName = "";
        email = "";
        picUrl = "";
        creationDate = new Date().toString();
        gender = "";
        budgets = new ArrayList<>();
    }
    
    public void initFromDB(String infUsername, String infPassword, String infFirstName, String infLastName, String infEmail, String infGender, String infPicUrl, String infDate, ArrayList<Budget> infBudget)
    {
        creationDate = infDate;
        username = infUsername;
        password = infPassword;
        firstName = infFirstName;
        lastName = infLastName;
        email = infEmail;
        gender = infGender;
        picUrl = infPicUrl;
        budgets = infBudget;
    }

    
    public UserAccount(CreateUser c, String date)
    {
        creationDate = date;
        username = c.getUsername().getText();
        password = c.getPassword().getText();
        firstName = c.getFirstName().getText();
        lastName = c.getLastName().getText();
        email = c.getEmail().getText();
        gender = c.getGender().getText();
        picUrl = c.getPicUrl();
    }
    public boolean emailProfileData() {

        String sentFrom = "budgetapp@fakepsuaccount.com";
        String host = "localhost";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sentFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Here is your Profile Data:");

            message.setText("Username: " + username
                    + "Password: " + "********"
                    + "Name: " + firstName + " " + lastName
                    + "DOB: " 
                    + "Account Created: " + creationDate
                    + "Gender: " + gender);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {

            //e.printStackTrace();
            System.out.println("Need to configure SMTP server correctly!");
            return false;
        }
    }
    
    

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    
    public String getCreationDate()
    {
        return creationDate;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
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

    /**
     * @return the budgets
     */
    public ArrayList<Budget> getBudgets() {
        return budgets;
    }

    /**
     * @param budgets the budgets to set
     */
    public void setBudgets(ArrayList<Budget> budgets) {
        this.budgets = budgets;
    }

}
