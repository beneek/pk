package pl.Java.kalorie;

import java.sql.Date;

public class Dzien {

	int id;
	public Date data;
	public int[] posilki=new int[5];
	public int current;
	public Macro dayMacro;
	
	public Dzien(int ID , Date d , int[] pot){
		id=ID;
		data=d;
		dayMacro= new Macro();
		for(int i=0 ; i<5 ; i++)
		{
		posilki[i]=pot[i];
		if(posilki[i]!=0)
			{
			dayMacro.add(pot[i]);
			current=i+1;
			}
		}
	}
	public Dzien (int Id,Date d){
		id=Id;
		data=d;
		current=0;
		for(int i=0 ; i<5 ; i++)
			posilki[i]=0;
		dayMacro= new Macro();
	}	
	
	public void updateMacro(){
		dayMacro.clean();
		for (int i=0 ; i<current ; i++)
		{
			dayMacro.add(posilki[i]);
		}
	}
	
	public boolean add(int ID){
		if (current<5)
		{
			posilki[current++]=ID;
			return true;
		}
	return false;
	}
	
	public String toString() {
        String str;
        str="["+id+"] - "+data;
       // for(int i=0 ; i<current ; i++)
       // 	str+=" "+posilki[i];
		return str;
    }
	
}
