
package Glowny;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class Tablica {
    
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
  //      PrintWriter writer3 = new PrintWriter("slowa2.txt");
        BufferedReader br = new BufferedReader(new FileReader("slowa2.txt"));
        for(int j = 0; j< 100; j++)
        {
            n = random.nextInt(6) + 3;
            for (int i = 0; i < n; i++) {
                c = literki[random.nextInt(literki.length)];
                sb.append(c);
            }
            output = br.readLine();
            kodujSlowo(output, writer);
    /*        writer3.println(sb.toString());
            kodujSlowo(sb.toString(), writer);
            generujIndeks(sb.toString(), writer2);
            sb.setLength(0);
        */}
        writer.close();
        writer2.close();
   //     writer3.close();
    }
        
    public void generujIndeks(String slowo, PrintWriter writer)
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
   /*     kod = String.format("%11s", Integer.toBinaryString(indeks)).replace(' ', '0');   
        for(int i = 0; i< 11 - kod.length(); i++)
        {
            kod = "0"+kod;
        }*/
        writer.println(indeks);
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
    
    public String modyfikujSlowo(String slowo)
    {
        Random generator = new Random();
        int pozycjaZmiany = generator.nextInt(slowo.length());
        int kodLitery = generator.nextInt(26);
        char litera = (char) (kodLitery + 'a');
        String noweSlowo = slowo.substring(0,pozycjaZmiany)+litera+slowo.substring(pozycjaZmiany+1);
        return noweSlowo;
    }
}
