package ru.netology;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket client = new Socket("127.0.0.1", 23444);

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
             PrintWriter printWriter = new PrintWriter(new OutputStreamWriter
                     (client.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in);) {

            String line;
            while (true) {
                System.out.println("Введите число для вычисления N-го члена ряда Фибоначчи или end для завершения программы: ");
                line = scanner.nextLine();

                printWriter.println(line);
                if ("end".equals(line)) break;

                line = bufferedReader.readLine();

                System.out.println("Ответ сервера " + line);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
