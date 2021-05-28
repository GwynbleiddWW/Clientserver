package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket sock = new ServerSocket(23444);
        System.out.println("Ожидание подключений...");
        try (Socket clientSocket = sock.accept(); PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

            System.out.println("Подключился клиент");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("end")) {
                    System.out.println("Завершаю работу...");
                    break;
                }
                int n = Integer.parseInt(line);
                long fibonacci = getFibonacciValue(n);
                printWriter.println("Число Фибоначчи: " + fibonacci);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static long getFibonacciValue(int n) {
        if (n < 2)
            return n;
        long ans = 0;
        long n1 = 0;
        long n2 = 1;
        for (n--; n > 0; n--) {
            ans = n1 + n2;
            n1 = n2;
            n2 = ans;
        }
        return ans;
    }
}