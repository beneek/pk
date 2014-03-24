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
        String plaintext = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("dane.txt"));
        }
        catch (FileNotFoundException fileNotFound) {
            System.err.println("Pliku nie znaleziono!");
            return;
        }
       
        // czytanie i zamykanie pliku
        try {
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();
 
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            plaintext = sb.toString();
        } finally {           
                bufferedReader.close();    
        }   
        RSA.getInstance();
        RSA.generateKey();
        
        // wypisz na ekran klucze oraz tekst
        System.out.println(RSA.getPublickey());
        System.out.println(RSA.getPrivatekey());
        System.out.println(plaintext);
       
        // szyfrowanie
        String ciphertext = RSA.encrypt(plaintext);
        System.out.println(ciphertext);
       
        // deszyfruj
        String plaintext1 = RSA.decrypt(ciphertext);
        System.out.println(plaintext1);
       
    }
}