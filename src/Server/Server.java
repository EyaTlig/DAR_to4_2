package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	private static final int PORT = 1234;
	private static byte[] data = new byte[1024];
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(PORT);
		System.out.println("Lancement du serveur");

		while (true) {
			DatagramPacket paquet = new DatagramPacket(data, data.length);
			socket.receive(paquet);
			String msg = new String(paquet.getData(), 0, paquet.getLength());
			System.out.println(paquet.getAddress() + " : " + msg);
			String reponse = msg + " est connecté à " + getCurrentTime();
			DatagramPacket envoi = new DatagramPacket(reponse.getBytes(), reponse.length(), paquet.getAddress(), paquet.getPort());
			socket.send(envoi);
		}
	}
		private static String getCurrentTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}