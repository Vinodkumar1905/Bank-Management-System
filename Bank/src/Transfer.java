import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class Transfer  extends JFrame implements ActionListener {
    LocalDateTime date = LocalDateTime.now();
    String account_no;
    JTextField acc1,acc2,money;
    JPasswordField pin;
    JButton Exit,Confirm;
    Transfer(String acc){
        this.account_no=acc;


        setLayout(null);
        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,850,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0,-70,900,900);
        add(label);

        JLabel greet = new JLabel("Transfer money Acc to Acc");
        greet.setForeground(Color.WHITE);
        greet.setFont(new Font("Times New Roman",Font.BOLD,20));
        greet.setBounds(200,320,300,30);
        label.add(greet);

        JLabel Acc1 = new JLabel("Enter your Account No:");
        Acc1.setForeground(Color.WHITE);
        Acc1.setFont(new Font("Times New Roman",Font.BOLD,15));
        Acc1.setBounds(170,355,180,30);
        label.add(Acc1);

        acc1 = new JTextField();
        acc1.setBackground(Color.white);
        acc1.setForeground(Color.black);
        acc1.setBounds(340,360,170,20);
        label.add(acc1);

        JLabel Acc2 = new JLabel("Enter other Account No:");
        Acc2.setForeground(Color.WHITE);
        Acc2.setFont(new Font("Times New Roman",Font.BOLD,15));
        Acc2.setBounds(170,390,180,30);
        label.add(Acc2);
        acc2 = new JTextField();
        acc2.setBackground(Color.white);
        acc2.setForeground(Color.black);
        acc2.setBounds(340,395,170,20);
        label.add(acc2);

        JLabel Money = new JLabel("Enter Amount :");
        Money.setForeground(Color.WHITE);
        Money.setFont(new Font("Times New Roman",Font.BOLD,15));
        Money.setBounds(170,425,180,30);
        label.add(Money);

        money = new JTextField();
        money.setBackground(Color.white);
        money.setForeground(Color.black);
        money.setBounds(340,430,170,20);
        label.add(money);

        JLabel TransPin = new JLabel("Transaction Pin :");
        TransPin.setForeground(Color.WHITE);
        TransPin.setFont(new Font("Times New Roman",Font.BOLD,15));
        TransPin.setBounds(170,460,180,30);
        label.add(TransPin);

        pin = new JPasswordField();
        pin.setBackground(Color.white);
        pin.setForeground(Color.black);
        pin.setBounds(340,465,170,20);
        label.add(pin);




        Exit = new JButton("Cancel");
        Exit.setBounds(170,516,90,28);
        Exit.addActionListener(this);
        label.add(Exit);

        Confirm = new JButton("Confirm");
        Confirm.setBounds(390,516,90,28);
        Confirm.addActionListener(this);
        label.add(Confirm);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accno1 = ""+acc1.getText();
        String accno2 = ""+acc2.getText();
        String tpin = ""+pin.getText().trim();
        String amount =""+money.getText();
        if(e.getSource()==Confirm){
            Conn cn = new Conn();
            try{
                String query = "select TransPin from transaction where Account_no ='"+accno1+"'";
                ResultSet rs = cn.st.executeQuery(query);
                if(rs.next()){
                    String pass = rs.getString("TransPin");
                    if(pass.equals(tpin)){
                        query = "select balance from signup transaction where Account_no ='"+accno1+"'";
                        rs = cn.st.executeQuery(query);
                        rs.next();
                        long balance = Integer.parseInt(rs.getString("Balance"));
                        if(Integer.parseInt(amount) < balance){
                           long acc1balance = balance-Integer.parseInt(amount);
                            query = "UPDATE signup SET Balance = '" + acc1balance + "' Where Account_no ='"+accno1+"'";
                            cn.st.executeUpdate(query);
                            query = "insert into data_"+accno1+" (Account_no,Date,Amount,Type,Source) values('"+accno1+"','Date','"+amount+"','Withdraw','Transfer')";
                            cn.st.executeUpdate(query);

                            query = "select balance from signup transaction where Account_no ='"+accno2+"'";
                            rs = cn.st.executeQuery(query);
                            rs.next();
                            long balance2 = Integer.parseInt(rs.getString("Balance"));
                            long acc2balance = balance2+ Integer.parseInt(amount);
                            query = "UPDATE signup SET Balance = '" + acc2balance + "' Where Account_no ='"+accno2+"'";
                            cn.st.executeUpdate(query);
                            query = "insert into data_"+accno2+" (Account_no,Date,Amount,Type,Source) values('"+accno1+"','"+date+"','"+amount+"','Deposit','Transfer')";
                            cn.st.executeUpdate(query);


                            JOptionPane.showMessageDialog(null,"Transactin Completed" +
                                    "\n remaning balance is :"+acc1balance);

                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Insufficient Amount");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Wrong pin");
                        pin.setText("");
                    }


                }
                else {
                    JOptionPane.showMessageDialog(null,"Wrong Account no");
                    acc1.setText("");
                }
            }
            catch (Exception ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Aaack! You caught us with our pants down! Here we are, tinkering with the servers and you show up. How awkward! Try back in just a couple of minutes");

            }


        } else if (e.getSource()==Exit) {
            setVisible(false);
            new Transaction("").setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Transfer("");
    }
}
