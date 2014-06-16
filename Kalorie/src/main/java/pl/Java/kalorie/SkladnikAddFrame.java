package pl.Java.kalorie;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class SkladnikAddFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton dodaj;
	JButton anuluj;
	JTextField pole1;
	JTextField pole2;
	JTextField pole3;
	JTextField pole4;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	
	private int szerokosc=300;
	private int wysokosc=190;
	
	public SkladnikAddFrame(){
		
		super("Dodaj nowy sk�adnik");
		setSize(szerokosc, wysokosc);
		setAlwaysOnTop(true);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setBounds((screenSize.width-szerokosc)/2, (screenSize.height-wysokosc)/2,szerokosc,wysokosc);
		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		dodaj=new JButton("Dodaj");
		anuluj=new JButton ("Anuluj");
		JPanel przyciskiPanel=new JPanel();
		przyciskiPanel.add(dodaj);
		przyciskiPanel.add(anuluj);
		dodaj.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				try{
					
					
							int w=Integer.parseInt(pole2.getText());
							int b=Integer.parseInt(pole3.getText());
							int t=Integer.parseInt(pole4.getText());
							String name= pole1.getText();
							Main.baza.InitDataBaseConnection();
							Main.baza.insertSkladnik(w, t, b, name);
							Main.baza.InitSkladniki();
							Main.baza.CloseConnection();
							for(int i=0 ; i<5 ; i++)
							MainFrame.posilkiPanel[i].skladnikiCombo.SkladnikiComboUpdate();
							pole1.setText(null);
							pole2.setText("");
							pole3.setText("");
							pole4.setText("");
							close();
							
					}
				catch(NumberFormatException c){
					JOptionPane.showMessageDialog(null, "Wpisales niedozwolone wartosci w pola!  :(","Blad",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		anuluj.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				pole1.setText(null);
				pole2.setText(null);
				pole3.setText(null);
				pole4.setText(null);
				close();	
			}
		});
		DocumentFilter numericFilter = new DocumentFilter(){

            @Override
            public void insertString(FilterBypass fb, int offset,
                    String string, AttributeSet attr)
                    throws BadLocationException {
                fb.insertString(offset, string.replaceAll("[^\\d]", ""), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length,
                    String text, AttributeSet attrs)
                    throws BadLocationException {

                fb.replace(offset, length, text.replaceAll("[^\\d]", ""), attrs);
            }
        };
        
		pole1=new JTextField(13);pole2=new JTextField(4);pole3=new JTextField(4);pole4=new JTextField(4);
		label1=new JLabel("Nazwa sk�adnika:");
		label2= new JLabel("W:");
		label3=new JLabel("B:");
		label4= new JLabel("T:");
		
		((AbstractDocument) pole2.getDocument()).setDocumentFilter(numericFilter);
    	((AbstractDocument) pole3.getDocument()).setDocumentFilter(numericFilter);
    	((AbstractDocument) pole4.getDocument()).setDocumentFilter(numericFilter);
		
		JPanel panelName= new JPanel();
		panelName.add(label1);
		panelName.add(pole1);
		
		JPanel panelW= new JPanel();
		panelW.add(label2);
		panelW.add(pole2);
		JPanel panelB= new JPanel();
		panelB.add(label3);
		panelB.add(pole3);
		JPanel panelT= new JPanel();
		panelT.add(label4);
		panelT.add(pole4);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		panelCentral.add(panelW,BorderLayout.NORTH);
		panelCentral.add(panelB,BorderLayout.CENTER);
		panelCentral.add(panelT,BorderLayout.SOUTH);
		
		add(panelName,BorderLayout.NORTH);
		add(panelCentral,BorderLayout.CENTER);
		add(przyciskiPanel,BorderLayout.SOUTH);
		
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
	}

private void close(){
	this.dispose();
}
	
}
