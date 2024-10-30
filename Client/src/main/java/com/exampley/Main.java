package com.exampley;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Main 
{
    public static void main(String[] args) throws UnknownHostException, IOException  
    {
        System.out.println("client partito");
        Socket s = new Socket("localhost",3000);
        BufferedReader in = new BufferedReader(new InputStreamReader((s.getInputStream())));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        boolean connessione = true;
        int biglietti = 100;
        while(connessione)
        {          
            Scanner input = new Scanner(System.in);
            String nome;
            System.out.println("Connessione effettuata, digita il tuo username");
            nome = input.nextLine();
            out.writeBytes("" + nome + '\n');
            do
            {
                System.out.println("MENU: 1.Richiedere disponibilita', 2.Acquistare biglietti, 3.Uscire");
                nome = input.next();

                String ris = in.readLine();
                switch(ris)
                {
                    case "1":
                    {
                        if(biglietti > 0)
                        {
                            System.out.println("Sono disponibili: " + biglietti + "biglietti");
                        }
                        break;
                    }

                    case "2":
                    {
                        if(biglietti > 0)
                        {
                            int big;
                            System.out.println("Quanti biglietti vuoi acquistare?");
                            big = input.nextInt();
                            out.writeBytes("" + big + '\n');
                            if(biglietti > big)
                            {
                                biglietti = biglietti - big;
                                System.out.println("Acquisto avvenuto con successo");
                            }
                            else
                            {
                                big = 0;
                                System.out.println("Impossibile completare acquisto");
                            }
                        }
                        break;
                    }

                    case "3":
                    {
                        System.out.println("Ok, alla prossima.");
                        System.out.println("Connessione chiusa");
                        connessione = false;
                        break;
                    }
                }

            } while(!nome.equals("1") &&!nome.equals("2")&&!nome.equals("3"));
          
        }

        s.close();
    }
}