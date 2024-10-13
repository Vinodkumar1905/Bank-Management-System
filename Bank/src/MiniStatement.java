import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MiniStatement extends JFrame implements ActionListener {
    String acc,card_no;
    JButton Exit;

    MiniStatement(String account_no){setLayout(null);
        int y_axis =160;
        this.acc = account_no;

        setTitle("Mini Statement");
        JLabel header = new JLabel("Vinod Group of Banks");
        header.setBounds(70,10,500,30);
        header.setFont(new Font("Times New roman",Font.BOLD,25));
        add(header);

        JLabel account = new JLabel("Account no :");
        account.setBounds(20,70,150,20);
        account.setFont(new Font("Times New roman",Font.BOLD,20));
        add(account);

        JLabel accno = new JLabel(account_no);
        accno.setBounds(160,70,150,30);
        accno.setFont(new Font("Times New roman",Font.BOLD,20));
        add(accno);

        JLabel card = new JLabel("Card No : ");
        card.setBounds(20,100,150,30);
        card.setFont(new Font("Times New roman",Font.BOLD,20));
        add(card);

        Conn cn = new Conn();
        String query="select CardNo from signup where Account_no ='"+account_no+"'" ;
        try {
            ResultSet rs = cn.st.executeQuery(query);
            rs.next();
            card_no = rs.getString("CardNo");

        } catch (SQLException e) {
            System.out.println(e);
        }
        JLabel cardno = new JLabel(card_no.substring(0,4)+"XXXXXXXXXXX"+card_no.substring(12));
        cardno.setBounds(160,100,250,30);
        cardno.setFont(new Font("Times New roman",Font.BOLD,20));
        add(cardno);

        query="select * from data_"+account_no+" ORDER BY srno DESC LIMIT 8";
        try{
            ResultSet rs = cn.st.executeQuery(query);


            while (rs.next()){
                String print = "" +rs.getString("Date")+"  "+rs.getString("Amount")+"   "+rs.getString("Type")+"   "+rs.getString("Source");
                JLabel i = new JLabel(print);
                i.setBounds(20,y_axis,300,20);
                i.setFont(new Font("Times New roman",Font.BOLD,15));
                add(i);
                y_axis = y_axis+30;

            }
        }catch (Exception ex){
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Aaack! You caught us with our pants down! Here we are, tinkering with the servers and you show up. How awkward! Try back in just a couple of minutes");

        }



        y_axis =y_axis +60;

        Exit = new JButton("Back");
        Exit.setBounds(350, y_axis, 70, 30);
        Exit.addActionListener(this);
        add(Exit);


        setSize(450,y_axis+100);
        setLocation(400,100);
        getContentPane().setBackground(Color.white);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ex) {
        setVisible(false);
        new Transaction(acc).setVisible(true);

    }

    public static void main(String[] args) {
        new MiniStatement(""+230018);
    }
}