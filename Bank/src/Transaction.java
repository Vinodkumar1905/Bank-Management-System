import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction extends JFrame implements ActionListener {

    JButton Withrawal,Deposit,Enquiry,MiniStatement,Update,Transfer,Exit;
    String account_no;
    Transaction(String accountno){
        this.account_no= accountno;

        setLayout(null);
        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,850,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0,-70,900,900);
        add(label);

        JLabel greet = new JLabel("Please Select Desired Operation");
        greet.setForeground(Color.WHITE);
        greet.setFont(new Font("Times New Roman",Font.BOLD,20));
        greet.setBounds(200,320,300,30);
        label.add(greet);

        Withrawal = new JButton("Withdraw");
        Withrawal.setBounds(170,420,100,25);
        Withrawal.addActionListener(this);
        label.add(Withrawal);

        Deposit = new JButton("Deposit");
        Deposit.setBounds(380,420,120,25);
        Deposit.addActionListener(this);
        label.add(Deposit);

        Enquiry = new JButton("Enquiry");
        Enquiry.setBounds(170,452,100,25);
        Enquiry.addActionListener(this);
        label.add(Enquiry);

        MiniStatement = new JButton("Mini Statement");
        MiniStatement.setBounds(380,452,120,28);
        MiniStatement.addActionListener(this);
        label.add(MiniStatement);

        Update = new JButton("Update");
        Update.setBounds(170,484,100,28);
        Update.addActionListener(this);
        label.add(Update);

        Transfer = new JButton("Transfer");
        Transfer.setBounds(380,484,120,28);
        Transfer.addActionListener(this);
        label.add(Transfer);

        Exit = new JButton("Exit");
        Exit.setBounds(380,516,120,28);
        Exit.addActionListener(this);
        label.add(Exit);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Withrawal){
            setVisible(false);
            new Withdraw().setVisible(true);
        } else if (e.getSource()==Deposit) {
            setVisible(false);
            new Deposit(account_no).setVisible(true);
        } else if (e.getSource()==Enquiry) {
            setVisible(false);
            new Enquiry(account_no).setVisible(true);
        } else if (e.getSource()==Transfer) {
            setVisible(false);
            new Transfer(account_no).setVisible(true);
        } else if (e.getSource()==Exit) {
            System.exit(0);
        } else if (e.getSource()==MiniStatement) {
            setVisible(false);
            new MiniStatement(account_no).setVisible(true);
        } else if (e.getSource()==Update) {
            setVisible(false);
            new Update().setVisible(true);
        }


    }

    public static void main(String[] args) {
        new Transaction("");
    }
}
