package pl.Java.kalorie;

public class SaveChanges {

	
	static int[] SkladnikiUpdate;
	static int[] SkladnikiInsert;
	static int[] SkladnikiDelete;
	
	static int[] PosilkiUpdate;
	static int[] PosilkiInsert;
	static int[] PosilkiDelete;
	
	static int[] DniUpdate;
	static int[] DniInsert;
	static int[] DniDelete;
	
	
	
SaveChanges()
{
		SkladnikiUpdate = new int [5];
		SkladnikiUpdate[0]=0;
		SkladnikiUpdate[1]=5;
		
		SkladnikiInsert = new int [5];
		SkladnikiInsert[0]=0;
		SkladnikiInsert[1]=5;
		
		SkladnikiDelete = new int [5];
		SkladnikiDelete[0]=0;         //////////////////trzeba pozmeniac tal jak w pierwsuzm
		SkladnikiDelete[1]=5;
		
		PosilkiUpdate = new int [5];
		PosilkiUpdate[0]=0;
		PosilkiUpdate[1]=5;
		
		PosilkiInsert = new int [5];
		PosilkiInsert[0]=0;
		PosilkiInsert[1]=5;
		
		PosilkiDelete = new int [5];
		PosilkiDelete[0]=0;
		PosilkiDelete[1]=5;
		
		DniUpdate = new int [5];
		DniUpdate[0]=0;
		DniUpdate[1]=5;
		
		DniInsert = new int [5];
		DniInsert[0]=0;
		DniInsert[1]=5;
		
		DniDelete = new int [5];
		DniDelete[0]=0;
		DniDelete[1]=5;
}
public static int test3 (){
	return PosilkiInsert[PosilkiInsert[0]+1];
}
	
public static void addInserPosilek(int ID)
{
	boolean flaga=true;
	for (int i=0 ; i<PosilkiInsert[0] ; i++)
	{
		if(ID==PosilkiInsert[i+2])
		{
			flaga=false;
			break;
		}
	}
	if(flaga)
	{
		//  Count          ArraySize
		if (PosilkiInsert[0]+2 >=PosilkiInsert[1]) 
		{
	        int[] tempArray = new int[PosilkiInsert[1]];
	        for (int i = 0; i < PosilkiInsert[1]; i++)
	        	tempArray[i] = PosilkiInsert[i];
	        tempArray[1]+= 3;  
	        PosilkiInsert = null;         
	        PosilkiInsert = new int[tempArray[1]];
	        for (int i = 0; i < (tempArray[1] - 3); i++) {
	        	PosilkiInsert[i] = tempArray[i];
	        }
	    }
		PosilkiInsert[2+PosilkiInsert[0]] = ID; 
	    System.out.println("PosilkiInsert    dodalem :"+PosilkiInsert[2+PosilkiInsert[0]]+"    count"+PosilkiInsert[0]+"  arraysize"+PosilkiInsert[1]);
		PosilkiInsert[0]++;
	}
}

public static void addDeleteDzien(int ID)
{
	boolean flaga=true;
	for (int i=0 ; i<DniDelete[0] ; i++)
	{
		if(ID==DniDelete[i+2])
		{
			flaga=false;
			break;
		}
	}
	for( int i = 0; i<DniInsert[0] ; i++)
	{
		if(ID==DniInsert[i+2])
		{
			flaga=false;
			break;
		}
	}
	if(flaga)
	{
		//  Count          ArraySize
		if (DniDelete[0]+2 >=DniDelete[1]) 
		{
	        int[] tempArray = new int[DniDelete[1]];
	        for (int i = 0; i < DniDelete[1]; i++)
	        	tempArray[i] = DniDelete[i];
	        tempArray[1]+= 3;  
	        DniDelete = null;         
	        DniDelete = new int[tempArray[1]];
	        for (int i = 0; i < (tempArray[1] - 3); i++) {
	        	DniDelete[i] = tempArray[i];
	        }
	    }
		DniDelete[2+DniDelete[0]] = ID; 
	    //System.out.println("dodalem :"+tablica[2+tablica[0]]+"    count"+tablica[0]+"  arraysize"+tablica[1]);
		DniDelete[0]++;
	}
}
public static void addInserDzien(int ID)
{
	boolean flaga=true;
	for (int i=0 ; i<DniInsert[0] ; i++)
	{
		if(ID==DniInsert[i+2])
		{
			flaga=false;
			break;
		}
	}
	if(flaga)
	{
		//  Count          ArraySize
		if (DniInsert[0]+2 >=DniInsert[1]) 
		{
	        int[] tempArray = new int[DniInsert[1]];
	        for (int i = 0; i < DniInsert[1]; i++)
	        	tempArray[i] = DniInsert[i];
	        tempArray[1]+= 3;  
	        DniInsert = null;         
	        DniInsert = new int[tempArray[1]];
	        for (int i = 0; i < (tempArray[1] - 3); i++) {
	        	DniInsert[i] = tempArray[i];
	        }
	    }
		DniInsert[2+DniInsert[0]] = ID; 
	    System.out.println("dodalem :"+DniInsert[2+DniInsert[0]]);
		DniInsert[0]++;
	}
}
public void addUpdatePosilek(int ID)
{
	boolean flaga=true;
	for (int i=0 ; i<PosilkiUpdate[0] ; i++)
	{
		if(ID==PosilkiUpdate[i+2])
		{
			flaga=false;
			break;
		}
	}
	if(flaga)
	{
		//  Count          ArraySize
		if (PosilkiUpdate[0]+2 >=PosilkiUpdate[1]) 
		{
	        int[] tempArray = new int[PosilkiUpdate[1]];
	        for (int i = 0; i < PosilkiUpdate[1]; i++)
	        	tempArray[i] = PosilkiUpdate[i];
	        tempArray[1]+= 3;  
	        PosilkiUpdate = null;         
	        PosilkiUpdate = new int[tempArray[1]];
	        for (int i = 0; i < (tempArray[1] - 3); i++) {
	        	PosilkiUpdate[i] = tempArray[i];
	        }
	    }
		PosilkiUpdate[2+PosilkiUpdate[0]] = ID; 
	    //System.out.println("dodalem :"+tablica[2+tablica[0]]+"    count"+tablica[0]+"  arraysize"+tablica[1]);
		PosilkiUpdate[0]++;
	}
}
public static void addUpdateDzien(int ID)
{
	boolean flaga=true;
	for (int i=0 ; i<DniUpdate[0] ; i++)
	{
		if(ID==DniUpdate[i+2])
		{
			flaga=false;
			break;
		}
	}
	if(flaga)
	{
		//  Count          ArraySize
		if (DniUpdate[0]+2 >=DniUpdate[1]) 
		{
	        int[] tempArray = new int[DniUpdate[1]];
	        for (int i = 0; i < DniUpdate[1]; i++)
	        	tempArray[i] = DniUpdate[i];
	        tempArray[1]+= 3;  
	        DniUpdate = null;         
	        DniUpdate = new int[tempArray[1]];
	        for (int i = 0; i < (tempArray[1] - 3); i++) {
	        	DniUpdate[i] = tempArray[i];
	        }
	    }
		DniUpdate[2+DniUpdate[0]] = ID; 
	    //System.out.println("dodalem :"+tablica[2+tablica[0]]+"    count"+tablica[0]+"  arraysize"+tablica[1]);
		DniUpdate[0]++;
	}
}


public static void addUpdateSkladniki(int ID) {
	boolean flaga=true;
	for (int i=0 ; i<SkladnikiUpdate[0] ; i++)
	{
		if(ID==SkladnikiUpdate[i+2])
		{
			flaga=false;
			break;
		}
	}
	if(flaga)
	{
		//  Count          ArraySize
		if (SkladnikiUpdate[0]+2 >=SkladnikiUpdate[1]) 
		{
	        int[] tempArray = new int[SkladnikiUpdate[1]];
	        for (int i = 0; i < SkladnikiUpdate[1]; i++)
	        	tempArray[i] = DniUpdate[i];
	        tempArray[1]+= 3;  
	        SkladnikiUpdate = null;         
	        SkladnikiUpdate = new int[tempArray[1]];
	        for (int i = 0; i < (tempArray[1] - 3); i++) {
	        	SkladnikiUpdate[i] = tempArray[i];
	        }
	    }
		SkladnikiUpdate[2+SkladnikiUpdate[0]] = ID; 
		SkladnikiUpdate[0]++;
	}
}
	
}
