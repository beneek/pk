package pl.Java.kalorie;


import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class PosilekPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int DEFAULT_WEIGHT=100;
	static final private int wysokosc=24;
	static final private int dlugosc_m=26;
	static final private int dlugosc_n=120;
	static final private int dlugosc_w=38;
	static final private int ILOSC_POL=Posilek.ILOSC_SKLADNIKOW*5;
		
	private JTextField pole1 ; 
	private	JTextField pole2 ;
	private	JTextField pole3 ;
	private	JTextField pole4 ;
	private	JTextField pole5 ;
	public  SkladnikiComboBox skladnikiCombo;
		
	private	MouseDeleteAction[] DeleteAction;
	
	private	JTextField poleM1 ; 
	private	JTextField poleM2 ;
	private	JTextField poleM3 ;
	private	JTextField poleM4 ;
	private	JTextField poleM5 ;
	
	private Posilek pos;
	
	private	JTextField[] pola;
	private int current;
	private int HLevel;
	private Skladnik skl;
	private int wag,flaga;
	public boolean HIDE_FLAG=false;
	
	
public PosilekPanel(){
	flaga=0;
	Init();
};	

public PosilekPanel(Posilek po){		
	pos=po;
	flaga=0;
	Init();
	
}
public PosilekPanel(int id){
	pos=BazaObject.IteratorPosilki.getPosilek(id);
	flaga=0;
	Init();
}

void setPos (int id){
	pos=BazaObject.IteratorPosilki.getPosilek(id);
	BudujTabele();
}


private void Init(){
	
	
	this.setLayout(null);
	skladnikiCombo = new SkladnikiComboBox() {
		public void Action(){
			pos.add(   ((Skladnik) skladnikiCombo.getSelectedItem()).id, DEFAULT_WEIGHT);
			BazaObject.saveLog.addUpdatePosilek(  pos.id  );
			HIDE_FLAG=true;
			BudujTabele();
		}
	};
	
	pola = new JTextField[ILOSC_POL];
	DeleteAction = new MouseDeleteAction[Posilek.ILOSC_SKLADNIKOW];
	for (int i =0 ; i<ILOSC_POL ; i++)
	pola[i]=new JTextField();
	for (int i =0 ; i<ILOSC_POL ; i++)
		{
			if(  i%5 ==0){
				pola[i].setEditable(false);
			}
			if( i%5 ==0)
			{	
				DeleteAction[i/5]= new MouseDeleteAction();
				pola[i].addMouseListener(DeleteAction[i/5]); 
				pola[i].setEditable(false);
			}
			else if( i%5==1)
			{	
				pola[i]=new WeightTextField() {
				public void change(){
					try{
						if(getText().trim().length() > 0 & flaga==1){
						pos.waga[ktory]  =  Integer.parseInt(this.getText());
						pos.upDate();
						BazaObject.saveLog.addUpdatePosilek(pos.id);
						UpdateMacroSkladnika(ktory);
						
						}
					}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Nie zjesz tyle :(","Nie ladnie....",JOptionPane.ERROR_MESSAGE);
					}
				
				}
				};
				pola[i].setEditable(true);
			}
			else 
				{
				pola[i].setEditable(false);
				}
				
		}
	for(int j=0 ; j<ILOSC_POL ; j++ )
		this.add(pola[j]);
	Opis();
	poleM1=new JTextField();poleM2=new JTextField();poleM3=new JTextField();
	poleM5=new JTextField();poleM4=new JTextField();
	
	
	skladnikiCombo.SkladnikiComboInit();
	this.add(poleM1);this.add(poleM2);this.add(poleM3);this.add(poleM4);this.add(poleM5);
	this.add(pole2);
	this.add(pole1);
	this.add(pole3);
	this.add(pole4);
	this.add(pole5);
	this.add(skladnikiCombo);
	flaga=1;
}



public void BudujTabele(){
	
	current=0;						//wysokosc jedenj ramki 22piks
	HLevel=22;
	
	for(int i=0 ; i<ILOSC_POL ; i++)
		 pola[i].setVisible(false);

	for (int i=0 ; i<Posilek.ILOSC_SKLADNIKOW ; i++){
		 DeleteAction[i].setValue(0);
	}
	
	for (int i =0 ; i<pos.current ; i++){	
				skl = BazaObject.IteratorSkladniki.getSkladnik(pos.skladniki[i]);
				wag = pos.waga[i];
		
				DeleteAction[i].setValue(i);							((WeightTextField) pola[current+1]).setValue(i,pola[current+1]);
				pola[current].setText(skl.name);  	        pola[current+1].setText(""+wag);
				pola[current].setBounds(0,HLevel,dlugosc_n,wysokosc); 	pola[current+1].setBounds(dlugosc_n, HLevel, dlugosc_w, wysokosc);
				pola[current+2].setText(""+skl.W*wag/100); 			 	pola[current+3].setText(""+skl.B*wag/100);
				pola[current+2].setBounds(dlugosc_n+dlugosc_w,HLevel,dlugosc_m,wysokosc);			pola[current+3].setBounds(dlugosc_n+dlugosc_w+dlugosc_m, HLevel, dlugosc_m,wysokosc);
				pola[current+4].setText(""+skl.T*wag/100);      
				pola[current+4].setBounds(dlugosc_n+dlugosc_w+2*dlugosc_m,HLevel,dlugosc_m,wysokosc);
									pola[current].setVisible(true);					
									pola[current+1].setVisible(true);					
									pola[current+2].setVisible(true);					
									pola[current+3].setVisible(true);					
									pola[current+4].setVisible(true);					
				HLevel+=wysokosc;
				current+=5;
	}
	
	if (pos.current<Posilek.ILOSC_SKLADNIKOW)
	{
		skladnikiCombo.setBounds(5, HLevel+45, 150, 25);
		skladnikiCombo.setVisible(true);
	}
	else skladnikiCombo.setVisible(false);
	
	
	
	poleM1.setText("Ogolnie");      		poleM1.setBounds(108,HLevel+5,50,25);
	poleM2.setText(""+pos.macro.W);    		poleM2.setBounds(158,HLevel+5,26,25);
	poleM3.setText(""+pos.macro.B);    		poleM3.setBounds(184,HLevel+5,26,25);
	poleM4.setText(""+pos.macro.T);    		poleM4.setBounds(210,HLevel+5,26,25);
    poleM5.setText(pos.macro.Kcal+" Kcal"); poleM5.setBounds(162,HLevel+30,70,25);
	poleM1.setEditable(false);	poleM2.setEditable(false);	poleM3.setEditable(false);	poleM4.setEditable(false); 	poleM5.setEditable(false);
	MainFrame.updateMainMacro();
	
	System.out.println("===========================BudujTabele()=============================");
	//System.out.println(SaveChanges.DniInsert[0]);
	for(Skladnik c: BazaObject.skladniki){
		if(c!=null)
			System.out.println("name="+c.name);
	}
	
}

private void UpdateMacroSkladnika(int i){
	int position=(i)*5;
	skl= BazaObject.IteratorSkladniki.getSkladnik(pos.skladniki[i]);
	wag=pos.waga[i];
	pola[position+2].setText(""+skl.W*wag/100); 			 							
	pola[position+3].setText(""+skl.B*wag/100);
	pola[position+4].setText(""+skl.T*wag/100);      
	
	poleM1.setText("Ogolnie");      		
	poleM2.setText(""+pos.macro.W);    		
	poleM3.setText(""+pos.macro.B);    		
	poleM4.setText(""+pos.macro.T);    		    
	poleM5.setText(pos.macro.Kcal+" Kcal"); 

	MainFrame.updateMainMacro();
}

public void Opis(){
		pole1 = new JTextField("Produkt");  pole2= new JTextField("Waga");
		pole1.setBounds(0,0,120,22);	  pole2.setBounds(120, 0, 38, 22);
		pole1.setEditable(false);		  pole2.setEditable(false);
		pole3 = new JTextField("W");      pole4= new JTextField("B");
		pole3.setBounds(158,0,26,22);	  pole4.setBounds(184, 0, 26, 22);
		pole3.setEditable(false);		  pole4.setEditable(false);
		pole5 = new JTextField("T");      
		pole5.setBounds(210,0,26,22);	  
		pole5.setEditable(false);		  
}

// ActionListenery do interfejsu  ==================
class  MouseDeleteAction implements MouseListener{

	private int ktory_i;
	private int flagaMouse;
	
	
	public MouseDeleteAction() {
		ktory_i=0;
	}
	
	void setValue(int i){
		ktory_i=i;
	}
	
	
	public void mouseReleased(MouseEvent arg0) {}
	
	public void mousePressed(MouseEvent arg0) {
		if( (arg0.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) != 0){
			System.out.println("Mouse Pressed");
			flagaMouse=1;
		}
	}
	
	
	public void mouseExited(MouseEvent arg0) {}
	
	public void mouseEntered(MouseEvent arg0) {}
	
	public void mouseClicked(MouseEvent arg0) {
		if (flagaMouse==1)
		{
			flagaMouse=0;
			BazaObject.saveLog.addUpdatePosilek(pos.id);
			pos.delete(ktory_i);
			
			BudujTabele();
		}
	}
	
}

// =================================================
}