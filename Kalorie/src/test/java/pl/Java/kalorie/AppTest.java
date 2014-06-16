package pl.Java.kalorie;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    public void test1() {
		Baza baza = new Baza();
		BazaObject bazaO = new BazaObject();
		assertEquals(1,Baza.ID_potrawa);
		assertEquals(1,Baza.ID_dzien);
		assertEquals(3, BazaObject.arraySizeSkladniki);
		assertEquals(0,BazaObject.CountSkladniki);
	}
	public void test2() {
		BazaObject bazaObj = new BazaObject();
		Skladnik skladnik = new Skladnik(1,"Skladnik",25,25,25);
		BazaObject.addSkladnik(skladnik.id, skladnik.name, skladnik.W, skladnik.B, skladnik.T);
		BazaObject.IteratorSkladniki.first();
		assertEquals(skladnik.id,BazaObject.IteratorSkladniki.currentItem().id);
		assertEquals(skladnik.name,BazaObject.IteratorSkladniki.currentItem().name);
		assertEquals(skladnik.W, BazaObject.IteratorSkladniki.currentItem().W);

	}   
    public void test3() {
    	BazaObject bazaObj = new BazaObject();
		Skladnik skladnik = new Skladnik(1,"Skladnik",25,25,25);
		BazaObject.addSkladnik(skladnik.id, skladnik.name, skladnik.W, skladnik.B, skladnik.T);
		Macro macro = new Macro();
		macro.add(1,100);
    	assertEquals(425,macro.Kcal);
    }
    
    public void test4() {
    	BazaObject bazaObj = new BazaObject();
		Skladnik skladnik = new Skladnik(1,"Skladnik",25,25,25);
		BazaObject.addSkladnik(skladnik.id, skladnik.name, skladnik.W, skladnik.B, skladnik.T);
		int[] tab1={1,0,0,0,0,0,0,0};
    	int[] tab2={100,0,0,0,0,0,0,0};
    	Posilek pos = new Posilek(10,tab1,tab2);
    	assertEquals(10,pos.id);
    	
    }
    	
	public void test5() {
		SaveChanges save = new SaveChanges();
		save.addInserPosilek(10);
		assertEquals(10,save.test3());
	}
	
	
}
