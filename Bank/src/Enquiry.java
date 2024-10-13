import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Enquiry extends JFrame implements ActionListener {
    String account_no,balance;
    JButton Exit;

    Enquiry(String accountno){
        this.account_no = accountno;
        setLayout(null);
        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,850,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0,-70,900,900);
        add(label);

        JLabel greet = new JLabel("Balance Enquiry");
        greet.setForeground(Color.WHITE);
        greet.setFont(new Font("Times New Roman",Font.BOLD,20));
        greet.setBounds(270,320,300,30);
        label.add(greet);

        JLabel acc = new JLabel("Account No:");
        acc.setBounds(170,360,150,30);
        acc.setFont(new Font("Times New Roman",Font.BOLD,20));
        acc.setForeground(Color.white);
        label.add(acc);

        JLabel accn = new JLabel(account_no);
        accn.setBounds(300,360,150,30);
        accn.setFont(new Font("Times New Roman",Font.BOLD,20));
        accn.setForeground(Color.white);
        label.add(accn);

        JLabel Balance = new JLabel("Balance :");
        Balance.setBounds(170,400,150,30);
        Balance.setFont(new Font("Times New Roman",Font.BOLD,20));
        Balance.setForeground(Color.WHITE);
        label.add(Balance);

        Conn cn = new Conn();
        String query = "select Balance from signup where Account_no ='"+account_no+"'";
        try {
            ResultSet rs = cn.st.executeQuery(query);
            rs.next();
            balance = rs.getString("Balance");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        JLabel Balancep = new JLabel(balance);
        Balancep.setBounds(300,400,150,30);
        Balancep.setFont(new Font("Times New Roman",Font.BOLD,20));
        Balancep.setForeground(Color.WHITE);
        label.add(Balancep);

        Exit = new JButton("Cancel");
        Exit.setBounds(170, 525, 90, 20);
        Exit.addActionListener(this);
        label.add(Exit);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Exit){
            setVisible(false);
            new Transaction(account_no);
        }
    }
    public static void main(String[] args) {
        new Enquiry("");
    }
}
