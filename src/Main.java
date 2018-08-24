import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		int i=0;
		Main m=new Main();
		System.out.println(i++ + +m.op(i));
		System.out.println(i);
	
		
			}
		public int op(int i){
			System.out.println(i++);
			return i;
			
		}	
			
}
