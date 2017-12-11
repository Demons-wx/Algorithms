package fundamentals.algorithmsanalysis;

public class Stopwatch {

	private final long start;
	public Stopwatch(){
		start = System.currentTimeMillis();
	}
	public double elapsdTime(){
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}
}
