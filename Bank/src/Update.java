import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update extends JFrame implements ActionListener {

    JButton Exit,Confirm;
    JTextField phonenoField,emailField,accd;
    JPasswordField pin,curpin;

    Update(){
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0, -70, 900, 900);
        add(label);

        JLabel greet = new JLabel("Update Fields");
        greet.setForeground(Color.WHITE);
        greet.setFont(new Font("Times New Roman", Font.BOLD, 15));
        greet.setBounds(270, 320, 300, 20);
        label.add(greet);

        JLabel phoneno = new JLabel("Phone No :");
        phoneno.setBounds(170,360,150,20);
        phoneno.setFont(new Font("Times New Roman",Font.BOLD,15));
        phoneno.setForeground(Color.white);
        label.add(phoneno);

        phonenoField = new JFormattedTextField();
        phonenoField.setBounds(320,360,150,20);
        phonenoField.setFont(new Font("Times New Roman",Font.BOLD,15));
        label.add(phonenoField);

        JLabel email = new JLabel("Email ID :");
        email.setBounds(170,390,150,20);
        email.setFont(new Font("Times New Roman",Font.BOLD,15));
        email.setForeground(Color.WHITE);
        label.add(email);

        emailField = new JTextField();
        emailField.setBounds(320,390,150,20);
        emailField.setFont(new Font("Times New Roman",Font.BOLD,15));
        label.add(emailField);

        JLabel currTransPin = new JLabel("Transaction Pin :");
        currTransPin.setForeground(Color.WHITE);
        currTransPin.setFont(new Font("Times New Roman",Font.BOLD,15));
        currTransPin.setBounds(170,420,150,20);
        label.add(currTransPin);

        curpin = new JPasswordField();
        curpin.setBackground(Color.white);
        curpin.setForeground(Color.black);
        curpin.setBounds(320,420,150,20);
        label.add(curpin);


        JLabel TransPin = new JLabel("New Transaction Pin :");
        TransPin.setForeground(Color.WHITE);
        TransPin.setFont(new Font("Times New Roman",Font.BOLD,15));
        TransPin.setBounds(170,450,180,20);
        label.add(TransPin);

        pin = new JPasswordField();
        pin.setBackground(Color.white);
        pin.setForeground(Color.black);
        pin.setBounds(320,450,150,20);
        label.add(pin);

        JLabel accDetail = new JLabel("Account type :");
        accDetail.setBounds(170,480,140,30);
        accDetail.setFont(new Font("Times New Roman",Font.BOLD,15));
        accDetail.setForeground(Color.WHITE);
        label.add(accDetail);

        accd = new JTextField();
        accd.setBackground(Color.white);
        accd.setForeground(Color.black);
        accd.setBounds(320,480,150,20);
        label.add(accd);

        JLabel accop = new JLabel("1.Saving 2.current 3.FD 4.RD");
        accop.setBounds(170,500,200,20);
        accop.setFont(new Font("Times New Roman",Font.BOLD,10));
        accop.setForeground(Color.white);
        label.add(accop);










        Exit = new JButton("Cancel");
        Exit.setBounds(170, 525, 90, 20);
        Exit.addActionListener(this);
        label.add(Exit);

        Confirm = new JButton("Confirm");
        Confirm.setBounds(400, 525, 90, 20);
        Confirm.addActionListener(this);
        label.add(Confirm);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String phone = ""+phonenoField.getText();
        String email = ""+emailField.getText();
        String cpin = "" +curpin.getText();
        String pinf = ""+pin.getText();
        String accde =""+accd.getText();


        if(e.getSource()==Confirm) {
            if (phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter phone no");
            }
            else if (email.isEmpty() || !email.endsWith("@gmail.com")) {
                JOptionPane.showMessageDialog(null, "Enter Valid Email");
            }
            else if (pinf.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter New pin");
            }
            else if (cpin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Current pin");
            }
            else if(accde.isEmpty()){
                JOptionPane.showMessageDialog(null, "Enter Account Type");
            }

            String acct="";
            if(accd.getText().equals("1")){
                acct = acct + "Saving";
            }else if(accd.getText().equals("2")){
                acct += "Current";
            }else if(accd.getText().equals("3")){
                acct += "FD";
            }else if(accd.getText().equals("4")){
                acct += "RD";
            }

            Conn cn = new Conn();
            String query = "Select Tpin from signup WHERE Tpin ='" + cpin + "'";

            try {
                ResultSet rs = cn.st.executeQuery(query);

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Wrong Current pin");
                } else {
                    try {
                        query = "UPDATE signup SET Phone_no ='" + phone + "', Email = '" + email + "', Tpin = '" + pinf + "',Account_type = '"+acct+"' WHERE Tpin ='" + cpin + "'";
                        cn.st.executeUpdate(query);
                        query = "UPDATE transaction SET TransPin = '" + pinf +"' WHERE TransPin ='" + cpin + "'";
                        cn.st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Updation completed");
                        setVisible(false);
                        new Login().setVisible(true);

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == Exit) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }


    public static void main(String[] args) {
        new Update();
    }
}