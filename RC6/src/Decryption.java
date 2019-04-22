import java.util.Arrays;

/**
 * Algorithm for Decryption as given in the Document
 * @author Kunal
 *
 */
public class Decryption {

	public String decrypt(int[] key,byte[] cText) {
		
		Conversion conv=new Conversion();
		int A,B,C,D;
		int w=32; int r=20;
		int t,u;
		int len=cText.length/4;
		int[] initText=new int[len];
		int [] S=key;
		String finalPText;
		
		Arrays.fill(initText, 0);
		initText=conv.ByteArraytoIntegerArray(cText, len);
		A=initText[0]; B=initText[1]; C=initText[2]; D=initText[3];
	
		C=C-S[2*r+3];
		A=A-S[2*r+2];
		for(int i=r;i>0;i--) {
			
			int temp=D;
			D=C;
			C=B;
			B=A;
			A=temp;
			
			u=((D*(2*D+1)) << 5) | ((D*(2*D+1)) >>> 27);
			t=((B*(2*B+1)) << 5) | ((B*(2*B+1)) >>> 27);
			int a=C-S[2*i+1];
			C=((a>>>t) | (a<<(32-t))) ^ u;
			int b=A-S[2*i];
			A=((b>>>u) | (b<<(32-u))) ^ t;
		}
		D=D-S[1];
		B=B-S[0];
		initText[0]=A; initText[1]=B; initText[2]=C; initText[3]=D;
		int initlen=initText.length;
		byte[] plainText=new byte[initlen<<2];
		for(int i=0;i<initlen;i++) {
			
			int x= initText[i];
			int j=i<<2;
			plainText[j++]=(byte)((x >>> 0) & 0xff);
			plainText[j++]=(byte)((x >>> 8) & 0xff);
			plainText[j++]=(byte)((x >>> 16) & 0xff);
			plainText[j++]=(byte)((x >>> 24) & 0xff);
		}
		
		StringBuilder sb=new StringBuilder(plainText.length);
		for(byte b:plainText)
			sb.append(String.format("%02x", b));
		finalPText=sb.toString();
		return finalPText;
	}
}
