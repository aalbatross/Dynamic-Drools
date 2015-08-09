/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;
import org.drools.io.ResourceFactory;


/**
 *
 * @author Ravi
 */
public class JDrools extends JPanel implements ActionListener {
    private JLabel jengine;
    private JTextField enginePort;
    private JButton startEngine;
    private JComboBox showAllRule;
    private JButton addRule;
    private JButton removeRule;
    private JFileChooser jRuleBrowse;
    private Drooler drools;
    private ServerSocket server;
    
    
    
    public JDrools()
    {
        this.setLayout(null);
        
        this.jengine=new JLabel("Engine Port");
        this.jengine.setBounds(100, 50, 150, 50);
        this.add(this.jengine);
        
        
        this.enginePort=new JTextField();
        this.enginePort.setBounds(250, 50, 150, 50);
        this.enginePort.addActionListener(this);
        this.add(this.enginePort);
        
        this.startEngine=new JButton("Start Engine");
        this.startEngine.setBounds(175, 125, 150, 50);
        this.startEngine.addActionListener(this);
        //this.startEngine.setEnabled(false);
        this.add(this.startEngine);
        
        this.showAllRule=new JComboBox();
        this.showAllRule.setBounds(25, 200, 150, 50);
        this.showAllRule.addActionListener(this);
        //this.showAllRule.setEnabled(false);
        this.add(this.showAllRule);
        
        this.addRule=new JButton("Add Rule");
        this.addRule.setBounds(250, 200, 150, 50);
        this.addRule.addActionListener(this);
        //this.addRule.setEnabled(false);
        this.add(this.addRule);
        
        this.removeRule=new JButton("Remove Rule");
        this.removeRule.setBounds(475, 200, 150, 50);
        this.removeRule.addActionListener(this);
        //this.removeRule.setEnabled(false);
        this.add(this.removeRule);
        
        this.jRuleBrowse=new JFileChooser();
        this.add(this.jRuleBrowse);
        
       this.setSize(700,360);         
    }
    /**
     * @param args the command line arguments
     */
    class NetworkThread implements Runnable
    {

        @Override
        public void run() {
            try
                    {
                        System.out.println("Started!!!");
                        while(true)
                        {
                            Socket s=server.accept();
                            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                            Object read = ois.readObject();
                            System.out.println("Data coming "+read.getClass().toString());
                            drools.getKsession().insert(read);
                            drools.getKsession().fireAllRules();
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
        }
                
    }
    
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        JFrame jframe = new JFrame("CEP Server");
        
        JDrools j=new JDrools();
        j.setBackground(Color.yellow);
        jframe.add(j);
        jframe.setSize(700, 360);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.startEngine)
        {
            int port =Integer.parseInt(this.enginePort.getText());
            try
            {
                
                this.server=new ServerSocket(port);
                System.out.println(port+" "+InetAddress.getLocalHost().getHostAddress());
                this.drools=new Drooler(new String[]{JDrools.class.getResource("basic.drl").getFile()});
                Collection<KnowledgePackage> kpkg = this.drools.getKbase().getKnowledgePackages();
                Iterator i=   kpkg.iterator();
			while(i.hasNext())
			{
				KnowledgePackage kp=(KnowledgePackage)i.next();
				Collection<Rule> r= kp.getRules();
				Iterator i1=r.iterator();
					while(i1.hasNext())
					{
						Rule x=(Rule)i1.next();
						this.showAllRule.addItem(x.getPackageName()+"/"+x.getName());
					
					}
			}
               
                        NetworkThread th=new NetworkThread();
                        new Thread(th).start();
            }
            catch(Exception ex)
            {
                this.display(ex.getMessage());
                ex.printStackTrace();
            }
             
            
        }
        if(e.getSource()==this.addRule)
        {
            KnowledgeBuilder kbuild=KnowledgeBuilderFactory.newKnowledgeBuilder();
           
            int option = this.jRuleBrowse.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
	            try{
	                kbuild.add( ResourceFactory.newFileResource( this.jRuleBrowse.getSelectedFile().getPath() ),
	            			ResourceType.DRL);
	            	if(kbuild.hasErrors())
	            	{
	            		JOptionPane.showMessageDialog(this, kbuild.getErrors());
	            	}
	            	else
	            	{
	            		JOptionPane.showMessageDialog(this, "Successfully Compiled");
                                this.drools.getKbase().addKnowledgePackages(kbuild.getKnowledgePackages());
                                this.display("New Rule added!!!");
                                this.refreshList();
	            	}
                        
	            }
	            catch(Exception ex)
	            {
	            	this.display(ex.getMessage());
	            }
	        }
        }
        if(e.getSource()==this.removeRule)
        {
            String selectedItem = this.showAllRule.getSelectedItem().toString();
            String[] split=selectedItem.split("/");
            this.drools.getKbase().removeRule(split[0], split[1]);
            this.display(split[1]+ " Rule removed!!!");
            this.refreshList();
        }
    }
    
    public void display(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }
    
    public void refreshList()
    {
        this.showAllRule.removeAllItems();
        Collection<KnowledgePackage> kpkg = this.drools.getKbase().getKnowledgePackages();
                Iterator i=   kpkg.iterator();
			while(i.hasNext())
			{
				KnowledgePackage kp=(KnowledgePackage)i.next();
				Collection<Rule> r= kp.getRules();
				Iterator i1=r.iterator();
					while(i1.hasNext())
					{
						Rule x=(Rule)i1.next();
						this.showAllRule.addItem(x.getPackageName()+"/"+x.getName());
					
					}
			}
     }
            
    
}
