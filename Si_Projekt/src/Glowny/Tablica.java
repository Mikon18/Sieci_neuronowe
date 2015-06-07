package Glowny;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class Tablica {
    
    final private int LICZBA_SLOW = 500;
    
    public void generujSlowa() throws FileNotFoundException, IOException
    {
        char c;
        int n;
        String output;
        char[] literki = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        PrintWriter writer = new PrintWriter("slowa.txt");
        PrintWriter writer2 = new PrintWriter("indeksy.txt");
        PrintWriter writer3 = new PrintWriter("slowa_do_modyfikacji.txt");
        PrintWriter writer4 = new PrintWriter("indeksy_bin.txt");
        for(int j = 0; j < LICZBA_SLOW; j++)
        {
            n = random.nextInt(6) + 3;
            for (int i = 0; i < n; i++) {
                c = literki[random.nextInt(literki.length)];
                sb.append(c);
            }            
            writer3.println(sb.toString());
            kodujSlowo(sb.toString(), writer);
            generujIndeks(sb.toString(), writer2, writer4);
            sb.setLength(0);
        }
        writer3.close();
        modyfikujSlowa();
        writer.close();
        writer2.close();
        writer4.close();
    }
        
    public void generujIndeks(String slowo, PrintWriter writer, PrintWriter writer2)
    {
        int indeks = 0, a;
        String pozycja, literka, kod;
        for(int i = 0; i < slowo.length(); i++)
        {
            pozycja = String.format("%3s", Integer.toBinaryString(((i >> 1) ^i))).replace(' ', '0');   
            a = slowo.charAt(i) - 'a';
            literka = String.format("%5s", Integer.toBinaryString(((a >> 1) ^a))).replace(' ', '0');  
            String wynik = pozycja + literka;
            indeks += Integer.parseInt(wynik,2);            
        };
        kod = String.format("%11s", Integer.toBinaryString(indeks)).replace(' ', '0');   
        for(int i = 0; i< 11 - kod.length(); i++)
        {
            kod = "0"+kod;
        }
        writer.println(indeks);
        writer2.println(kod);
    }
    public void kodujSlowo(String slowo, PrintWriter writer)
    {
        for(int i = 0; i < 8; i++)
        {
            if(slowo.length() > i)
            {
                writer.print(String.format("%8s", Integer.toBinaryString((int)(slowo.charAt(i)))).replace(' ', '0'));
            }
            else
            {
                writer.print("00000000");
            }
        }
        writer.println();      
    }
    public void modyfikujSlowa() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("slowa_do_modyfikacji.txt"));
        PrintWriter writer = new PrintWriter("slowa_modyfikowane.txt");
        PrintWriter writer2 = new PrintWriter("slowa_zmodyfikowane.txt");
        Random generator = new Random();
        String slowo;
        String noweSlowo;
        int pozycjaZmiany;
        int kodLitery;
        char litera;
        for(int i = 0; i < LICZBA_SLOW; i++)
        {
            slowo = br.readLine();
            pozycjaZmiany = generator.nextInt(slowo.length());
            kodLitery = generator.nextInt(26);
            litera = (char) (kodLitery + 'a');
            noweSlowo = slowo.substring(0,pozycjaZmiany)+litera+slowo.substring(pozycjaZmiany+1);
            writer.println(noweSlowo);
            kodujSlowo(noweSlowo,writer2);           
        }
        writer.close();
        writer2.close();
    }
}
