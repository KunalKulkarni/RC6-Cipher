import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;




public class Driver {

	public static void main(String args[]) throws IOException {
		
		Results r=new Results();
		KeySchedule keySch=new KeySchedule();
		String inputFname = args[0];
		String outputFname=args[1];
		int choice = 0;
		byte[] ptBytes; byte[] keyBytes;
		byte[] cipBytes;
		File f = new File(inputFname);
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		String type=br.readLine();
		if(type.equals("Encryption"))
			choice=1;
		else if(type.equals("Decryption"))
			choice=2;
		
		if (choice == 1) {
			int finalKey[];
			String encryptedText;
			Conversion conv=new Conversion();
			Encryption encrypt=new Encryption();
			String contents[]=br.readLine().split(":");
			String plainText=contents[1].trim();
			plainText=plainText.replace(" ", "");
			ptBytes=conv.HexToByte(plainText);
			
			String contents1[]=br.readLine().split(":");
			String keyText=contents1[1].trim();
			keyText=keyText.replace(" ", "");
			keyBytes=conv.HexToByte(keyText);
			finalKey=keySch.Scheduler(keyBytes);
			encryptedText=encrypt.encrypt(finalKey, ptBytes);
			r.fileWrite(encryptedText, outputFname,1);
		}	
		
		if(choice==2) {
			
			Decryption decrypt=new Decryption();
			int finalKey[]; String plainText;
			Conversion conv=new Conversion();
			String contents[]=br.readLine().split(":");
			String cipherText=contents[1].trim();
			cipherText=cipherText.replace(" ", "");
			cipBytes=conv.HexToByte(cipherText);
			
			String contents1[]=br.readLine().split(":");
			String keyText=contents1[1].trim();
			keyText=keyText.replace(" ", "");
			keyBytes=conv.HexToByte(keyText);
			finalKey=keySch.Scheduler(keyBytes);
			plainText=decrypt.decrypt(finalKey,cipBytes);
			r.fileWrite(plainText, outputFname,2);
		}

	}

}
