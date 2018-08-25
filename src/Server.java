import java.io.*;
import java.net.*;
import java.util.*;

//服务器类
public class Server
{

	public static void main(String[] args) throws Exception
	{

		// 实例化一个list,用于保存所有的User
		List<User> list = new ArrayList<User>();
		// 创建绑定到特定端口的服务器套接字
		@SuppressWarnings("resource")
            ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("服务端正在开始~");
		// 循环监听客户端连接
		while (true)
		{
			Socket socket = serverSocket.accept();
			// 每接受一个线程，就随机生成一个一个新用户
			User user = new User("user" + Math.round(Math.random() * 100), socket);

			System.out.println(user.getName() + "正在登录。。。");
			list.add(user);
			// 创建一个新的线程，接收信息并转发
			ServerThread thread = new ServerThread(user, list);
			thread.start();





			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out=new PrintWriter(socket.getOutputStream());
			while (true)
			{
				String s=br.readLine();

				String[] str = s.split(",");

				switch (str[0])
				{

					case "say":
					
							sendToClient(str[1], list, str[2]);
						

						//out.println("Server:"+str[2]);
						//out.write(s+"\n");
						//out.flush();
						break;
					default:
						break;


				}




			}




		}
	}


	private static void sendToClient(String username, List list, String msg)
	{
		if (username.equals("All"))
		{

			for (User user : list)
			{
				//if (user.getName().equals(username)) {
				try
				{
					PrintWriter pw =user.getPw();
					pw.println(msg);
					pw.flush();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}


		}
		else
		{
			for (User user : list)
			{
				if (user.getName().equals(username))
				{
					try
					{
						PrintWriter pw =user.getPw();
						pw.println(msg);
						pw.flush();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}

		}












    }
}

