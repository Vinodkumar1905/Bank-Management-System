import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class SighnUp extends JFrame implements ActionListener {

    JTextField nameField,emailField,fathernameField,addressField,pinField;
    JFormattedTextField phonenoField;
    JButton  next,home;
    JRadioButton male,female;
    JComboBox statebox,citybox;
    int formno,balance;
    long accno;





    SighnUp(){

        setTitle("Sign Up");
        setLayout(null);
        //JLbale for image---->>>>
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("images/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(80,80 ,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(40,30,100,100);
        add(label);

        JLabel form = new JLabel("VGB Sign Up Form..");
        form.setBounds(190,20,550,100);
        form.setFont(new Font("Osward",Font.BOLD,30));
        add(form);

        formno = (int)(Math.random()*10000);

        JLabel appli = new JLabel("Application No : "+formno);
        appli.setBounds(480,10,120,10);
        appli.setFont(new Font("Osward",Font.BOLD,10));
        add(appli);

        JLabel detail = new JLabel("Page 1 : Personal Details");
        detail.setBounds(260,100,200,20);
        detail.setFont(new Font("Osward",Font.BOLD,15));
        add(detail);

        JLabel name = new JLabel("Name :");
        name.setBounds(90,140,150,30);
        name.setFont(new Font("Osward",Font.BOLD,20));
        add(name);

        nameField = new JTextField();
        nameField.setBounds(260,140,250,30);
        nameField.setFont(new Font("Osward",Font.BOLD,15));
        add(nameField);

        JLabel phoneno = new JLabel("Phone No :");
        phoneno.setBounds(90,180,150,30);
        phoneno.setFont(new Font("Osward",Font.BOLD,20));
        add(phoneno);

        phonenoField = new JFormattedTextField();
        phonenoField.setBounds(260,180,250,30);
        phonenoField.setFont(new Font("Osward",Font.BOLD,15));
        add(phonenoField);

        JLabel email = new JLabel("Email ID :");
        email.setBounds(90,220,150,30);
        email.setFont(new Font("Osward",Font.BOLD,20));
        add(email);

        emailField = new JTextField();
        emailField.setBounds(260,220,250,30);
        emailField.setFont(new Font("Osward",Font.BOLD,15));
        add(emailField);

        JLabel fathername = new JLabel("Father Name :");
        fathername.setBounds(90,260,150,30);
        fathername.setFont(new Font("Osward",Font.BOLD,20));
        add(fathername);

        fathernameField = new JTextField();
        fathernameField.setBounds(260,260,250,30);
        fathernameField.setFont(new Font("Osward",Font.BOLD,15));
        add(fathernameField);

        JLabel state = new JLabel("State :");
        state.setBounds(90,300,100,30);
        state.setFont(new Font("Osward",Font.BOLD,20));
        add(state);

        String stateopt[] = {"Haryana","Himachal","UP","MP","Gujrat","Punjab","J&K","Uttrakhand"};
        statebox = new JComboBox<>(stateopt);
        statebox.setBounds(90,330,195,30);
        statebox.setFont(new Font("Osward",Font.BOLD,15));
        add(statebox);

        JLabel city = new JLabel("City :");
        city.setBounds(315,300,100,30);
        city.setFont(new Font("Osward",Font.BOLD,20));
        add(city);
        String cityopt[] = {"Gurugram", "Faridabad", "Panipat", "Ambala", "Hisar","Karnal", "Rohtak", "Sonipat", "Yamunanagar", "Kurukshetra", "Panchkula", "Rewari", "Bhiwani", "Sirsa", "Fatehabad","Jind", "Kaithal", "Mahendragarh", "Palwal", "Jhajjar"};
        citybox = new JComboBox<>(cityopt);
        citybox.setBounds(315,330,195,30);
        citybox.setFont(new Font("Osward",Font.BOLD,15));
        add(citybox);

        JLabel address = new JLabel("Address :");
        address.setBounds(90,370,150,30);
        address.setFont(new Font("Osward",Font.BOLD,20));
        add(address);

        addressField = new JTextField();
        addressField.setBounds(260,370,250,30);
        addressField.setFont(new Font("Osward",Font.BOLD,15));
        add(addressField);

        JLabel pin = new JLabel("Pin Code :");
        pin.setBounds(90,410,150,30);
        pin.setFont(new Font("Osward",Font.BOLD,20));
        add(pin);

        pinField = new JTextField();
        pinField.setBounds(260,410,250,30);
        pinField.setFont(new Font("Osward",Font.BOLD,15));
        add(pinField);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(90,450,100,30);
        gender.setFont(new Font("Oswars",Font.BOLD,20));
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(260,450,80,30);
        male.setFont(new Font("Osward",Font.BOLD,15));
        male.setBackground(Color.WHITE);
        add(male);
        female = new JRadioButton("Female");
        female.setBounds(400,450,100,30);
        female.setFont(new Font("Osward",Font.BOLD,15));
        female.setBackground(Color.WHITE);
        add(female);
        ButtonGroup g = new ButtonGroup();
        g.add(female);
        g.add(male);

        setSize(800,450);
        setVisible(true);
        setBounds(370,100,630,600);
        getContentPane().setBackground(Color.white);

        next = new JButton("Next >>");
        next.setBounds(480,520,70,25);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);

        home = new JButton("Home");
        home.setBounds(30,520,70,25);
        home.setBackground(Color.black);
        home.setForeground(Color.white);
        home.addActionListener(this);
        add(home);







    }
    public void actionPerformed(ActionEvent e) {
        String formNo = "" + formno;
        String name = "" + nameField.getText();
        String fName = "" + fathernameField.getText();
        String address = "" + addressField.getText();
        String state = (String) statebox.getSelectedItem();
        String pincode = "" + pinField.getText().trim();
        String city =(String) citybox.getSelectedItem();
        String email = "" + emailField.getText().trim();
        String gender = null;
        String phno = "" + phonenoField.getText();

        if (e.getSource() == home) {
            setVisible(false);
            new Login().setVisible(true);
        } else {

            if (male.isSelected()) {
                gender = "Male";
            } else if (female.isSelected()) {
                gender = "Female";
            }
            try {
                Conn cn = new Conn();
                String query = "SELECT MAX(Account_no) AS max_acc_no FROM signup";
                ResultSet rs = cn.st.executeQuery(query);
                if (rs.next()) {
                    accno = rs.getLong("max_acc_no");
                    accno = accno + 1;
                } else if (rs.wasNull()) {
                    accno = 12001200012L;
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            try {
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Name is required");
                } else if (!email.endsWith("@gmail.com")) {
                    JOptionPane.showMessageDialog(null, "Enter valid Email");
                } else if (city.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter valid city");

                } else if (state.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter valid State");

                } else if (pincode.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter valid pincode");
                } else if (phno.isEmpty() || phno.length() != 10) {
                    JOptionPane.showMessageDialog(null, "Enter valid Phone no  ");
                } else {
                    Conn cn = new Conn();
                    String query = "insert into signup (Formno,Name,Phone_no,Father_name,Gender,Pincode,Address,State,City,Email,Balance,Account_no) values('" + formNo + "', '" + name + "','" + phno + "','" + fName + "','" + gender + "','" + pincode + "', '" + address + "','" + state + "','" + city + "','" + email + "','" + balance + "','" + accno +"')";
                    cn.st.executeUpdate(query);
                    setVisible(false);
                    new SignupTwo().setVisible(true);

                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }

        }
        public static void main (String[]args){
            new SighnUp();
        }

}
