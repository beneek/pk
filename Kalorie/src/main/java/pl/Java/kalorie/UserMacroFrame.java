package pl.Java.kalorie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserMacroFrame extends JFrame{

	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JTextField field1;
	JTextField field2;
	JTextField field3;
	WeightTextField W;
	WeightTextField B;
	WeightTextField T;
	JTextField poleM1;
	JTextField poleM5;
	Macro macro;
	int HLevel=12;
	JButton zapisz;
	
	private int szerokosc=350;
	private int wysokosc=240;
	
	public UserMacroFrame(){
	
		super("Edytuj dzienne zapotrzebowanie");
		setSize(szerokosc,wysokosc );
		setAlwaysOnTop(true);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setBounds((screenSize.width-szerokosc)/2, (screenSize.height-wysokosc)/2,szerokosc,wysokosc);
		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener(){
			public void windowClosing(WindowEvent arg0) {
			close();
			
		}

			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
	});
		
		label1 = new JLabel("Procentowy rozk�ad wartosci od�ywczych");
		label2 = new JLabel("W�glowodany");
		label3 = new JLabel("Bia�ko");
		label4 = new JLabel("T�uszcze");
		field1 = new JTextField();
		field1.setEditable(false);
		field2 = new JTextField();
		field2.setEditable(false);
		field3 = new JTextField();
		field3.setEditable(false);
		
		macro= new Macro();
		macro= BazaObject.UserDemandMacro;
		
		W = new WeightTextField() {
			public void change(){
			 MyAction(W, CO.W);
			}
		};
		B = new WeightTextField() {
			public void change(){
				MyAction(B,CO.B);
			}
		};
		T = new WeightTextField() {
			public void change(){
				MyAction(T,CO.T);
			}
		};
		
		zapisz = new JButton("Zapisz");
		zapisz.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				BazaObject.UserDemandMacro=macro;
				Main.UserMacroUpdateFlag=true;
				MainFrame.updateMainMacro();
				close();
			}
		});
		
		poleM1= new JTextField();poleM5= new JTextField();
		poleM1.setText(" Makrosk�adniki:");      	
		W.setText(""+macro.W);    		
		B.setText(""+macro.B);    		
		T.setText(""+macro.T);    		
	    poleM5.setText(macro.Kcal+" Kcal"); poleM5.setBounds(45,HLevel+35,70,25);
		poleM1.setEditable(false);poleM5.setEditable(false);
		Print();
		
		//=================rozklad====================
		JPanel panelNorth = new JPanel();
		JPanel panelCentral = new JPanel();
		JPanel panelSouth = new JPanel();
		
		panelNorth.add(poleM1);panelNorth.add(W);panelNorth.add(B);panelNorth.add(T);panelNorth.add(poleM5);
		panelNorth.setLayout(null);
		int przesuniecie=70;
		poleM1.setBounds(przesuniecie,HLevel+5,100,25);
		W.setBounds(przesuniecie+101,HLevel+5,32,25);
		B.setBounds(przesuniecie+133,HLevel+5,32,25);
		T.setBounds(przesuniecie+165,HLevel+5,32,25);
		poleM5.setBounds(przesuniecie+45,HLevel+35,70,25);
		
		panelCentral.add(label1);panelCentral.add(label2);panelCentral.add(label3);panelCentral.add(label4);
		panelCentral.add(field1);panelCentral.add(field2);panelCentral.add(field3);
		panelCentral.setLayout(null);
		int height=19;  int y1=110;  przesuniecie=90;
		label1.setBounds(40, 0,300, height);
		label2.setBounds(przesuniecie,height+3, y1, height);    field1.setBounds(y1+przesuniecie,height+3, 30, height);

		label3.setBounds(przesuniecie,height*2+6, y1, height);    field2.setBounds(y1+przesuniecie,height*2+6, 30, height);

		label4.setBounds(przesuniecie,height*3+9, y1, height);    field3.setBounds(y1+przesuniecie,height*3+9, 30, height);
		
		panelSouth.add(zapisz);
		
		JPanel panelMainCentral = new JPanel();
		panelMainCentral.setLayout(new GridLayout(2,0));
		panelMainCentral.add(panelNorth);
		panelMainCentral.add(panelCentral);
		
		//add(panelNorth,BorderLayout.NORTH);
		add(panelMainCentral,BorderLayout.CENTER);
		add(panelSouth,BorderLayout.SOUTH);
		
		//=========================rozklad=============
	}
	
	private void close(){
		this.dispose();
	}
	
	private void Print(){	
	    poleM5.setText(macro.Kcal+" Kcal");
	    float kcal=macro.Kcal;
	    float W=macro.W;
	    float B=macro.B;
	    float T=macro.T;
	    field1.setText(Math.round((4*W)/kcal*100)+"");
	    field2.setText(Math.round(4*B/kcal*100)+"");
	    field3.setText(Math.round(9*T/kcal*100)+"");
	}
	
	public enum CO {
			W,B,T
	}
	
	private void MyAction(WeightTextField f1, CO co){
		try{
			if(f1.getText().trim().length() > 0 ){
				switch (co){
				case W : macro.W=Integer.parseInt(W.getText());
						 break;
				case B : macro.B=Integer.parseInt(B.getText());
				 		 break;
				case T : macro.T=Integer.parseInt(T.getText());
						 break;
				}
				macro.update();
				Print();
			}
		}
	catch(NumberFormatException e){
		JOptionPane.showMessageDialog(null, "Nie zjesz tyle :(","Nie ladnie....",JOptionPane.ERROR_MESSAGE);
		}
	}
}
