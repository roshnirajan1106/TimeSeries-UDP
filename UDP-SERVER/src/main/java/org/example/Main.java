package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        final int port = 9999;
        final int timeout = 50000;
        byte[] buffer = new byte[1024];
        try(DatagramSocket socket = new DatagramSocket(port)){
            System.out.println("Server is up and running!!!");
            //socket.setSoTimeout(timeout);
            while (true){
                DatagramPacket request  = new DatagramPacket(buffer,buffer.length);
                try{
                    socket.receive(request);
                }catch (SocketTimeoutException e){
                    System.err.println("Timeout error : " + e.getMessage());
                }
                System.out.println("Recived request from :" + request.getAddress() + " from Port :" + request.getPort());
                String currentTime  = "Server Time: " + LocalDateTime.now();
                byte[] responseData = currentTime.getBytes();
                DatagramPacket response =  new DatagramPacket(responseData, responseData.length,request.getAddress(),request.getPort());
                try {
                    socket.send(response);
                }catch (IOException e){
                    System.err.println("Send error : " + e.getMessage());
                }
                buffer = new byte[1024];
            }
        }
    }
}