package pl.Java.kalorie;


public class BazaObject {

	static Skladnik[] skladniki ;   // tu bedzie klasa baza skladniki z iteratorem 
	public static IteratorSkladnikClass IteratorSkladniki;
	public static int arraySizeSkladniki;
	public static int CountSkladniki;
	
	static Posilek[] posilki ;
	static IteratorPosilkiClass IteratorPosilki;
	static int arraySizePosilki;
	static int CountPosilki;
	static int[] freeIDPosilki;
	
	public static Dzien[] dni ;
	static IteratorDniClass IteratorDni;
	static int arraySizeDni;
	static int CountDni;
	static int[] freeIDDni;

	static Macro UserDemandMacro;

	static SaveChanges saveLog;
	
	public BazaObject(){
		
		skladniki= new Skladnik[3];
		IteratorSkladniki = new IteratorSkladnikClass();
		arraySizeSkladniki=3;
		CountSkladniki=0;
		
		posilki= new Posilek[3];
		IteratorPosilki = new IteratorPosilkiClass();
		arraySizePosilki=3;
		CountPosilki=0;
		freeIDPosilki=new int[10];
		
		dni= new Dzien[3];
		IteratorDni = new IteratorDniClass();
		arraySizeDni=3;
		CountDni=0;
		freeIDDni= new int[10];
		
		for(int i=0 ; i<10 ; i ++)
		{
			freeIDDni[i]=0;
			freeIDPosilki[i]=0;
		}
		UserDemandMacro= new Macro();
		saveLog= new SaveChanges();
	}
		
	
	
	
	public static void addDzien(Dzien dzionek ){	
		
		if (CountDni >= arraySizeDni) {
	           Dzien[] tempArray = new Dzien[arraySizeDni];
	           for (int i = 0; i < arraySizeDni; i++)
	           {tempArray[i] = dni[i];}
	           dni = null;
	           arraySizeDni+= 3;           
	           dni = new Dzien[arraySizeDni];
	           for (int i = 0; i < (arraySizeDni - 3); i++) {
	               dni[i] = tempArray[i];
	           }
	       }
	       dni[CountDni++] = dzionek; 
	}
	
	
	public static void addSkladnik(int id ,String n, int w, int b ,int t ){
		
		if (CountSkladniki >= arraySizeSkladniki) {
	           Skladnik[] tempArray = new Skladnik[arraySizeSkladniki];
	           for (int i = 0; i < arraySizeSkladniki; i++)
	           {tempArray[i] = skladniki[i];}
	           skladniki = null;
	           arraySizeSkladniki+= 3;           
	           skladniki = new Skladnik[arraySizeSkladniki];
	           for (int i = 0; i < (arraySizeSkladniki - 3); i++) {
	               skladniki[i] = tempArray[i];
	           }
	       }
	       skladniki[CountSkladniki++] = new Skladnik(id,n,w,b,t); 
	}
	
	public static void addPosilek( int ID ){
		
		if (CountPosilki >= arraySizePosilki) {
	           Posilek[] tempArray = new Posilek[arraySizePosilki];
	           for (int i = 0; i < arraySizePosilki; i++)
	           {tempArray[i] = posilki[i];}
	           posilki = null;
	           arraySizePosilki+= 3;           
	           posilki = new Posilek[arraySizePosilki];
	           for (int i = 0; i < (arraySizePosilki - 3); i++) {
	               posilki[i] = tempArray[i];
	           }
	       }
	       posilki[CountPosilki++] = new Posilek(ID); 
	}
	public static void addPosilek(Posilek pos ){
		
		if (CountPosilki >= arraySizePosilki) {
	           Posilek[] tempArray = new Posilek[arraySizePosilki];
	           for (int i = 0; i < arraySizePosilki; i++)
	           {tempArray[i] = posilki[i];}
	           posilki = null;
	           arraySizePosilki+= 3;           
	           posilki = new Posilek[arraySizePosilki];
	           for (int i = 0; i < (arraySizePosilki - 3); i++) {
	               posilki[i] = tempArray[i];
	           }
	       }
	       posilki[CountPosilki++] = pos; 
	}
	
static void deleteDzien(){
	SaveChanges.addDeleteDzien(dni[CountDni-1].id);
	for(int i=0 ; i<10 ; i++)
	{
		if(freeIDDni[i]!=0)
		{
			freeIDDni[i]=dni[CountDni-1].id;
			break;
		}
	}
	
	dni[CountDni-1]=null;
	CountDni--;
}
public static int getNextIDDzien(){
	
	if(dni[0]==null)
		return 1;
	for(int i=0 ; i<10 ; i++)
		if(freeIDDni[i]!=0)
		{
			int d = freeIDDni[i];
			freeIDDni[i]=0;
			return d;
		}
	int ID;
	boolean flaga=true;
	while(true)
	{
		IteratorDni.first();
		ID= dni[CountDni-1].id + 1;
		while(IteratorDni.isDone())
			{
			 if(ID==IteratorDni.currentItem().id)
				 {
				 flaga=false;
				 break;
				 }
			  IteratorDni.next();
			}
		if(flaga)
			return ID;
	}	
}
static int getNextIDPosilek(){
	
	if(posilki[0]==null)
		return 1;
	for(int i=0 ; i<10 ; i++)
		if(freeIDPosilki[i]!=0)
		{
			int d = freeIDPosilki[i];
			freeIDPosilki[i]=0;
			return d;
		}
	int ID;
	boolean flaga=true;
	while(true)
	{
		IteratorPosilki.first();
		ID= posilki[CountPosilki-1].id + 1;
		while(IteratorPosilki.isDone())
			{
			 if(ID==IteratorPosilki.currentItem().id)
				 {
				 flaga=false;
				 break;
				 }
			  IteratorPosilki.next();
			}
		if(flaga)
			return ID;
	}	
}
static int getNextIDSkladnik(){
	
	return skladniki[CountSkladniki-1].id+1;
	/*for(int i=0 ; i<10 ; i++)
		if(freeIDPosilki[i]!=0)
		{
			int d = freeIDPosilki[i];
			freeIDPosilki[i]=0;
			return d;
		}
	int ID;
	boolean flaga=true;
	while(true)
	{
		IteratorPosilki.first();
		ID= posilki[CountPosilki-1].id + 1;
		while(IteratorPosilki.isDone())
			{
			 if(ID==IteratorPosilki.currentItem().id)
				 {
				 flaga=false;
				 break;
				 }
			  IteratorPosilki.next();
			}
		if(flaga)
			return ID;
	}*/	
}	
	
static class IteratorPosilkiClass{

	private int currentPosition;

		public void first() {
			currentPosition=0;
		}

		public void next() {
			if(currentPosition < CountPosilki)
				currentPosition++;
		}
	
		public boolean isDone() {
			if (currentPosition==CountPosilki)
			return false;
			return true;
		}

		
		public Posilek currentItem() {
			return posilki[currentPosition];
		}
		
		public Posilek getPosilek (int id){
			first();
			while (isDone())
			{	if (id==currentItem().id)
					return currentItem();
				else
					next();
			}
			return null;
		}
}

public static class IteratorSkladnikClass{
	
	private int currentPosition;
	
	public IteratorSkladnikClass(){
		first();
	}
	
	public void first() {
		currentPosition=0;
	}

	public void next() {
		if(currentPosition < CountSkladniki)
			currentPosition++;
	}

	public boolean isDone() {
		if (currentPosition==CountSkladniki)
		return false;
		return true;
	}

	public Skladnik currentItem() {
		return skladniki[currentPosition];
	}
	
	public Skladnik getSkladnik (int id){
		first();
		while (isDone())
		{	if (id==currentItem().id)
				return currentItem();
			else
				next();
		}
		return null;
	}
	
}
static class IteratorDniClass{
	
	private int currentPosition;
	
	public IteratorDniClass(){
		first();
	}
	
	public void first() {
		currentPosition=0;
	}

	public void next() {
		if(currentPosition < CountDni)
			currentPosition++;
	}

	public boolean isDone() {
		if (currentPosition==CountDni)
		return false;
		return true;
	}

	public Dzien currentItem() {
		return dni[currentPosition];
	}
	
	public Dzien getDzien (int id){
		first();
		while (isDone())
		{	if (id==currentItem().id)
				return currentItem();
			else
				next();
		}
		return null;
	}
	
}

}
