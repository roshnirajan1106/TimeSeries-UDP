package org.example;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        InetAddress address = InetAddress.getByName("localhost");
        DatagramSocket socket = new DatagramSocket();
        String msg = "What time is it now ?";
        byte[] messageArray = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(messageArray,messageArray.length,address,9999);
        socket.send(packet);
        byte[] buffer = new byte[1024];
        DatagramPacket response = new DatagramPacket(buffer,buffer.length);
        socket.receive(response);
        String message = new String(response.getData(), 0, response.getLength(), StandardCharsets.UTF_8);

        System.out.println(message);
    }
}