package com.example;

import java.io.IOException;
import java.net.ServerSocket;

public class Main 
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("Server avviato...");
        ServerSocket server = new ServerSocket(3000);
        while(true)
        {
            Connessione c = new Connessione(server.accept());
            c.start();
        }
    }
}