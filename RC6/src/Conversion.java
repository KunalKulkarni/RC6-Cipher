import java.util.Arrays;

public class Conversion {
	
	int hexmax=0xff;
	public int[] toWords(byte[] key, int c) {

		int[] words = new int[c];
		Arrays.fill(words, 0);
		int cnt = 0;
		for (int i = 0; i < c; i++) {

			words[i] = ((key[cnt++] & hexmax)) | ((key[cnt++] & hexmax) << 8) | ((key[cnt++] & hexmax) << 16)
					| ((key[cnt++] & hexmax) << 24);
			
		}
		return words;
	}
	
	public byte[] HexToByte(String str) {
		
		byte [] retArr=new byte[str.length()/2];
		for(int i=0;i<str.length();i=i+2) {
			int pos=i/2;
			retArr[pos]= (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
		}
		return retArr;
	}
	
	public int[] ByteArraytoIntegerArray(byte[] oldArray,int len) {
		
		int pos=0;
		int newArray[]=new int[len];
		Arrays.fill(newArray, 0);
		for(int i=0;i<len;i++) {
			
			newArray[i]=((oldArray[pos++] & hexmax)) | ((oldArray[pos++] & hexmax) << 8)
					| ((oldArray[pos++] & hexmax) << 16) | ((oldArray[pos++] & hexmax) << 24);
		}
		return newArray;
	}
	
}
