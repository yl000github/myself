package network.scanner1;

import java.net.*;
import java.io.*;

public class SocketChecker implements Runnable {
	// file://declarations
	private String server;
	private DatagramSocket dsock;
	private DatagramPacket packet;
	private int port;
	private Socket socket;
	private String response = "";
	private BufferedReader is;
	private PrintWriter os;
	public static final int TCP = 0;
	public static final int UDP = 1;
	private int type;
	private boolean tcp; // if tcp true, then a tcp scan is done. if false,udp.
	private String message;
	private boolean error = false;

	// constructor - this is used by the factory method. You should not call it.
	public SocketChecker(String server, int port, int type, String message) {
		socket = null;
		dsock = null;
		packet = null;
		this.server = server;
		this.port = port;
		this.type = type;
		this.message = message;
		response = "trying; no connection"; // default response reports message
		// if there is a problem connecting it will be caught as
		// an exception in the run method...
	}

	// methods
	/*
	 * This static factory method is what you use to scan a port public static
	 * String checkSocket(String ahost, int aport, int timeout, int type, String
	 * message); ahost - the machine to scan aport - the port to scan timeout -
	 * tells the thread how many milliseconds to wait for the socket to
	 * respond... int type - you can use the static ints socketChecker.TCP or
	 * socketChecker.UDP to choose tcp or udp scans... message - a String used
	 * either to message a port (TCP), or as the data for the UDP packet. (use
	 * depends upon "type" of scan selected in type)
	 */
	public static String checkSocket(String ahost, int aport, int timeout, int type, String message) {
		SocketChecker look = new SocketChecker(ahost, aport, type, message);
		Thread t = new Thread(look);
		t.start();
		try {
			t.join(timeout);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException e: " + e.toString());
		}
		return look.getResponse();
	}

	// getResponse simply returns the String response
	private String getResponse() {
		return response;
	}

	// the run method
	public void run() {
		if (type == TCP) {
			tcp = true;
		} else {
			tcp = false;
		}
		if (tcp) {
			response = "trying TCP=\"" + message + "\"; no connection";
			// open a tcp socket
			try {
				socket = new Socket(server, port);
			} catch (Exception e) { // catches mainly security and unknown host
									// exceptions
				response += "; " + e.toString();
				error = true;
			}
			if (!error) { // if the socket is open Reader and Writer
				try {
					is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					os = new PrintWriter(socket.getOutputStream(), true /* autoFlush */
					);
				} catch (IOException e) {
					response += "; IO problem; " + e.toString();
					error = true;
				}
				if (!error) { // if Reader and Writer are open
					response = "sending TCP=\"" + message + "\"; no reply";
					try {
						os.println(message);
					} catch (Exception e) {
						response += ("; " + e.toString() + "=" + message);
					}
					try {
						response = "sending TCP=\"" + message + "\"; reply=" + is.readLine();
					} catch (IOException e) {
						response += "; " + e.toString();
					}
				}
			}
		} else {
			// open a udp socket, send a packet, get response..<br
			// />response="trying UDP packet=\"" + message + "\"; can't create";
			try {
				dsock = new DatagramSocket();
			} catch (SocketException se) {
				response = "SocketException: " + se.toString();
				error = true;
			} catch (Exception e) { // mostly to gather the variety of possible
									// security exceptions
				response += "; " + e.toString();
				error = true;
			}
			if (!error) {
				response = "sending UDP packet=\"" + message + "\"; can't send";
				try {
					dsock.send(new DatagramPacket(message.getBytes(), message.getBytes().length,
							InetAddress.getByName(server), port));
				} catch (UnknownHostException e) {
					response += "UnknownHostException:" + e.toString();
					error = true;
				} catch (IOException e) {
					response += "IOException: " + e.toString();
					error = true;
				} catch (Exception e) { // mostly to gather the variety of
										// possible security exceptions
					response += "; " + e.toString();
					error = true;
				}
				if (!error) {
					response = "UDP packet sent=\"" + message + "\"; no reply";
					byte[] buf = new byte[1024];
					packet = new DatagramPacket(buf, buf.length);
					try {
						dsock.receive(packet);
						error = true;
					} catch (ArrayIndexOutOfBoundsException e) {
						response = "server trying to overflow buffer: " + e.toString();
						error = true;
					} catch (IOException e) {
						response = "IOException: " + e.toString();
						error = true;
					}
					response = "UDP packet sent=\"" + message + "\"; reply="
							+ new String(packet.getData(), 0, packet.getLength());
				}
			}
		}
	}
	public static void main(String[] args) {
		String res=checkSocket("127.0.0.1", 80, 3000, TCP, "ee");
		System.out.println(res);
	}
}
