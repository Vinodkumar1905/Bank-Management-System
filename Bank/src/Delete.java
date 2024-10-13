import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete extends JFrame implements ActionListener {
    JTextField nameField,passField,applField;
    JButton delete,home;
    String pin;

    Delete(){
        setTitle("Delete Account");
        setLayout(null);
        //JLbale for image---->>>>
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("images/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(80,80 ,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(40,30,100,100);
        add(label);

        JLabel form = new JLabel("VGB Acc Deletion Form..");
        form.setBounds(190,20,550,100);
        form.setFont(new Font("Osward",Font.BOLD,30));
        add(form);

        JLabel name = new JLabel("Name :");
        name.setBounds(90,140,150,30);
        name.setFont(new Font("Osward",Font.BOLD,20));
        add(name);

        nameField = new JTextField();
        nameField.setBounds(260,140,250,30);
        nameField.setFont(new Font("Osward",Font.BOLD,15));
        add(nameField);

        JLabel pass = new JLabel("Password :");
        pass.setBounds(90,180,150,30);
        pass.setFont(new Font("Osward",Font.BOLD,20));
        add(pass);

        passField = new JFormattedTextField();
        passField.setBounds(260,180,250,30);
        passField.setFont(new Font("Osward",Font.BOLD,15));
        add(passField);

        JLabel appl = new JLabel("Application No :");
        appl.setBounds(90,220,150,30);
        appl.setFont(new Font("Osward",Font.BOLD,20));
        add(appl);

        applField = new JFormattedTextField();
        applField.setBounds(260,220,250,30);
        applField.setFont(new Font("Osward",Font.BOLD,15));
        add(applField);

        delete = new JButton("Delete >>");
        delete.setBounds(400,270,90,25);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.addActionListener(this);
        add(delete);

        home = new JButton("Home");
        home.setBounds(290,270,90,25);
        home.setBackground(Color.black);
        home.setForeground(Color.white);
        home.addActionListener(this);
        add(home);

        setSize(800,450);
        setVisible(true);
        setBounds(350,200,650,450);
        getContentPane().setBackground(Color.white);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String applino = "" + applField.getText();
        String name = "" + nameField.getText();
        String pass=""+passField.getText();


        if(e.getSource()==home){
            setVisible(false);
            new Login().setVisible(true);
        } else if (e.getSource()==delete) {
            Conn c =new Conn();
            try {
                String check = "select Password from signup where Account_no = '"+applino+"'";
                ResultSet rs = c.st.executeQuery(check);
                rs.next();
                pin = rs.getString("Password");

                if(pass.equals(pin)){
                    String query = " delete from signup where Account_no ='"+applino+"' and Name = '"+name+"'";
                    c.st.executeUpdate(query);

                    JOptionPane.showMessageDialog(null,"Account  deleted Successfully");

                }

                else if(!pass.equals(pin)){
                    JOptionPane.showMessageDialog(null,"Account no don't matches with password");
                }



            } catch (SQLException ex) {
                System.out.println(ex);
            }




        }

    }

    public static void main(String[] args) {
        new Delete();
    }
}
