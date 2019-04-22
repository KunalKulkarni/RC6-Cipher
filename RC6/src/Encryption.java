import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Algorithm for Encryption as given in the document 
 * @author Kunal
 *
 */
public class Encryption {

	public String encrypt(int[] key,byte[] pText) throws UnsupportedEncodingException {
		
		String finalCipher;
		Conversion conv=new Conversion();
		int A,B,C,D;
		int w=32; int r=20;
		int t,u;
		int len=pText.length/4;
		int[] initText=new int[len];
		int [] S=key;
		//byte[] cipherText=new byte[pText.length];
		Arrays.fill(initText, 0);
		initText=conv.ByteArraytoIntegerArray(pText, len);
		A=initText[0]; B=initText[1]; C=initText[2]; D=initText[3];
		
		B=B+S[0];
		D=D+S[1];
		
		for(int i=1;i<=r;i++) {
			
			t=((B*(2*B+1)) << 5) | ((B*(2*B+1)) >>> 27);
			
			u=((D*(2*D+1)) << 5) | ((D*(2*D+1)) >>> 27);
			
			A=(((A^t) << u) | ((A^t) >>> (32-u)))+S[2*i];
			
			C=(((C^u)<<t) | ((C^u)>>>(32-t)))+S[2*i+1];
			
			int temp=A;
			A=B;
			B=C;
			C=D;
			D=temp;
		}
		A=A+S[2*r+2];
		C=C+S[2*r+3];
		
		initText[0]=A; initText[1]=B; initText[2]=C; initText[3]=D; 
		
		int initlen=initText.length;
		/**
		 * Convert to an byte array from an integer array
		 */
		byte[] cipher=new byte[initlen<<2];
		for(int i=0;i<initlen;i++) {
			int x= initText[i];
			int j=i<<2;
			cipher[j++]=(byte)((x >>> 0) & 0xff);
			cipher[j++]=(byte)((x >>> 8) & 0xff);
			cipher[j++]=(byte)((x >>> 16) & 0xff);
			cipher[j++]=(byte)((x >>> 24) & 0xff);
		}
		/**
		 * Following code has been taken from a stackoverflow question to convert a Byte array to Hexadecimal String
		 */
		StringBuilder sb=new StringBuilder(cipher.length);
		for(byte b:cipher)
			sb.append(String.format("%02x", b));
		finalCipher=sb.toString();
		return finalCipher;
	}
}
