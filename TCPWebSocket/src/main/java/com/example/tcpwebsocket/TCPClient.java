package com.example.tcpwebsocket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class TCPClient extends Thread {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("输入要建立socket的端口:");
        int port = scanner.nextInt();
        TCPClient clientTest = new TCPClient("localhost", 10000);
        clientTest.start();
    }

    Socket socket = null;

    public TCPClient(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int[] getRandom() {
        Random random = new Random();
        int[] temp = new int[33];
        int[] array = new int[7];
        for(int i=0;i<temp.length;i++){
            temp[i] = i+1;
        }
        int index = -1;
        for (int i=0;i<array.length ;i++ ) {
            if(i<=6){
                index = random.nextInt(temp.length-i);
            }
            else{
                index = random.nextInt(16);
            }
            array[i] = temp[index];
            int num = temp[index];
            temp[index] = temp[temp.length-1-i];
            temp[temp.length-1-i] = num;

        }
        return array;
    }

    @Override
    public void run() {
        new sendMessThread().start();
        super.run();
    }

    class sendMessThread extends Thread {
        @Override
        public void run() {
            super.run();
            //写操作
            OutputStream os = null;
            try {
                os = socket.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                scanner = new Scanner(System.in);
                int[] num1 = getRandom();
                int[] num2 = getRandom();
                String str = "";
                while (true) {
                    str = scanner.nextLine();
                    if (str.equals("exit")) {
                        System.exit(0);
                    } else {
                        char[] temp = str.toCharArray();
                        bufferedWriter.write(temp);
                        bufferedWriter.flush();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
