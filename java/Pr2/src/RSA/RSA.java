package RSA;

import java.math.BigInteger;
import java.util.Random;
 
 
public final class RSA {
    private static volatile RSA instance = null;
 
    private static BigInteger privatekey;
    private static BigInteger publickey;
   
    private static BigInteger n = new BigInteger("0");
    private static BigInteger e = new BigInteger("0");
    private static BigInteger d = new BigInteger("0");
   
    public static void generateKey() {
        BigInteger p = new BigInteger("33478071698956898786044169848212690817704794983713768568912431388982883793878002287614711652531743087737814467999489");
        BigInteger q = new BigInteger("36746043666799590428244633799627952632279158164343087642676032283815739666511279233373417143396810270092798736308917");
       
        n = p.multiply(q);
       
        BigInteger euler = new BigInteger("1");
        p.subtract(BigInteger.ONE);
        q.subtract(BigInteger.ONE);
        euler = euler.multiply(p).multiply(q);
       
        p.add(BigInteger.ONE);
        q.add(BigInteger.ONE);
       
        Random random = new Random();
       
        e = BigInteger.probablePrime(euler.bitCount(), random);
       
        while((e.compareTo(BigInteger.ONE) < 1) || (euler.compareTo(e) < 1)) {
            e.nextProbablePrime();
        }
       
        d = e.modInverse(euler);
       
        publickey = n.shiftLeft(e.bitCount());
        publickey = n.or(e);
       
        privatekey = n.shiftLeft(d.bitCount());
        privatekey = n.or(d);
    }
   
    public static String encrypt(String plaintext) {
        String encryptedtext = null;
 
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int currentchar;
       
        while(i < plaintext.length()) {
            currentchar = plaintext.charAt(i) - 'A';
           
            if(plaintext.charAt(i) == ' ') {
                currentchar = 26;
            }
            if(plaintext.charAt(i) == '\n') {
                currentchar = 27;
            }
            if(plaintext.charAt(i) == '\r') {
                currentchar = 28;
            }
           
            stringBuilder.append(currentchar);
           
            i++;
        }
       
        encryptedtext = stringBuilder.toString();
       
        BigInteger ct = new BigInteger(encryptedtext);
       
        ct.modPow(e, n);
       
        return ct.toString();
    }
   
    public static String decrypt(String ciphertext) {
        String plaintext = null;
       
        BigInteger cipherText = new BigInteger(ciphertext);
       
        cipherText.modPow(d, n);
       
        ciphertext = cipherText.toString();
       
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int currentchar;
       
        while(i < ciphertext.length()) {
            currentchar = (Integer.parseInt(ciphertext.substring(i, i+2)));
           
            if(currentchar == 26) {
                stringBuilder.append(" ");
            }
            else if(currentchar == 27) {
                stringBuilder.append("\n");
            }
            else if(currentchar == 28) {
                stringBuilder.append("\r");
            }
            else {
                stringBuilder.append((char)(currentchar + 'A'));
            }
            i+=2;
        }
       
        plaintext = stringBuilder.toString();
       
        return plaintext;
       
    }
    public static BigInteger getPrivatekey() {
        return privatekey;
    }
 
 
    public static BigInteger getPublickey() {
        return publickey;
    }
 
   
    public static RSA getInstance() {
        if (instance == null) {
            synchronized (RSA.class) {
                if (instance == null) {
                    instance = new RSA();
                }
            }
        }
       
        return instance;
    }
   
    private RSA() {}
}