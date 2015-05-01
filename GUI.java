import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import static java.lang.System.exit;

public class GUI implements ActionListener
{
    String net_add="";
    String ip="";
    JTextArea incoming;
    BufferedReader macreader;
    JButton button = new JButton("START");
    String str;
    int x,y;
    JFrame f1,f2,f3,f4;
    JButton butscan,butip,butmac,butstart,butstart2,butstop,butstop2,butstart3,butstop3,exit;
    JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8;
    JPanel pnl1,pnl2,pnl3,pnl4;
    JTextArea area,area2,area3,area4;
    JTextField field,field2,field3,field4;
    JComboBox list,list2;
    
    public void frame1()
    {
        f1=new JFrame("NETWORKING");
        f1.setVisible(true);
        f1.setResizable(false);
        f1.setSize(600,420);
        f1.setLocation(385,170);
        pnl1=new JPanel();
        pnl1.setLayout(null);
        pnl1.setBackground(Color.green);
        butscan= new JButton("SCAN NETWORK");        
        butip=new JButton("IP SEARCHING");
        butmac=new JButton("MAC SEARCHING");
        exit=new JButton("EXIT"); 
        butscan.setBounds(200,90,200,40);
	butip.setBounds(200,150,200,40);
        butmac.setBounds(200,210,200,40);
        exit.setBounds(200,270,200,40);
        lbl1=new JLabel("   WifiStinson - Network Scanner");
        lbl1.setBounds(140,20,500,20);
        lbl1.setFont(new Font("Serif", Font.PLAIN, 20));
        pnl1.add(lbl1);
        pnl1.add(butscan);
	pnl1.add(butip);
	pnl1.add(butmac);
        pnl1.add(exit);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.add(pnl1);
        butscan.addActionListener(this);
        butip.addActionListener(this);
        butmac.addActionListener(this);
        exit.addActionListener(this);        
    }
    
    public void frame2()
    {
        f2= new JFrame("NETWORKING");
        setDesign();
        butstart=new JButton("SCAN");
        butstop=new JButton("BACK");   
        lbl2=new JLabel("NETWORK SCANNING");
        lbl2.setFont(new Font("Serif", Font.PLAIN, 20));
        pnl2 = new JPanel();
        pnl2.setBackground(Color.yellow);
        area = new JTextArea(19,50);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        JScrollPane scroller = new JScrollPane(area);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pnl2.add(lbl2);
        pnl2.add(scroller);
        pnl2.add(butstart);
        pnl2.add(butstop);
        f2.getContentPane().add(BorderLayout.CENTER, pnl2);
        f2.setSize(600, 420);
        f2.setLocation(385,170);
        f2.setResizable(false);
        f2.setVisible(false);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        butstart.addActionListener(this);
        butstop.addActionListener(this);
    }
    
    public void frame3() 
    {  
        f3=new JFrame("NETWORKING"); 
        setDesign();
        f3.setSize(600,420);
	f3.setLocation(385,170);
        pnl3=new JPanel();
        pnl3.setLayout(null);
        pnl3.setBackground(Color.PINK);        
        lbl3=new JLabel("IP-MAC SEARCHING...");
        lbl3.setBounds(200,20,500,20);
        lbl3.setFont(new Font("Serif", Font.BOLD, 20));
        lbl4=new JLabel("ENTER THE IP ADDRESS: ");
        lbl4.setBounds(50,100,250,40);
        lbl4.setFont(new Font("Serif", Font.PLAIN, 18));
        lbl5=new JLabel("MAC ADDRESS: ");
        lbl5.setBounds(50,200,250,40);
        lbl5.setFont(new Font("Serif", Font.PLAIN, 18));
//        field=new JTextField(15);
//        field.setBounds(340,100,200,40);
        list = new JComboBox(); 
        list.setBounds(340,100,200,40);
        list.addItem("");
        field2=new JTextField(20);
        field2.setBounds(340,200,200,40);
        field2.setEditable(false);
        butstart2=new JButton("SEARCH");
        butstart2.setBounds(180,300,100,40);        
        butstop2=new JButton("BACK"); 
        butstop2.setBounds(310,300,100,40);
        pnl3.add(lbl3);
        pnl3.add(lbl4);
//        pnl3.add(field);
        pnl3.add(list);
        pnl3.add(lbl5);
        pnl3.add(field2);
        pnl3.add(butstart2);
        pnl3.add(butstop2);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        f3.add(pnl3);
        f3.setVisible(false);
        butstart2.addActionListener(this);  
        butstop2.addActionListener(this);
        list.addActionListener(this);
    }
    
    public void frame4()
    {  
        f4=new JFrame("NETWORKING"); 
        setDesign();
        f4.setSize(600,420);
	f4.setLocation(385,170);
        pnl4=new JPanel();
        pnl4.setLayout(null);
        pnl4.setBackground(Color.lightGray);        
        lbl6=new JLabel("MAC-IP SEARCHING...");
        lbl6.setBounds(200,20,500,20);
        lbl6.setFont(new Font("Serif", Font.BOLD, 20));
        lbl7=new JLabel("ENTER THE MAC ADDRESS: ");
        lbl7.setBounds(50,100,250,40);
        lbl7.setFont(new Font("Serif", Font.PLAIN, 18));
        lbl8=new JLabel("IP ADDRESS: ");
        lbl8.setBounds(50,200,250,40);
        lbl8.setFont(new Font("Serif", Font.PLAIN, 18));
//        field3=new JTextField(17);
//        field3.setBounds(340,100,200,40);
        field4=new JTextField(15);
        field4.setBounds(340,200,200,40);
        //field4.setEditable(false);
        list2 = new JComboBox(); 
        list2.setBounds(340,100,200,40);
        list2.addItem("");
        butstart3=new JButton("SEARCH..");
        butstart3.setBounds(180,300,100,40);        
        butstop3=new JButton("BACK"); 
        butstop3.setBounds(310,300,100,40);
        pnl4.add(lbl6);
        pnl4.add(lbl7);
//        pnl4.add(field3);
        pnl4.add(lbl8);
        pnl4.add(field4);
        pnl4.add(list2);
        pnl4.add(butstart3);
        pnl4.add(butstop3);
        f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        f4.add(pnl4);
        f4.setVisible(false);
        butstart3.addActionListener(this);  
        butstop3.addActionListener(this);
    }    
    
    public static void main(String[] args) 
    {
        GUI gui = new GUI();
        gui.frame1();
        gui.frame2();
        gui.frame3();
        gui.frame4();
    }
    
    public void own(String str)
    {
        try 
        {	
            butstart.setText("Scanning...");  
            butstart.setEnabled(false);	
            area.append(str);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    public final void setDesign() 
    {
        try 
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } 
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException af) 
        {
            System.out.println(af);
        }
    }
    
    public void ipmac(String str)
    {                              
        field2.setText(str);
    }
    
    public void macip(String str)
    {   
        //System.out.println(str);
        field4.setText(str);
    }
    
    public void settinglist(String arr)
    {
        list.addItem(arr);
    }
    
    public void settinglist2(String arr)
    {
        list2.addItem(arr);
    }
  
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "SCAN NETWORK":    f1.setVisible(false);
                                    this.frame2();
                                    f2.setVisible(true);
                                    break;
            case "IP SEARCHING":    f1.setVisible(false);
                                    this.frame3();
                                    f3.setVisible(true); 
                                    IPScanner scan4 = new IPScanner();
                                    scan4.ipdropdown();
                                    break;
            case "MAC SEARCHING":   f1.setVisible(false);
                                    this.frame4();
                                    f4.setVisible(true);  
                                    IPScanner scan5 = new IPScanner();
                                    scan5.macdropdown();
                                    break;
            case "BACK":    f1.setVisible(true);
                            this.frame3();
                            this.frame4();
                            f2.setVisible(false);
                            f3.setVisible(false);
                            f4.setVisible(false);
                            break;
            case "EXIT":    exit(0);
                            break;
            case "SCAN":    IPScanner scan = new IPScanner();
                            //WifiStinson scan = new WifiStinson();
                            scan.action();
                            break;
            case "SEARCH":  Object ip1=list.getSelectedItem();
                            //System.out.println(ip1);
                            IPScanner scan2 = new IPScanner();
                            scan2.IPtoMAC(ip1);
                            break; 
            case "SEARCH..":    String mac1=list2.getSelectedItem().toString();
                                //System.out.println(mac1);
                                IPScanner scan3 = new IPScanner();
                                scan3.MACtoIP(mac1);
                                break;
        }
    }
    
}
