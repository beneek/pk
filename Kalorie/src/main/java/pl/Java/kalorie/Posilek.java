package pl.Java.kalorie;


public class Posilek {
	int id;
	int[] skladniki=new int[ILOSC_SKLADNIKOW];
	int[] waga=new int[ILOSC_SKLADNIKOW];
	int current;
	static final int ILOSC_SKLADNIKOW=8;
	
	public Macro macro; 
	
	public Posilek(int ID) {
		current=0;
		id=ID;
		for (int i=0 ; i<ILOSC_SKLADNIKOW ; i++)
		{waga[i]=0;
		skladniki[i]=0;
		}
		macro= new Macro();
   }    
  

public Posilek(int ID, int[] sk,int[] w)
{
		for (int i=0 ; i<ILOSC_SKLADNIKOW ; i++)
		{
			waga[i]=w[i];
			skladniki[i]=sk[i];
			if(waga[i]!=0) current=i+1;
		}
		id=ID;
		macro= new Macro();
		upDate();
		
}
	
	
	public String toString() {
        int i;
		String tempS;
        tempS="["+id+"] - "+current+" >>\n";
        for (i=0 ; i<current ; i++)
        tempS=tempS+" "+BazaObject.IteratorSkladniki.getSkladnik(skladniki[i])+" "+waga[i]+"\n";
        tempS+=macro;
        return tempS;
    }

public void upDate()
{
		macro.clean();
		for (int i=0 ; i<current ; i++)
		{
			macro.add(skladniki[i],waga[i]);
		}
}
	
public boolean add(int skladnik,int wa){
	if (current==ILOSC_SKLADNIKOW) return false;
	else 
	{
		waga[current]=wa;
		skladniki[current++]=skladnik;
		upDate();
		return true;
	}
}
	
	public boolean delete (int i_skl){
		if(current>0 & i_skl<current)
			{	current--;
				for (int i=i_skl; i<ILOSC_SKLADNIKOW-1 ; i++)
				{
						skladniki[i]=skladniki[i+1];
						waga[i]=waga[i+1];
				}
				waga[ILOSC_SKLADNIKOW-1]=0;
				skladniki[ILOSC_SKLADNIKOW-1]=0;
				upDate();
				return true;
			}
		return false;
	}
	

	
}
