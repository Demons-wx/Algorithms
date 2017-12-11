package fundamentals.unionfind;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class UFTest {

	public static void main(String[] args) throws IOException {
		BufferedReader bufr = new BufferedReader(new FileReader("F:\\Workspaces\\Algorithms\\src\\fundamentals\\unionfind\\tinyUF.txt"));
		int ch,ch1;
		ch = bufr.read();
		System.out.println(ch);
		ch1 = bufr.read();
		System.out.println(ch1);
// 		QuickFindUF uf = new QuickFindUF(N);
		/*WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
		while(mbfr.read() != -1){
			int p = mbfr.read();
			int q = mbfr.read();
			if(uf.connected(p, q)) continue;
			uf.union(p, q);
			StdOut.println(p+" "+q);
		}
		StdOut.println(uf.count() + "components");*/
	}
}
