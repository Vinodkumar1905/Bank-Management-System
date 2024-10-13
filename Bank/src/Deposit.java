import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class Deposit extends JFrame implements ActionListener {
    LocalDateTime date = LocalDateTime.now();
    JTextField acc1,money;
    String acc;
    JButton Exit,Confirm;
    Deposit(String account){
        setLayout(null);
        this.acc = account;


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0, -70, 900, 900);
        add(label);

        JLabel greet = new JLabel("Cash Deposit");
        greet.setForeground(Color.WHITE);
        greet.setFont(new Font("Times New Roman", Font.BOLD, 20));
        greet.setBounds(280, 320, 300, 30);
        label.add(greet);

        JLabel Acc1 = new JLabel("Enter your Account No:");
        Acc1.setForeground(Color.WHITE);
        Acc1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        Acc1.setBounds(170, 365, 180, 30);
        label.add(Acc1);

        acc1 = new JTextField();
        acc1.setBackground(Color.white);
        acc1.setForeground(Color.black);
        acc1.setBounds(340, 370, 170, 20);
        label.add(acc1);

        JLabel Money = new JLabel("Enter Amount :");
        Money.setForeground(Color.WHITE);
        Money.setFont(new Font("Times New Roman",Font.BOLD,15));
        Money.setBounds(170,400,180,30);
        label.add(Money);

        money = new JTextField();
        money.setBackground(Color.white);
        money.setForeground(Color.black);
        money.setBounds(340,405,170,20);
        label.add(money);

        Exit = new JButton("Cancel");
        Exit.setBounds(170, 516, 90, 28);
        Exit.addActionListener(this);
        label.add(Exit);

        Confirm = new JButton("Confirm");
        Confirm.setBounds(390, 516, 90, 28);
        Confirm.addActionListener(this);
        label.add(Confirm);



        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String accno1 = "" + acc1.getText();
        String amount = "" + money.getText();
        if(e.getSource() == Confirm) {
            Conn cn =new Conn();
            try{
                String query = "select Balance from signup where Account_no ='"+accno1+"'";
                ResultSet rs = cn.st.executeQuery(query);
                rs.next();
                long accbalance = Integer.parseInt(rs.getString("Balance"));
                long balance = accbalance+Integer.parseInt(amount);

                query ="UPDATE signup SET Balance='"+balance+"' where Account_no ='"+accno1+"'";
                cn.st.executeUpdate(query);
                query = "insert into data_"+accno1+" (Account_no,Date,Amount,Type,Source) values('"+accno1+"','"+date+"','"+amount+"','Deposit','Cash')";
                cn.st.executeUpdate(query);


                JOptionPane.showMessageDialog(null,"Transaction completed\nCurrent Balance: "+balance);
                setVisible(false);
                new Transaction(acc).setVisible(true);

            }catch (Exception ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Aaack! You caught us with our pants down! Here we are, tinkering with the servers and you show up. How awkward! Try back in just a couple of minutes");

            }
        } else if (e.getSource()==Exit) {
           setVisible(false);
           new Transaction(acc).setVisible(true);

        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
