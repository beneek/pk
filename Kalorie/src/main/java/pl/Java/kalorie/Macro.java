package pl.Java.kalorie;


public class Macro {

	public int B,T,W;
	public int Kcal;
	
	
	public Macro(){
	clean();
	}

	public void add(int id, int wag)
	{		
		Skladnik skl=BazaObject.IteratorSkladniki.getSkladnik(id);
		if(skl==null) 
			System.out.println("Macro.add: skladnik o podanym ID nie istnieje");
		else{															
		B+=skl.B*wag/100;
		T+=skl.T*wag/100;
		W+=skl.W*wag/100;
		Kcal=4*(B+W)+9*T;}
	}
	public void add(int id)
	{		
		Posilek pos=BazaObject.IteratorPosilki.getPosilek(id);
		if(pos==null) 
			System.out.println("Macro.add: pos o podanym ID nie istnieje");
		else{															
		B+=pos.macro.B;
		T+=pos.macro.T;
		W+=pos.macro.W;
		Kcal=4*(B+W)+9*T;}
	}
	
	public void update(){
		Kcal=4*(B+W)+9*T;
	}
	
	//public void add()
	
	public void clean()
	{
		B=0;
		T=0;
		W=0;
		Kcal=0;
	}
	
	public String toString (){
		return "W:"+W+" B:"+B+" T:"+T+" Ogolnie: "+Kcal+"kcal";
	}
}
