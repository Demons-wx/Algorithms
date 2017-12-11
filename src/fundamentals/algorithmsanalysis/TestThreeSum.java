package fundamentals.algorithmsanalysis;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TestThreeSum {

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform(-1000000, 1000000);
		}
		Stopwatch stopwatch = new Stopwatch();
		int cnt = ThreeSum.count(a);
		double time = stopwatch.elapsdTime();
		StdOut.println(cnt + " triples" + time + " seconds");
	}
}
