import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//Servidor myServer
/*
public class MyServer{
	private Socket sock;
	public MyServer(Socket s) { sock = s; }
	
	public static void main(String[] args) throws Exception {
		System.setProperty("javax.net.ssl.keyStore", "./keystore.server");
		System.setProperty("javax.net.ssl.keyStorePassword", "key123");
		ServerSocketFactory ssf = SSLServerSocketFactory.getDefault( );
		SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(9096);
		while (true) {
			new MyServer(ss.accept()).startServer(); // uma thread por ligação
		}
		
	}

	public void startServer (){

		Socket inSoc = sock;
		ServerThread newServerThread = new ServerThread(inSoc);
		newServerThread.start();

		//sSoc.close();
	}


	//Threads utilizadas para comunicacao com os clientes
	class ServerThread extends Thread {

		private Socket socket = null;

		ServerThread(Socket inSoc) {
			socket = inSoc;
			System.out.println("thread do server para cada cliente");
		}
 
		public void run(){
			try {
				ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

				String user = null;
				String passwd = null;
				int bytes = 0;
				
				try {
					user = (String)inStream.readObject();
					passwd = (String)inStream.readObject();
					
					
					System.out.println("thread: depois de receber a password e o user");
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
 			
				//TODO: refazer
				//este codigo apenas exemplifica a comunicacao entre o cliente e o servidor
				//nao faz qualquer tipo de autenticacao
				if (user.length() != 0 && user.equals("sara") && passwd.equals("vasques")){
					
					outStream.writeBoolean(true);
					
					bytes = (Integer)inStream.readObject();
					System.out.println(bytes);
				}
				else {
					outStream.writeBoolean(false);
				}

				outStream.close();
				inStream.close();
 			
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
*/

public class MyServer {
	public static void main (String[]args) throws IOException, ClassNotFoundException {
		ServerSocket srvSocket = new ServerSocket(23456);
		try {
			Socket cliSocket = srvSocket.accept();

			ObjectInputStream in = new ObjectInputStream(cliSocket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(cliSocket.getOutputStream());

			String fromClient = (String) in.readObject();

			System.out.print(fromClient);

			String answer = new String();
			answer = "resposta";
			out.writeObject(answer);

			out.close();
			in.close();

			cliSocket.close();
			srvSocket.close();
		}catch (IOException e){
			System.out.print("Erro");
		}
	}
}