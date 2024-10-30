package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connessione extends Thread
{
    Socket s;
    String q = "QUIT";
    String b = "BUY";
    String n = "N";

    Connessione(Socket s)
    {
        super();
        this.s = s;
        this.q = "QUIT";
        this.b = "BUY";
        this.n = "N";
    }

    public void run()
    {
        try
        {
            System.out.println("Un client si e' collegato");
            BufferedReader in = new BufferedReader(new InputStreamReader((s.getInputStream())));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            boolean connessione = true;
            while(connessione)
            {         
                String stringaOttenuta = in.readLine();
                try
                {
                    if(stringaOttenuta != q)
                    {
                        if(stringaOttenuta == n)
                        {
                            out.writeBytes("N");
                        }
                        else if(stringaOttenuta == b)
                        {
                            out.writeBytes("OK");
                        }
                    }
                    else
                    {
                        System.out.println("Connessione chiusa");
                        connessione = false;
                    }

                } catch(Exception e)
                {

                }

            }

        s.close();

        } catch(Exception e)
        {

        }
        
    }



}
