import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Results {

	public void fileWrite(String text,String filename,int type) throws IOException {
		
		FileWriter fw=new FileWriter(filename);
		PrintWriter pw=new PrintWriter(fw,true);
		text=text.replaceAll("..", "$0 ");
		if(type==1)
		pw.println("Ciphertext- "+text);
		else
		pw.println("Plaintext:- "+text);
	}
}
