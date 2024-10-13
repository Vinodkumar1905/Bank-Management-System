import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignupTwo extends JFrame implements ActionListener {
    long accno;
    JTextField adharField,panField,nomenieField,passwordField;
    JFormattedTextField incomeField;
    JButton  next,back;
    JRadioButton yes,no;
    JComboBox occubox,religionbox,nomeniebox;
    ButtonGroup g = new ButtonGroup();
    SignupTwo() {

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
        form.setFont(new Font("Osward",Font.BOLD,30));
        add(form);

        JLabel detail = new JLabel("Page 2 : Additional Details");
        detail.setBounds(260,100,200,20);
        detail.setFont(new Font("Osward",Font.BOLD,15));
        add(detail);

        JLabel religion = new JLabel("Religion :");
        religion.setBounds(90,140,150,30);
        religion.setFont(new Font("Osward",Font.BOLD,20));
        add(religion);

        String religionopt[]={"Hindu","Christian","Sikh","Muslim"};
        religionbox = new JComboBox<>(religionopt);
        religionbox.setBounds(260,140,250,30);
        religionbox.setFont(new Font("Osward",Font.BOLD,15));
        religionbox.setBackground(Color.white);
        add(religionbox);


        JLabel income = new JLabel("Income");
        income.setBounds(90,180,150,30);
        income.setFont(new Font("Osward",Font.BOLD,20));
        add(income);

        incomeField = new JFormattedTextField();
        incomeField.setBounds(260,180,250,30);
        incomeField.setFont(new Font("Osward",Font.BOLD,15));
        add(incomeField);

        JLabel adhar = new JLabel("Aadhaar no :");
        adhar.setBounds(90,220,150,30);
        adhar.setFont(new Font("Osward",Font.BOLD,20));
        add(adhar);

        adharField = new JTextField();
        adharField.setBounds(260,220,250,30);
        adharField.setFont(new Font("Osward",Font.BOLD,15));
        add(adharField);

        JLabel pan = new JLabel("Pan no :");
        pan.setBounds(90,260,150,30);
        pan.setFont(new Font("Osward",Font.BOLD,20));
        add(pan);

        panField = new JTextField();
        panField.setBounds(260,260,250,30);
        panField.setFont(new Font("Osward",Font.BOLD,15));
        add(panField);

        JLabel occupation = new JLabel("Occupation :");
        occupation.setBounds(90,300,250,30);
        occupation.setFont(new Font("Osward",Font.BOLD,20));
        add(occupation);

        String occuopt[] = {"Bussiness","Self Employed","CEO","Salesman","Teacher","Doctor","Soldier","Unemployed"};
        occubox= new JComboBox<>(occuopt);
        occubox.setBounds(260,300,250,30);
        occubox.setFont(new Font("Osward",Font.BOLD,15));
        occubox.setBackground(Color.white);
        add(occubox);


        JLabel nomenie = new JLabel("Nomeniee :");
        nomenie.setBounds(90,340,250,30 );
        nomenie.setFont(new Font("Osward",Font.BOLD,20));
        add(nomenie);

        nomenieField = new JTextField();
        nomenieField.setBounds(260,340,250,30);
        nomenieField.setFont(new Font("Osward",Font.BOLD,15));
        add(nomenieField);

        JLabel nomenieRel = new JLabel("Relation :");
        nomenieRel.setBounds(90,380,150,30);
        nomenieRel.setFont(new Font("Osward",Font.BOLD,20));
        add(nomenieRel);

        String nomenieopt[]= {"Father","Mother","Son","Daughter","Brother","Spouse","Sister","Grandfather","Grandmother","Grandson","Granddaughter"};
        nomeniebox = new JComboBox<>(nomenieopt);
        nomeniebox.setBounds(260,380,250,30);
        nomeniebox.setFont(new Font("Osward",Font.BOLD,15));
        add(nomeniebox);

        JLabel password = new JLabel("Password");
        password.setBounds(90,420,150,30);
        password.setFont(new Font("Osward",Font.BOLD,20));
        add(password);

        passwordField = new JTextField();
        passwordField.setBounds(260,420,250,30);
        passwordField.setFont(new Font("Osward",Font.BOLD,15));
        add(passwordField);

        JLabel senior = new JLabel("Senior Citizen:");
        senior.setBounds(90,460,130,30);
        senior.setFont(new Font("Oswars",Font.BOLD,18));
        add(senior);

        yes = new JRadioButton("Yes");
        yes.setBounds(280,460,80,30);
        yes.setFont(new Font("Osward",Font.BOLD,15));
        yes.setBackground(Color.WHITE);
        add(yes);
        no = new JRadioButton("No");
        no.setBounds(420,460,100,30);
        no.setFont(new Font("Osward",Font.BOLD,15));
        no.setBackground(Color.WHITE);
        add(no);

        g.add(no);
        g.add(yes);

        setSize(800,450);
        setVisible(true);
        setBounds(370,100,630,600);
        getContentPane().setBackground(Color.white);

        next = new JButton("Next >>");
        next.setBounds(480,520,90,25);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);

        back = new JButton("<<Back");
        back.setBounds(370,520,90,25);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);


    }


    public static void main(String[] args) {
        new SignupTwo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            setVisible(false);
            new SighnUp().setVisible(true);
        } else if (e.getSource()==next) {
            String Relation_with_nomenie = (String) nomeniebox.getSelectedItem();
            String Nomenie = "" + nomenieField.getText();
            String Religion = (String) religionbox.getSelectedItem();
            String Adhar = "" + adharField.getText();
            String Occupation = (String) occubox.getSelectedItem();
            String Pan_no = "" + panField.getText();
            String Password = "" + passwordField.getText();
            String Income = "" + incomeField.getText();
            String Senior = null;

            if(Adhar.length()!=12){
                JOptionPane.showMessageDialog(null, "Invalid Aadhaar ");
            } else if (Pan_no.length()!=10) {
                JOptionPane.showMessageDialog(null, "Invalid Pan Card No");
            }


            try{
            if (yes.isSelected()) {
                Senior = "Yes";
            } else if (no.isSelected()) {
                Senior = "No";

            }
            else {
                JOptionPane.showMessageDialog(null, "Please select your gender", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            }catch (Exception exception){
                System.out.println(exception);
            }

            try {
                Conn cn = new Conn();
                String query = "SELECT MAX(Account_no) AS max_acc_no FROM signup";
                ResultSet rs = cn.st.executeQuery(query);
                if (rs.next()) {
                    accno = rs.getLong("max_acc_no");
                }


                String query1 = "UPDATE signup SET Religion = '" + Religion + "', Income = '" + Income + "', Adhar = '" + Adhar
                        + "', Pan_no = '" + Pan_no + "', Occupation = '" + Occupation + "', Nomenie = '" + Nomenie
                        + "', Relation_with_nomenie = '" + Relation_with_nomenie + "', Password = '" + Password
                        + "', Senior = '" + Senior + "' WHERE Account_no = '" + accno + "'";
                cn.st.executeUpdate(query1);
                String query2 = "insert into transaction (Account_no,Password) values('"+accno+"','"+Password+"')";
                cn.st.executeUpdate(query2);

                setVisible(false);
                 new SignupThree().setVisible(true);


            } catch (Exception ex) {
                System.out.println(ex);
            }



        }
    }
}
