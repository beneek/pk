package pl.Java.kalorie;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.server.UID;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.xml.ws.Dispatch;

public class Main {

	static Baza baza;
	static BazaObject bazaObj;
	static boolean UserMacroUpdateFlag=false;
	
	public static void main(String[] args){
		
		//FirstThis();
		LoadData();
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				MainFrame frame = new MainFrame();
				//Frame frame = new Frame();
				frame.addWindowListener(new WindowListener() {
				
					
					public void windowOpened(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					
					public void windowIconified(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					
					public void windowDeiconified(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					
					public void windowDeactivated(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					public void windowClosing(WindowEvent arg0) {
						cloooooosing();
					}
					
					

					
					public void windowClosed(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					
					public void windowActivated(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);
				
			}
		});   
	}
	
public static void cloooooosing() {
		try {
			baza.InitDataBaseConnection();
			//baza.insertSkladnik();
			baza.DeleteDzien();
			baza.insertPosilek();
			baza.insertDzien();
			baza.upDateDzien();
			baza.upDatePosilek();
			if(UserMacroUpdateFlag){
				baza.updateMacro(BazaObject.UserDemandMacro.W,BazaObject.UserDemandMacro.B,BazaObject.UserDemandMacro.T);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		baza.CloseConnection();
		System.exit(0);
		
	}
	
static void LoadData(){
	
	baza = new Baza();
	bazaObj = new BazaObject();
	
	baza.InitSkladniki();
	baza.InitPotrawa();
	baza.InitDzien();
	baza.InitMacro();
	if (BazaObject.dni[0]==null){
		BazaObject.addDzien(new Dzien(BazaObject.getNextIDDzien(), new Date(System.currentTimeMillis())));
		SaveChanges.addInserDzien(1);
	}
		
		System.out.println("Skladniki:");
		for(Skladnik c: BazaObject.skladniki)
			System.out.println(c);
		
		/*System.out.println("Potrawy:");
		for(Posilek c: BazaObject.posilki)
			System.out.println(c);
		
		System.out.println("Dni:");
		for(Dzien c: BazaObject.dni)
			System.out.println(c);
	
		
	baza.upDateSkladnik( new Skladnik(1, "Zmieniony12", 69, 69, 59));
	baza.InitSkladniki();
	System.out.println("Skladniki:");
	for(Skladnik c: BazaObject.skladniki)
		System.out.println(c);	
*/
	
		
		
		
	baza.CloseConnection();
}
	

	
	public static void stop(){
		System.out.println("Test systemu");
	}
	
	
		
}
	


class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private int posilek_count;
	static int DisplayedDay;
	static DateControlPanel dateControlPanel;
	static PosilekPanel[] posilkiPanel;
	static JButton addButton ;
	JButton addSkladnik;
	private SkladnikAddFrame skladnikFrame;
	
	//rozmiary ITP 
	private final int PosilekPanelSzer=270;
	public final static int PosilekPanelDlug=236;
	private final int PosilekPanelPrzesuniecie=237;
	

	JTextField poleC1= new JTextField("Calkowicie");
	static JTextField poleC2= new JTextField();
	static JTextField poleC3= new JTextField();
	static JTextField poleC4= new JTextField();
	static JTextField poleC5= new JTextField();
	static JTextField poleB1= new JTextField("Brakuje");
	static JTextField poleB2= new JTextField();
	static JTextField poleB3= new JTextField();
	static JTextField poleB4= new JTextField();
	static JTextField poleB5= new JTextField();
	
	private int szerokosc=1215;
	private int wysokosc=445;
	
	public MainFrame(){
		
		super("Jedzonko Dupcio");
		setSize(szerokosc, wysokosc);
		setLayout(null);
		setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setBounds((screenSize.width-szerokosc)/2, (screenSize.height-wysokosc)/2,szerokosc,wysokosc);
		setIconImage(kit.getImage("ico.gif"));
		
		
		
		DisplayedDay=BazaObject.CountDni-1;	
		addButton= new JButton("Dodaj");
		addButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				
					int posID=BazaObject.getNextIDPosilek();
					BazaObject.addPosilek(  posID  );
					BazaObject.dni[DisplayedDay].add((BazaObject.posilki[BazaObject.CountPosilki-1]).id);
					SaveChanges.addInserPosilek(posID);
					SaveChanges.addUpdateDzien(BazaObject.dni[DisplayedDay].id);
					
					
				upDate();
			}
		});
		this.add(addButton);
		
		dateControlPanel= new DateControlPanel();
		dateControlPanel.setBounds(0, 0, 1000, 40);
		posilkiPanel= new PosilekPanel[5];
		for (int i=0 ; i<5 ; i++)
		{
			posilkiPanel[i]=new PosilekPanel();
			this.add(posilkiPanel[i]);
			posilkiPanel[i].setBounds(6+PosilekPanelPrzesuniecie*i,50,PosilekPanelDlug,PosilekPanelSzer);
			posilkiPanel[i].setVisible(false);
		}
		this.add(dateControlPanel);

		int Cx=400; 
		int Cy=325;
		poleC1.setBounds(Cx, Cy, 80, 25);
		poleC2.setBounds(Cx+80, Cy, 26, 25);
		poleC3.setBounds(Cx+80+26, Cy, 26, 25);
		poleC4.setBounds(Cx+80+26+26, Cy, 26, 25);
		poleC5.setBounds(Cx+70, Cy+25, 70, 25);
      	poleC1.setEditable(false);	poleC2.setEditable(false);	poleC3.setEditable(false);	poleC4.setEditable(false); 	poleC5.setEditable(false);
      	add(poleC1);add(poleC2);add(poleC3);add(poleC4);add(poleC5);
      	
      	int Bx=590; int By=325;
      	poleB1.setBounds(Bx, By, 80, 25);
		poleB2.setBounds(Bx+80, By, 30, 25);
		poleB3.setBounds(Bx+80+30, By, 30, 25);
		poleB4.setBounds(Bx+80+30+30, By, 30, 25);
		poleB5.setBounds(Bx+70, By+25, 70, 25);
		poleB1.setEditable(false);	poleB2.setEditable(false);	poleB3.setEditable(false);	poleB4.setEditable(false); 	poleB5.setEditable(false);
      	add(poleB1);add(poleB2);add(poleB3);add(poleB4);add(poleB5);
      	
      	addSkladnik= new JButton("Dodaj skladnik");
      	skladnikFrame = new SkladnikAddFrame();
		addSkladnik.addActionListener(new ActionListener() {
      	
      		public void actionPerformed(ActionEvent arg0) {
      		skladnikFrame.setVisible(true);
      		}
      	});
		addSkladnik.setBounds(25, Cy, 120, 30);
		add(addSkladnik);
		
		JFrame usermacroframe = new UserMacroFrame();
		JFrame skladnikimanagerframe = new SkladnikiManagerFrame();
		
		
		JMenuBar menu = new JMenuBar();
		JMenu Ustawienia = new JMenu("Ustawienia");
		JMenu plik = new JMenu ("Plik");
		JMenuItem zarzadzajSkladnikami = new JMenuItem("Zarz�dzaj sk�adnikami");
		JMenuItem edytujMacro = new JMenuItem("Edytuj dzienne zapotrzebowanie");
		JMenuItem zakoncz = new JMenuItem("Zako�cz");
		zarzadzajSkladnikami.addActionListener(new MenuNewFrameActionClass(skladnikimanagerframe));
		edytujMacro.addActionListener(new MenuNewFrameActionClass(usermacroframe));
		zakoncz.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				Main.cloooooosing();
			}
		});
		menu.add(plik);
		menu.add(Ustawienia);
		Ustawienia.add(zarzadzajSkladnikami);
		Ustawienia.addSeparator();
		Ustawienia.add(edytujMacro);
		plik.add(zakoncz);
		
		setJMenuBar(menu);
		
		upDate();		
	}

	class MenuNewFrameActionClass implements ActionListener{
		private JFrame frame;
		
		public MenuNewFrameActionClass(JFrame fr) {
		frame=fr;
		}
		
		
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(true);
		}
	}
	
static void upDate(){
		dateControlPanel.up();
		
		for(int i=0 ; i<5 ; i++)
			posilkiPanel[i].setVisible(false);
		
		for (int i=0 ; i<BazaObject.dni[DisplayedDay].current; i++)
		{
			posilkiPanel[i].setPos(BazaObject.dni[DisplayedDay].posilki[i]);
			posilkiPanel[i].setVisible(true);
		}
		if(BazaObject.dni[DisplayedDay].current>=5)
			addButton.setVisible(false);
		else
		{
			addButton.setBounds((BazaObject.dni[DisplayedDay].current)*PosilekPanelDlug+35, 90, 70, 50);
			addButton.setVisible(true);
		}
		poleC2.setText(""+BazaObject.dni[DisplayedDay].dayMacro.W);    		
		poleC3.setText(""+BazaObject.dni[DisplayedDay].dayMacro.B);    		
		poleC4.setText(""+BazaObject.dni[DisplayedDay].dayMacro.T);    		
	    poleC5.setText(BazaObject.dni[DisplayedDay].dayMacro.Kcal+" Kcal"); 

		if (BazaObject.CountDni-1==DisplayedDay)
		{
	    poleB2.setText(""+(BazaObject.UserDemandMacro.W-BazaObject.dni[DisplayedDay].dayMacro.W));    		
		poleB3.setText(""+(BazaObject.UserDemandMacro.B-BazaObject.dni[DisplayedDay].dayMacro.B));    		
		poleB4.setText(""+(BazaObject.UserDemandMacro.T-BazaObject.dni[DisplayedDay].dayMacro.T));    		
	    poleB5.setText((BazaObject.UserDemandMacro.Kcal-BazaObject.dni[DisplayedDay].dayMacro.Kcal)+" Kcal"); 
	    poleB1.setVisible(true);
	    poleB2.setVisible(true);
	    poleB3.setVisible(true);
	    poleB4.setVisible(true);
	    poleB5.setVisible(true);
		}
		else
		{
			poleB1.setVisible(false);
			poleB2.setVisible(false);
			poleB3.setVisible(false);
			poleB4.setVisible(false);
			poleB5.setVisible(false);
		}
}
	
public static void updateMainMacro(){
	BazaObject.dni[DisplayedDay].updateMacro();
	poleC2.setText(""+BazaObject.dni[DisplayedDay].dayMacro.W);    		
	poleC3.setText(""+BazaObject.dni[DisplayedDay].dayMacro.B);    		
	poleC4.setText(""+BazaObject.dni[DisplayedDay].dayMacro.T);    		
    poleC5.setText(BazaObject.dni[DisplayedDay].dayMacro.Kcal+" Kcal"); 
  /*  if (BazaObject.CountDni-1==DisplayedDay)
	{
    poleB2.setText(""+(BazaObject.UserDemandMacro.W-BazaObject.dni[DisplayedDay].dayMacro.W));    		
	poleB3.setText(""+(BazaObject.UserDemandMacro.B-BazaObject.dni[DisplayedDay].dayMacro.B));    		
	poleB4.setText(""+(BazaObject.UserDemandMacro.T-BazaObject.dni[DisplayedDay].dayMacro.T));    		
    poleB5.setText((BazaObject.UserDemandMacro.Kcal-BazaObject.dni[DisplayedDay].dayMacro.Kcal)+" Kcal"); 
    try{
    	if(Integer.parseInt(poleB2.getText())<0)
    		poleB2.setBackground(Color.RED);
    	else 
    		poleB2.setBackground(null);
    	if(Integer.parseInt(poleB3.getText())<0)
    		poleB3.setBackground(Color.RED);
    	else 
    		poleB3.setBackground(null);
    	if(Integer.parseInt(poleB4.getText())<0)
    		poleB4.setBackground(Color.RED);
    	else 
    		poleB4.setBackground(null);
    	}
    
    	catch(NumberFormatException e)
    	{
    		poleB2.setBackground(null);
    		poleB3.setBackground(null);
    		poleB4.setBackground(null);
        	}
	
	}*/
}

class DateControlPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton previous;
	JButton next;
	JLabel label;
	JButton usun;
	int choose;
	DateControlPanel(){
		
		previous = new JButton("Poprzedni");
		next = new JButton("Nastepny");
		label = new JLabel();
		usun= new JButton("Usu�");
		
		previous.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				if(DisplayedDay>0)
					DisplayedDay--;
				upDate();
			}			
		});
		next.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(DisplayedDay+1<BazaObject.CountDni)
					{
					DisplayedDay++;
					upDate();
					}
				else
					{
					choose=JOptionPane.showConfirmDialog(null, "Czy chcesz utworzyc nowy dzien?", "Brak kolejnych dni", JOptionPane.YES_NO_OPTION);
					if (choose==JOptionPane.YES_OPTION)
						{
						BazaObject.addDzien(new Dzien(BazaObject.getNextIDDzien(),new Date(  +BazaObject.dni[BazaObject.CountDni-1].data.getTime()+86400000) ));
						DisplayedDay++;
						SaveChanges.addInserDzien(BazaObject.dni[BazaObject.CountDni-1].id);
						upDate();
						}	
					}
			}
		});
		usun.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(BazaObject.CountDni>1 & BazaObject.CountDni-1==DisplayedDay)
				{
					BazaObject.deleteDzien();
					DisplayedDay--;
					upDate();
				}
			}
		});
		//setLayout(new BorderLayout());
		add(previous,BorderLayout.WEST);
		add(label,BorderLayout.SOUTH);
		add(next,BorderLayout.CENTER);
		add(usun);
		
	}
	
	void up(){
		if(DisplayedDay==BazaObject.CountDni-1)
			{
			next.setText("Dodaj dzien");
			usun.setVisible(true);
			}
		else
			{
			next.setText("Nastepny");
			usun.setVisible(false);
			}
		label.setText(""+BazaObject.dni[DisplayedDay]);
	}
	
}

}

