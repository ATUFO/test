import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 *   作用：一直接收服务端转发过来的信息
 * */
public class ClientThread extends Thread {

	private Socket socket;
	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream);
			BufferedReader br = new BufferedReader(inputStreamReader);
			try {
				// 信息的格式：(login||logout||say),发送人,收发人,信息体
				while (true) {
					String msg=br.readLine();
					if(msg.startsWith("登录")||msg.startsWith("From")){
					System.out.println(msg);
					}
			
					String[] str = msg.split(",");
					switch (str[0]) {
						case "say":
							System.out.println("  From  "+str[3]+":"+str[2]);
							break;
						default:
							break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    }

	
