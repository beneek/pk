package RSA;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
 
public class Projekt2 {
 
    public static void main(String[] args) throws IOException {
       
        // Tworzenie scannera i zczytywanie peselu z klawiatury
        Scanner scanner = new Scanner(System.in);
        BigInteger pesel = scanner.nextBigInteger();
        System.out.println(pesel);
       
        // otwarcie pliku
        BufferedReader bufferedReader = null;
        String plainText = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("tekst_do_szyfrowania.txt"));
        }
        catch (FileNotFoundException fileNotFound) {
            //Jezeli pliku nie znaleziono, koniec programu
            System.err.println("Pliku nie znaleziono!");
            return;
        }
       
        // czytanie i zamykanie pliku
        try {
            //Uzywanie StringBuildera poniewaz jest bardziej optymalny w uzyciu w porownaiu z dzialaniem na Stringach
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
 
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            plainText = stringBuilder.toString();
        } finally {           
                bufferedReader.close();    
        }   
        RSA.getInstance();
        RSA.generateKey();
        
        // wypisz na ekran klucze oraz tekst
        System.out.println(RSA.getPublickey());
        System.out.println(RSA.getPrivatekey());
        System.out.println(plainText);
       
        // szyfrowanie
        String cipherText = RSA.encrypt(plainText);
        System.out.println(cipherText);
       
        // deszyfrowanie
        String plaintext1 = RSA.decrypt(cipherText);
        System.out.println(plaintext1);
       
    }
}