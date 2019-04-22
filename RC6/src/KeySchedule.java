import java.util.Arrays;


public class KeySchedule {

	Conversion conv = new Conversion();

	public int[] Scheduler(byte[] key) {

		int w = 32, r = 20;
		int A=0, B=0;
		int i=0; int j=0;
		int Pw = 0xb7e15163, Qw = 0x9e3779b9;
		int[] S = new int[2 * r + 4];
		int L[];
		S[0] = Pw;
		int c = key.length / 4;
		L = conv.toWords(key, c);
		for (int x = 1; x <= (2 * r + 3); x++) {
			S[x] = S[x-1] + Qw;
		}
		int v= 3 * Math.max(c, (2*r+4));
		for(int s=1;s<=v;s++) {
			S[i]= ((S[i]+A+B) << 3) | ((S[i]+A+B) >>> 29);
			A=S[i];
			L[j]= ((L[j]+A+B) << (A+B)) | ((L[j]+A+B) >>> (32-(A+B)));
			B=L[j];
			i=(i+1)%(2*r+4);
			j=(j+1)%c;
		}
		return S;

	}

}
