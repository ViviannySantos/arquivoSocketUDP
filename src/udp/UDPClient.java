package udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	
	public static void main(String args[]) throws Exception {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		DatagramSocket clientSocket = new DatagramSocket();

		String servidor = "localhost";
		int porta = 12345;

		InetAddress IPAddress = InetAddress.getByName(servidor);

		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];

		System.out.println("Digite o texto a ser enviado ao servidor: ");
		String sentence = bufferedReader.readLine();
		sendData = sentence.getBytes();
		DatagramPacket datagramPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);

		System.out.println("Enviando pacote UDP para " + servidor + ": " + porta);
		clientSocket.send(datagramPacket);

		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

		clientSocket.receive(receivePacket);
		System.out.println("Pacote UDP recebido...");

		String texto = new String(receivePacket.getData());

		System.out.println("Texto recebido do servidor: " + texto);
		clientSocket.close();
		System.out.println("Socket cliente fechado!");
	}
}
