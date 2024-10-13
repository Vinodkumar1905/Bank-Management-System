import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SignupThree extends JFrame implements ActionListener {
    JComboBox accDe;
    JTextField transpinField;
    JButton back,submit;
    JCheckBox c1,c2,c3,c4,c5,c6,declaration;
    long accno;
    static long dcno1 = 1000000000000000L+(long)(Math.random()*9999999999999999L);


    SignupThree(){
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
        form.setFont(new Font("Times New Roman",Font.BOLD,30));
        add(form);

        JLabel detail = new JLabel("Page 3 : Accounts Details");
        detail.setBounds(270,100,200,20);
        detail.setFont(new Font("Times New Roman",Font.BOLD,15));
        add(detail);


        JLabel accDetail = new JLabel("Account type :");
        accDetail.setBounds(90,130,140,30);
        accDetail.setFont(new Font("Times New Roman",Font.BOLD,20));
        add(accDetail);

        String acc[] ={"Saving","Current","FD","RD"};
        accDe = new JComboBox<>(acc);
        accDe.setBackground(Color.white);
        accDe.setFont(new Font("Times New Roman",Font.BOLD,20));
        accDe.setBounds(270,130,250,30);
        add(accDe);

        JLabel cardno = new JLabel("Card no:");
        cardno.setBounds(90,190,100,30);
        cardno.setFont(new Font("Times New Roman",Font.BOLD,20));
        add(cardno);
        JLabel cardnol = new JLabel("Your 16 digit debit Card no");
        cardnol.setBounds(90,210,150,30);
        cardnol.setFont(new Font("Times New Roman",Font.BOLD,10));
        add(cardnol);

        JLabel cno = new JLabel(String.valueOf(dcno1/1000000000000L+"-"+(dcno1/100000000)%10000+"-"+(dcno1/10000)%10000+"-"+dcno1%10000));
        cno.setBounds(270,190,250,30);
        cno.setFont(new Font("Times New Roman",Font.BOLD,22));
        add(cno);

        JLabel transpin = new JLabel("Transaction pin :");
        transpin.setBounds(90,250,170,30);
        transpin.setFont(new Font("Times New Roman",Font.BOLD,20));
        add(transpin);

        JLabel transpinl = new JLabel("4 digit pin");
        transpinl.setBounds(90,270,150,20);
        transpinl.setFont(new Font("Times New Roman",Font.BOLD,10));
        add(transpinl);

        transpinField = new JTextField();
        transpinField.setBounds(260,250,250,30);
        transpinField.setBackground(Color.WHITE);
        add(transpinField);


        JLabel service = new JLabel("Service Required :");
        service.setBounds(90,300,200,30);
        service.setFont(new Font("Times New Roman",Font.BOLD,25));
        add(service);

        c1 = new JCheckBox("Cheque Book");
        c1.setBounds(90,340,150,20);
        c1.setFont(new Font("Times New Roman",Font.BOLD,15));
        c1.setBackground(Color.WHITE);
        add((c1));

        c2 = new JCheckBox("Internet Banking");
        c2.setBounds(300,340,150,20);
        c2.setFont(new Font("Times New Roman",Font.BOLD,15));
        c2.setBackground(Color.WHITE);
        add(c2);

        c3 = new JCheckBox("Credit Card");
        c3.setBounds(90,370,150,20);
        c3.setFont(new Font("Times New Roman",Font.BOLD,15));
        c3.setBackground(Color.WHITE);
        add(c3);

        c4 = new JCheckBox("E-Statement");
        c4.setBounds(300,370,150,20);
        c4.setFont(new Font("Times New Roman",Font.BOLD,15));
        c4.setBackground(Color.WHITE);
        add(c4);

        c5 = new JCheckBox("Email & SMS alert");
        c5.setBounds(90,400,150,20);
        c5.setFont(new Font("Times New Roman",Font.BOLD,15));
        c5.setBackground(Color.WHITE);
        add(c5);

        c6 = new JCheckBox("Loan");
        c6.setBounds(300,400,150,20);
        c6.setFont(new Font("Times New Roman",Font.BOLD,15));
        c6.setBackground(Color.WHITE);
        add(c6);


        declaration = new JCheckBox("I hereby declare that the above entered details are correct to my knowledge ");
        declaration.setBounds(70,460,550,20);
        declaration.setFont(new Font("Times New Roman",Font.BOLD,15));
        declaration.setBackground(Color.WHITE);
        add(declaration);











        submit = new JButton("Submit >>");
        submit.setBounds(480,520,90,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        add(submit);

        back = new JButton("<<Back");
        back.setBounds(370,520,90,25);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);


        setSize(800,450);
        setVisible(true);
        setBounds(370,100,630,600);
        getContentPane().setBackground(Color.white);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        String accType = (String)accDe.getSelectedItem();
        String pin = ""+transpinField.getText();
       if(pin.length()!=4){
           JOptionPane.showMessageDialog(null, "Invalid pin ");
       }
       try{
           Integer.parseInt(pin);
       } catch (NumberFormatException ex) {
           JOptionPane.showMessageDialog(null, "Enter NUmeric entry only  ");
       }

        if(!declaration.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please fill confirmation ");
        }

        String servic = "";
        if(c1.isSelected()){
            servic = servic +"Cheque Book ";
        } if (c2.isSelected()){
            servic = servic +"Internet banking ";

        }if (c3.isSelected()){
            servic = servic +"Credit Card ";

        }if (c4.isSelected()){
            servic = servic +"E-statement ";

        }if (c2.isSelected()){
            servic = servic +"E-Mail & SMS alert ";

        }if (c2.isSelected()){
            servic = servic +"Loan ";

        }


        if (e.getSource() == back) {
            setVisible(false);
            new Login().setVisible(true);
        }
        if (e.getSource() == submit) {
            if (declaration.isSelected()) {
                try {
                    Conn cn = new Conn();
                    String query = "SELECT MAX(Account_no) AS max_acc_no FROM signup";
                    ResultSet rs = cn.st.executeQuery(query);
                    if (rs.next()) {
                        accno = rs.getLong("max_acc_no");
                    }

                    String query1 =  "UPDATE signup SET Account_type = '" + accType + "', Tpin = '" + pin + "', Service = '" + servic  + "', CardNo = '"+dcno1+"' WHERE Account_no = '" + accno + "'";
                    cn.st.executeUpdate(query1);
                    System.out.println("hlo");
                    query1 =  "UPDATE transaction SET TransPin = '" + pin + "', CardNo = '" + dcno1  + "' WHERE Account_no ='" + accno + "'";
                    cn.st.executeUpdate(query1);
                    System.out.println("hlo");
                    query1 = "create table data_"+accno+" (srno INT AUTO_INCREMENT PRIMARY KEY, Account_no varchar(50) ,Date varchar(50),Amount varchar(50),Type varchar(50),Source varchar(250))";
                    cn.st.executeUpdate(query1);
                    System.out.println("hlo");


                    JOptionPane.showMessageDialog(null, "Account created succesfully \nYour Account no is :" + accno);
                    setVisible(false);
                    new Deposit(""+accno).setVisible(true);


                } catch (SQLException ex) {
                    System.out.println(ex);
                }

            }



        }
    }

    public static void main(String[] args) {
        new SignupThree();
    }
}
