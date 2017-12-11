package searching.wordcount;

public class SplitTest {
	
	public static void main(String[] args){
		String tiny = "It was the best of times, it was the worst of times,";
		String[] arr = tiny.split("[^a-zA-Z]"); 
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
