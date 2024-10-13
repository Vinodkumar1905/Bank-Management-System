import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
public class Withdraw extends JFrame implements ActionListener {
    LocalDateTime date = LocalDateTime.now();
    JTextField acc1, dcno, money;
    JPasswordField pin;
    JButton Exit, Confirm;

    Withdraw() {
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0, -70, 900, 900);
        add(label);

        JLabel greet = new JLabel("Cash Withdrawal");
        greet.setForeground(Color.WHITE);
        greet.setFont(new Font("Times New Roman", Font.BOLD, 20));
        greet.setBounds(250, 320, 300, 30);
        label.add(greet);

        JLabel Acc1 = new JLabel("Enter your Account No:");
        Acc1.setForeground(Color.WHITE);
        Acc1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        Acc1.setBounds(170, 355, 180, 30);
        label.add(Acc1);

        acc1 = new JTextField();
        acc1.setBackground(Color.white);
        acc1.setForeground(Color.black);
        acc1.setBounds(340, 360, 170, 20);
        label.add(acc1);

        JLabel dc = new JLabel("Enter Card No:");
        dc.setForeground(Color.WHITE);
        dc.setFont(new Font("Times New Roman", Font.BOLD, 15));
        dc.setBounds(170, 390, 180, 30);
        label.add(dc);

        dcno = new JTextField();
        dcno.setBackground(Color.white);
        dcno.setForeground(Color.black);
        dcno.setBounds(340, 395, 170, 20);
        label.add(dcno);

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
        Exit.setBounds(170, 516, 90, 28);
        Exit.addActionListener(this);
        label.add(Exit);

        Confirm = new JButton("Confirm");
        Confirm.setBounds(390, 516, 90, 28);
        Confirm.addActionListener(this);
        label.add(Confirm);


        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        String accno1 = "" + acc1.getText();
        String card = "" + dcno.getText();
        String tpin = "" + pin.getText().trim();
        String amount = "" + money.getText();

        if (e.getSource()==Exit) {
            setVisible(false);
            new Transaction("").setVisible(true);
        }
        else if (e.getSource() == Confirm) {
            try {
                if (accno1.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter acc no");
                } else if (card.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter card");
                } else if (tpin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter valid pin");
                } else if (amount.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Amount");
                }
            }
            catch (Exception ex1){
                System.out.println(ex1);
                JOptionPane.showMessageDialog(null,"Enter Valid Inputs");
            }
            Conn cn = new Conn();
            try {
                String query = "select TransPin from transaction WHERE Account_no ='" + accno1 + "' AND CardNo ='" +card + "'";
                ResultSet rs = cn.st.executeQuery(query);
                if (rs.next()){
                    String pass = rs.getString("TransPin");
                    if (pass.equals(tpin)) {
                        query = "select balance from signup where Account_no ='" + accno1 + "' and CardNo ='" +card + "'";
                        rs = cn.st.executeQuery(query);
                        rs.next();
                        long balance = Integer.parseInt(rs.getString("Balance"));
                        if (Integer.parseInt(amount) < balance) {
                            long acc1balance = balance - Integer.parseInt(amount);
                            query = "UPDATE signup SET Balance = '" + acc1balance + "' Where Account_no ='" + accno1 + "'";
                            cn.st.executeUpdate(query);
                            query = "insert into data_"+accno1+" (Account_no,Date,Amount,Type,Source) values('"+accno1+"','"+date+"','"+amount+"','Deposit','Cash')";
                            cn.st.executeUpdate(query);
                            JOptionPane.showMessageDialog(null, "Transaction Completed" +
                                    "\n remaining balance is :" + acc1balance);

                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient Amount");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong pin");
                        pin.setText("");
                    }


                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Account no " +
                            "\n Or Card no");
                    acc1.setText("");
                    dcno.setText("");
                }
            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Aaack! You caught us with our pants down! Here we are, tinkering with the servers and you show up. How awkward! Try back in just a couple of minutes");

            }

        }



    }

    public static void main(String[] args) {
        new Withdraw();
    }
}