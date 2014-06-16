package pl.Java.kalorie;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Baza {

	public static final String DRIVER = "org.sqlite.JDBC";
    public static final String B_URL = "jdbc:sqlite:BAZA.db";
    
    public static int ID_potrawa;
    public static int ID_dzien;
    private Connection conn;
    private Statement stat;
	
	public Baza(){
		InitDataBaseConnection();
		ID_potrawa=1;
		ID_dzien=1;
		createTable();
		sampleInserts();
	}
	
	public void InitDataBaseConnection(){
		try{
			Class.forName(Baza.DRIVER);
			}
		catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(B_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
	}
	
	private void createTable(){
		
		String skladniki="CREATE TABLE IF NOT EXISTS skladniki (id_skladnik INTEGER PRIMARY KEY AUTOINCREMENT,nazwa varchar(150), W int, B int, T int );";
		String potrawy="CREATE TABLE IF NOT EXISTS potrawy (id_potrawy INTEGER PRIMARY KEY , skladnik1 int REFERENCES skladnik(id_skladnik),waga1 int, skladnik2 int REFERENCES skladnik(id_skladnik),waga2 int, skladnik3 int REFERENCES skladnik(id_skladnik),waga3 int, skladnik4 int REFERENCES skladnik(id_skladnik),waga4 int, skladnik5 int REFERENCES skladnik(id_skladnik),waga5 int, skladnik6 int REFERENCES skladnik(id_skladnik),waga6 int, skladnik7 int REFERENCES skladnik(id_skladnik),waga7 int,skladnik8 int REFERENCES skladnik(id_skladnik),waga8 int);";
		String dzien="CREATE TABLE IF NOT EXISTS dzien (id_dzien INTEGER PRIMARY KEY, data DATE, potrawa1 int REFERENCES potrawy(id_potrawy), potrawa2 int REFERENCES potrawy(id_potrawy), potrawa3 int REFERENCES potrawy(id_potrawy), potrawa4 int REFERENCES potrawy(id_potrawy), potrawa5 int REFERENCES potrawy(id_potrawy));";
		String macro="CREATE TABLE IF NOT EXISTS macro (id_macro INTEGER PRIMARY KEY, W int, B int , T int);";
		try{
			stat.execute(skladniki);
			stat.execute(potrawy);
			stat.execute(dzien);
			stat.execute(macro);
			}
		catch(SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
			}
	}

public boolean updateMacro (int w, int b, int t){
		
		try{
			PreparedStatement newState = conn.prepareStatement("UPDATE macro SET W=?, B=?, T=? WHERE id_macro=1;");
			newState.setInt(1,w);
			newState.setInt(2,b);
			newState.setInt(3,t);
			newState.execute();
		}
		catch(SQLException e){
			System.err.println("updateMacro error");
			return false;
		}
	return true;
}
	
public boolean insertMacro (int w, int b, int t){
		
		try{
			PreparedStatement newState = conn.prepareStatement("INSERT INTO macro values(NULL,?,?,?);");
			newState.setInt(1,w);
			newState.setInt(2,b);
			newState.setInt(3,t);
			newState.execute();
		}
		catch(SQLException e){
			System.err.println("insertMacro error");
			return false;
		}
	return true;
}

public boolean insertSkladnik (Skladnik skl){
	
	try{
		PreparedStatement newState = conn.prepareStatement("INSERT INTO skladniki values(NULL,?,?,?,?);");
		//newState.setInt(1,ID_skladnik);
		newState.setString(1,skl.name);
		newState.setInt(2,skl.W);
		newState.setInt(3,skl.B);
		newState.setInt(4,skl.T);
		newState.execute();
	}
	catch(SQLException e){
		System.err.println("insertSkladnik error");
		return false;
	}

return true;
}

	public boolean insertSkladnik (int w, int t, int b, String n){
		
		try{
			PreparedStatement newState = conn.prepareStatement("INSERT INTO skladniki values(NULL,?,?,?,?);");
			//newState.setInt(1,ID_skladnik);
			newState.setString(1,n);
			newState.setInt(2,w);
			newState.setInt(3,b);
			newState.setInt(4,t);
			newState.execute();
		}
		catch(SQLException e){
			System.err.println("insertSkladnik error");
			return false;
		}
	//ID_skladnik++;
	return true;
	}
		

	public boolean insertPotrawa (int[] skl, int[] wag){
	
		try{																		//    id,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8
			PreparedStatement newState = conn.prepareStatement("INSERT INTO potrawy values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			newState.setInt(1,ID_potrawa);											//     1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7
			for (int i=2 ; i<18 ; i+=2){
			newState.setInt(i,skl[i/2-1]);
			newState.setInt(i+1,wag[i/2-1]);
			}
			newState.execute();
		}
		catch(SQLException e){
			System.err.println("insertPotrawa error");
			return false;
		}
	ID_potrawa++;
	return true;
	}
	
	public boolean insertDzien (Date w,int[] pot){
		try{																		//  id,d,1,2,3,4,5
			PreparedStatement newState = conn.prepareStatement("INSERT INTO dzien values(?,?,?,?,?,?,?);");
			newState.setInt(1,ID_dzien);
			newState.setDate(2, w);
			for (int i=0 ; i<5 ; i++)
			newState.setInt(i+3,pot[i]);
			newState.execute();
		}
		catch(SQLException e){
			System.err.println("insertDzien error");
			return false;
		}
	ID_dzien++;
	return true;
	}

public boolean insertSkladnik (){
		Skladnik skl;
		try{
			PreparedStatement newState = conn.prepareStatement("INSERT INTO skladniki values(?,?,?,?,?);");
			conn.setAutoCommit(false);
			for(int i=0 ; i<SaveChanges.SkladnikiInsert[0] ; i++)
			{
				skl=BazaObject.IteratorSkladniki.getSkladnik(SaveChanges.SkladnikiInsert[i+2]);
				newState.setInt(1,skl.id);
				newState.setString(2,skl.name);
				newState.setInt(3,skl.W);
				newState.setInt(4,skl.B);
				newState.setInt(5,skl.T);
				newState.addBatch();
			}
			newState.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(SQLException e){
			System.err.println("insertSkladnik error");
			return false;
		}
	return true;
}
		

public boolean insertPosilek ()
{
		Posilek posilek;
		try{																		//    id,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8
				PreparedStatement newState = conn.prepareStatement("INSERT INTO potrawy values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				conn.setAutoCommit(false);
				for (int j=0 ; j<SaveChanges.PosilkiInsert[0] ; j++)
				{	posilek=BazaObject.IteratorPosilki.getPosilek(SaveChanges.PosilkiInsert[j+2]);
					newState.setInt(1,posilek.id);		// trzeba wymyslicz jak ustalic wolne ID									//     1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7
					for (int i=2 ; i<18 ; i+=2)
					{	
						newState.setInt(i,posilek.skladniki[i/2-1]);
						newState.setInt(i+1,posilek.waga[i/2-1]);
					}
					System.out.println("Dodaje posilek do bazy id:"+SaveChanges.PosilkiInsert[j+2]);
					newState.addBatch();
				}
				conn.commit();
				newState.executeBatch();
			}
			catch(SQLException e){
				System.err.println("insertPotrawa error");
				return false;
			}
		return true;
		}
	
public boolean insertDzien (){
	Dzien dzien=null;
	try{																		//  id,d,1,2,3,4,5
		conn.setAutoCommit(false);
		PreparedStatement newState = conn.prepareStatement("INSERT INTO dzien values(?,?,?,?,?,?,?);");
		for(int i=0 ; i<SaveChanges.DniInsert[0] ; i++)
		{
			dzien=BazaObject.IteratorDni.getDzien(SaveChanges.DniInsert[i+2]);
			if(dzien!=null)
			{newState.setInt(1,dzien.id);
			newState.setDate(2,dzien.data);
			for (int j=0 ; j<5 ; j++)
				newState.setInt(i+3,dzien.posilki[j]);
			newState.addBatch();
			System.out.println("Robie InsertDzien ID:"+dzien.id);
			}
		}
		newState.executeBatch();
		conn.commit();
		conn.setAutoCommit(true);
	}
	catch(SQLException e){
		System.err.println("insertDzien error");
		return false;
	}
return true;
}

public void upDateSkladnik(Skladnik skl) throws SQLException
	{	
		
		PreparedStatement newState=null;
			newState= conn.prepareStatement("UPDATE skladniki SET nazwa=? , W=? , B=? , T=? WHERE id_skladnik=?;");
			   
				newState.setString(1, skl.name);
				newState.setInt(2,skl.W);
				newState.setInt(3,skl.B);
				newState.setInt(4,skl.T);
				newState.setInt(5,skl.id);
				newState.executeUpdate();
			
			newState.execute();	
	}
	
public void upDatePosilek() throws SQLException
{
		Posilek posi;
		PreparedStatement newState=null;
			conn.setAutoCommit(false);
			newState= conn.prepareStatement("UPDATE potrawy SET skladnik1=? ,waga1=?,skladnik2=? ,waga2=?,skladnik3=? ,waga3=?,skladnik4=? ,waga4=?,skladnik5=? ,waga5=?,skladnik6=? ,waga6=?,skladnik7=? ,waga7=?,skladnik8=? ,waga8=? WHERE id_potrawy=?;");
			for(int i=0 ; i<SaveChanges.PosilkiUpdate[0] ; i++ )
			{   
				System.out.println("RobieUpdate() posilkiUpdate[0]"+SaveChanges.PosilkiUpdate[0]);
				posi= BazaObject.IteratorPosilki.getPosilek(SaveChanges.PosilkiUpdate[i+2]);
				for (int j=0 ; j<16; j+=2)
				{
					System.out.println(posi.skladniki[j/2]+"  "+posi.waga[j/2]);
					newState.setInt(1+j, posi.skladniki[j/2]);
					newState.setInt(2+j, posi.waga[j/2]);
				}
				newState.setInt(17,posi.id);
				newState.addBatch();
			}
			newState.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
}

public void upDateDzien() throws SQLException
{
		Dzien dzien;
		PreparedStatement newState=null;
			conn.setAutoCommit(false);
			newState= conn.prepareStatement("UPDATE dzien SET potrawa1=? ,potrawa2=? ,potrawa3=? ,potrawa4=? ,potrawa5=?, data=? WHERE id_dzien=?;");
			for(int i=0 ; i<SaveChanges.DniUpdate[0] ; i++ )
			{   
				dzien= BazaObject.IteratorDni.getDzien(SaveChanges.DniUpdate[i+2]);
				for (int j=0 ; j<5; j++)
					newState.setInt(1+j, dzien.posilki[j]);
				newState.setDate(6, dzien.data);
				newState.setInt(7, dzien.id);
				newState.addBatch();
				System.out.println("Uaktualniam liste posilkow w dniu o id:"+SaveChanges.DniUpdate[i+2]);
			}
			newState.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
}
public void DeleteDzien() throws SQLException
{
		PreparedStatement newState=null;
			conn.setAutoCommit(false);
			newState= conn.prepareStatement("DELETE FROM dzien WHERE id_dzien=?;");
			for(int i=0 ; i<SaveChanges.DniDelete[0] ; i++ )
			{   
				newState.setInt(1,SaveChanges.DniDelete[i+2]);
				newState.addBatch();
			}
			newState.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
}
	
	
public void InitSkladniki (){
		int w,b,t,id;
		String s;
		ResultSet result;
		try {
			BazaObject.skladniki=null;
			BazaObject.skladniki= new Skladnik[3];
			BazaObject.arraySizeSkladniki=3;
			BazaObject.CountSkladniki=0;	result = stat.executeQuery("SELECT * FROM skladniki ORDER BY nazwa");
			while(result.next()){
			id=result.getInt("id_skladnik");
			w=result.getInt("W");
			b=result.getInt("B");
			t=result.getInt("T");
			s=result.getString("nazwa");
			BazaObject.addSkladnik(id,s,w,b,t);
			}
		} catch (SQLException e) {
			System.err.println("Blad przy inicjalizowaniu tablicy skladnikow!");
			e.printStackTrace();
		}
	}
	
public void InitPotrawa (){
		int[] skl = new int[8];
		int[] wag = new int[8];
		
		ResultSet result;
		try {
			result = stat.executeQuery("SELECT * FROM potrawy");
			
			while(result.next()){
				for (int i=0 ; i<8 ; i++){
					skl[i]=result.getInt("skladnik"+(i+1));
					wag[i]=result.getInt("waga"+(i+1));
				}
				BazaObject.addPosilek( new Posilek( result.getInt("id_potrawy"), skl, wag) );
			}
		} catch (SQLException e) {
			System.err.println("Blad przy inicjalizowaniu tablicy potraw!");
			e.printStackTrace();
		}
	}
	
	public void InitDzien (){
		int[] pot = new int[5];
		ResultSet result;
		try {
			result = stat.executeQuery("SELECT * FROM dzien");
			while(result.next()){
				for (int i=0 ; i<5 ; i++)
					pot[i]=result.getInt("potrawa"+(i+1));
				BazaObject.addDzien(new Dzien( result.getInt("id_dzien"), result.getDate("data"), pot));
			}
		} catch (SQLException e) {
			System.err.println("Blad przy inicjalizowaniu tablicy dni!");
			e.printStackTrace();
		}
	}
	public void InitMacro(){
		ResultSet result;
		try {
			result = stat.executeQuery("SELECT * FROM macro");
			BazaObject.UserDemandMacro.W=result.getInt("W");
			BazaObject.UserDemandMacro.B=result.getInt("B");
			BazaObject.UserDemandMacro.T=result.getInt("T");
			BazaObject.UserDemandMacro.update();

		} catch (SQLException e) {
			System.err.println("Blad przy inicjalizowaniu UserDemandMacro!");
			e.printStackTrace();
		}
	}

	
	public void CloseConnection(){
		try{
		conn.close();
		}
		catch(SQLException e)
		{	
			e.printStackTrace();
		}
	}
	public boolean BazaPusta() throws SQLException{
		ResultSet result;
		result=stat.executeQuery("SELECT * FROM skladniki");
		if(!result.next())
			return true;
		return false;
	}
	
	public void sampleInserts(){
		
		try {
			if(BazaPusta())
			{
				insertMacro(290,150,60);
				this.insertSkladnik(70, 4, 6, "Ry�");//1
				this.insertSkladnik(2, 5, 23, "Piers z kurczaka");//2
				this.insertSkladnik(70, 3, 12, "Makaron");//3
				this.insertSkladnik(1, 9, 22, "Wolowina");//4
				this.insertSkladnik(1, 92, 1, "Oliwa");//5
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}	
