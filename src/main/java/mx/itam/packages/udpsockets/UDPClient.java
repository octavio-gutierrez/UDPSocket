package mx.itam.packages.udpsockets;

import java.net.*;
import java.io.*;

public class UDPClient {

    public static void main(String args[]) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            String myMessage = "Hello";
            byte[] m = myMessage.getBytes();

            InetAddress aHost = InetAddress.getByName("localhost");
//            InetAddress aHost = InetAddress.getByAddress("localhost", new byte[] {(byte) 127, (byte) 0, (byte) 0, (byte) 1});
            int serverPort = 49152;
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            socket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);
            System.out.println("Reply: " + (new String(reply.getData())).trim());
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (socket != null)
                socket.close();
        }
    }
}