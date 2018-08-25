import java.io.*;
import java.util.*;
import java.net.*;

public class ServerInput  extends Thread
{
	List<User> list;
	Socket socket;
	public ServerInput(List<User> list,Socket socket)
	{
this.socket=socket;
this.list=list;
	}
	public void run()
	{

		while (true)
		{


			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//	PrintWriter out=new PrintWriter(socket.getOutputStream());
			while (true)
			{
				try
				{
					String s=br.readLine();

					String[] str = s.split(",");

					switch (str[0])
					{

						case "say":

							sendToClient(str[1], list,"FromServer:"+ str[2]);


							//out.println("Server:"+str[2]);
							//out.write(s+"\n");
							//out.flush();
							break;
						default:
							break;


					}
				}
				catch (IOException e)
				{}




			}
}
		}


	private static void sendToClient(String username, List<User> list, String msg)
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
