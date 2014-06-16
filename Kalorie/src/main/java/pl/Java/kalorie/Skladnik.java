package pl.Java.kalorie;

public class Skladnik {

		public int id;
		public int W;
		public int B;
		public int T;
		public String name;
		public static int nextID;
		
		
		public Skladnik(){};
		public Skladnik( int id ,String n, int w, int b ,int t ){
			this.id=id;
			W=w;
			B=b;
			T=t;
			name=n;
		}
		
		
		public String toString() {
	        return name;
	    }
		
		/*public String toString() {
	        return "["+id+"] - "+name+" "+W+" "+B+" "+T+" ";
	    }*/

}


