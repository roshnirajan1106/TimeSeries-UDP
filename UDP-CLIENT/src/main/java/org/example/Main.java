package org.example;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello and welcome!");
        InetAddress address = InetAddress.getByName("localhost");
        final int port = 9999;
        final int timeout = 5000;
        try(DatagramSocket socket = new DatagramSocket()){
            String msg = "What time is it now ?";
            byte[] messageArray = msg.getBytes();
            socket.setSoTimeout(timeout); // 5 second timeout
            DatagramPacket packet = new DatagramPacket(messageArray,messageArray.length,address,port);
            try{
                socket.send(packet);
            }catch (IOException e){
                System.err.println("Send error : " + e.getMessage());
            }
            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer,buffer.length);
            try {
            socket.receive(response);
            String message = new String(response.getData(), 0, response.getLength(), StandardCharsets.UTF_8);
            System.out.println(message);
            }catch (SocketTimeoutException e){
                System.err.println("Timeout error : " + e.getMessage());
            }
            catch (IOException e){
                System.err.println("Receive error : " + e.getMessage());
            }
        } catch (SocketException e){
            System.err.println("Socket error : "  +  e.getMessage());
        }
    }
}