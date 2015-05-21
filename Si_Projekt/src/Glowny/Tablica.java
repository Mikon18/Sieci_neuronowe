
package Glowny;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;


public class Tablica {
    
    public void generujSlowa() throws FileNotFoundException
    {
        char c;
        int n;
        String output;
        char[] literki = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        PrintWriter writer = new PrintWriter("slowa.txt");
        PrintWriter writer2 = new PrintWriter("indeksy.txt");
        for(int j = 0; j< 100; j++)
        {
            n = random.nextInt(6) + 3;
            for (int i = 0; i < n; i++) {
                c = literki[random.nextInt(literki.length)];
                sb.append(c);
            }
            kodujSlowo(sb.toString(), writer);
            generujIndeks(sb.toString(), writer2);
            sb.setLength(0);
        }
        writer.close();
        writer2.close();
    }
        
    public void generujIndeks(String slowo, PrintWriter writer)
    {
        int indeks = 0, a;
        String pozycja, literka;
        for(int i = 0; i < slowo.length(); i++)
        {
            pozycja = String.format("%3s", Integer.toBinaryString(((i >> 1) ^i))).replace(' ', '0');   
            a = slowo.charAt(i) - 'a';
            literka = String.format("%5s", Integer.toBinaryString(((a >> 1) ^a))).replace(' ', '0');  
            String wynik = pozycja + literka;
            indeks += Integer.parseInt(wynik,2);
            
        }
        writer.println(indeks);
    }
    public void kodujSlowo(String slowo, PrintWriter writer)
    {
        for(int i = 0; i < 8; i++)
        {
            if(slowo.length() > i)
            {
                writer.print((int)(slowo.charAt(i))+" ");
            }
            else
            {
                writer.print(0+" ");
            }
        }
        writer.println();      
    }
}
