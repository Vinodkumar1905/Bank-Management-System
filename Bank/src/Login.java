import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {

    JButton sigh_in ,sighn_up,clear,delete;
    JTextField cardField;
    JPasswordField pinField;
    String pass;


    @Override
    public void actionPerformed(ActionEvent e) {
        String applino = ""+cardField.getText();
        String pin = ""+pinField.getText().trim();
        if(e.getSource()==clear){
            cardField.setText("");
            pinField.setText("");

        }
        else if (e.getSource()==sigh_in){
            try {
                Conn cn = new Conn();
                String query = "select Password from signup where Account_no = '"+applino+"'";
                ResultSet rs = cn.st.executeQuery(query);
                if(rs.next()) {
                    pass = rs.getString("Password");
                    if (pin.equals(pass)) {
                        setVisible(false);
                        new Transaction(applino);
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong Password");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Account does not exist");
                }
            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Aaack! You caught us with our pants down! Here we are, tinkering with the servers and you show up. How awkward! Try back in just a couple of minutes");

            }
        }
        else if (e.getSource()==sighn_up) {
            setVisible(false);
            new SighnUp().setVisible(true);
        } else if (e.getSource()==delete) {
            setVisible(false);
            new Delete().setVisible(true);
        }
    }

    Login(){
        setTitle("Vinod Group Of Banks");
        setLayout(null);
        //JLbale for image---->>>>
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("images/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100,100 ,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70,40,100,100);
        add(label);
//image ends here---->>>>>

        // for text on panel -->>>>
        JLabel text = new JLabel("We Welcome you to VGB ");
        text.setBounds(190,40,550,100);
        text.setFont(new Font("Algerian",Font.BOLD,30));
        add(text);

        JLabel card_no = new JLabel("Enter your Account no :");
        card_no.setBounds(130,160,250,40);
        card_no.setFont(new Font("Osward",Font.BOLD,20));
        add(card_no);

        cardField = new JTextField();
        cardField.setBounds(390,170,200,30);
        cardField.setFont(new Font("Osward",Font.BOLD,15));
        add(cardField);

        JLabel pin = new JLabel("Enter your pin : ");
        pin.setBounds(130,210,250,40);
        pin.setFont(new Font("Osward",Font.BOLD,25));
        add(pin);

        pinField = new JPasswordField();
        pinField.setBounds(390,215,200,30);
        add(pinField);
    //texts end here ----->>>>>>

    // buton starts->>>
        sigh_in = new JButton("Sighn In");
        sigh_in.setBounds(250,270,100,35);
        sigh_in.setBackground(Color.black);
        sigh_in.setForeground(Color.white);
        sigh_in.addActionListener(this);
        add(sigh_in);

        clear = new JButton("Clear");
        clear.setBounds(380,270,100,35);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        sighn_up = new JButton("Sighn Up");
        sighn_up.setBounds(250,330,100,35);
        sighn_up.setBackground(Color.black);
        sighn_up.setForeground(Color.white);
        sighn_up.addActionListener(this);
        add(sighn_up);

        delete = new JButton("Delete");
        delete.setBounds(380,330,100,35);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.addActionListener(this);
        add(delete);

        //for panel size and location
        setSize(800,450);
        setVisible(true);
        setBounds(350,200,750,450);
        getContentPane().setBackground(Color.white);

        //panal ends here



    }
    public static void main(String[] args) {
        new Login();
    }
}
