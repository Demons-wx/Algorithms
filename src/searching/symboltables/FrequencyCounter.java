package searching.symboltables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.princeton.cs.algs4.StdOut;
import fundamentals.algorithmsanalysis.Stopwatch;

/**
 * 统计文件中各单词出现的频数
 *
 * @author Demons
 */
public class FrequencyCounter {

    public static void main(String[] args) throws IOException {

        int minlen = 1; // 最小键长
//		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        BST<String, Integer> st = new BST<String, Integer>();
//		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(9973);
        BufferedReader br = new BufferedReader(new FileReader("F:\\tale.txt"));
        List<String> words = new ArrayList<String>();
        String readLine = null;
        while ((readLine = br.readLine()) != null) {
            String[] wordsArr = readLine.split("[^a-zA-Z]");
            for (String word : wordsArr) {
                if (word.length() >= minlen) {
                    words.add(word.toLowerCase());
                }
            }
        }
        br.close();
        Stopwatch time = new Stopwatch();
        for (int i = 0; i < words.size(); i++) {
            String key = words.get(i);
            if (!st.contains(key)) {
                st.put(key, 1);
            } else {
                st.put(key, st.get(key) + 1);
            }
        }
        StdOut.println("size: " + st.size());
        // 找出出现频率最高的单词
        String max = " ";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));
        System.out.println("耗时：" + time.elapsdTime() + "秒");
    }
}
