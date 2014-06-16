package pl.Java.kalorie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SkladnikiManagerFrame extends JFrame{

	SkladnikiComboBox skladnikiCombo;
	JLabel labelN;
	JLabel labelW;
	JLabel labelB;
	JLabel labelT;
	WeightTextField W;
	WeightTextField B;
	WeightTextField T;
	JTextField nazwa;
	JButton usun;
	JButton zmien;
	Skladnik skladnik;
	
	private int szerokosc=300;
	private int wysokosc=190; 
	
	public SkladnikiManagerFrame(){
		
		super("Zarz�dzaj sk�adnikami");
		setSize(szerokosc, wysokosc);
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

		W= new WeightTextField() {};
		B= new WeightTextField() {};
		T= new WeightTextField() {};
		nazwa= new JTextField(13);
		W.setColumns(4);
		B.setColumns(4);
		T.setColumns(4);
		
		usun=new JButton("Usu� sk�adnik");
		zmien=new JButton("Aktualizuj sk�adnik");
		labelN=new JLabel("Nazwa");
		labelW=new JLabel("W");
		labelB=new JLabel("B");
		labelT=new JLabel("T");
		
		skladnikiCombo= new SkladnikiComboBox() {
			public void Action(){
			skladnik= (Skladnik) this.getSelectedItem();
			nazwa.setText(skladnik.name);
			W.setText(skladnik.W+"");
			B.setText(skladnik.B+"");
			T.setText(skladnik.T+"");
			}
		};
		skladnikiCombo.SkladnikiComboInit();
		
		zmien.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(!(   B.getText().equals(skladnik.B+"") &&  W.getText().equals(skladnik.W+"") &&  T.getText().equals(skladnik.T+"") &&  nazwa.getText().equals(skladnik.name ) ) )
				{   System.out.println("Weszlo!!!!!!!!!!!!!!!!!!!!!!");
					try{
						skladnik.B=Integer.parseInt(B.getText());
						skladnik.W=Integer.parseInt(W.getText());
						skladnik.T=Integer.parseInt(T.getText());
						skladnik.name=nazwa.getText();
						Main.baza.InitDataBaseConnection();
						Main.baza.upDateSkladnik(skladnik);
						Main.baza.InitSkladniki();
						Main.baza.CloseConnection();
						for(int i=0 ; i<5 ; i++){
						MainFrame.posilkiPanel[i].skladnikiCombo.SkladnikiComboUpdate();
						MainFrame.posilkiPanel[i].BudujTabele();
						}
						skladnikiCombo.SkladnikiComboUpdate();
						close();
					}
					catch(NumberFormatException c){
						JOptionPane.showMessageDialog(null, "Wpisales niedozwolone wartosci w pola!  :(","Blad",JOptionPane.ERROR_MESSAGE);
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		skladnik= (Skladnik) skladnikiCombo.getSelectedItem();
		nazwa.setText(skladnik.name);
		W.setText(skladnik.W+"");
		B.setText(skladnik.B+"");
		T.setText(skladnik.T+"");
		
		//===========rozkad=========================
		
		JPanel panelCombo = new JPanel();  // NORTH
		
		JPanel panelSkladnik1 = new JPanel();//	/	CENTER
		
		JPanel panelButton = new JPanel();  // SOUTH
		
		int x=15;
		panelSkladnik1.setLayout(null);
		panelSkladnik1.add(labelN);labelN.setBounds(80,2,60,19);
		panelSkladnik1.add(labelW);labelW.setBounds(168,2,20,19);
		panelSkladnik1.add(labelB);labelB.setBounds(203,2,20,19);
		panelSkladnik1.add(labelT);labelT.setBounds(238,2,20,19);
		panelSkladnik1.add(nazwa);nazwa.setBounds(x+3, 20, 140, 23);
		panelSkladnik1.add(W);		W.setBounds(x+143, 20, 35, 23);
		panelSkladnik1.add(B);		B.setBounds(x+178, 20, 35, 23);
		panelSkladnik1.add(T);		T.setBounds(x+213, 20, 35, 23);
		
		
		panelButton.add(zmien);
		panelButton.add(usun);
		 
		panelCombo.add(skladnikiCombo);
		
		setLayout(new BorderLayout());
		add(panelCombo,BorderLayout.NORTH);
		add(panelSkladnik1,BorderLayout.CENTER);
		add(panelButton,BorderLayout.SOUTH);
		
		//rozklad========================================================
	}
	

	
	private void close(){
		this.dispose();
	}
}
