package network.scanner1;

import java.net.*;

public class SimpleChecker {
	public static void main(String args[]) {
		for (int port = 1024; port <= 65535; port++) {
			try {
				DatagramSocket server = new DatagramSocket(port);
				server.close();
			} catch (SocketException e) {
				System.out.println("there is a server in port " + port + ".");
			}
		}
	}
}