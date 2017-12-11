package fundamentals.algorithmsanalysis;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DoubingTest {

	public static double timeTrial(int N){
		int MAX = 1000000;
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform(-MAX, MAX);
		}
		Stopwatch stopwatch = new Stopwatch();
		int cnt = ThreeSum.count(a);
		return stopwatch.elapsdTime();
	}
	
	public static void main(String[] args) {
		// 打印运行时间的表格
		for (int N = 250; true; N += N) {
			double time = timeTrial(N);
			StdOut.printf("%7d %5.1f\n", N, time);
		}
	}
}
