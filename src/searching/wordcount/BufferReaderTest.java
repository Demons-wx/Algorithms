package searching.wordcount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 读取文件，统计文件中单词频数
 * 
 * @author Demons
 * 
 */
public class BufferReaderTest {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("text/tale.txt"));
		List<String> list = new ArrayList<String>();
		String readLine = null;
		while ((readLine = br.readLine()) != null) {
			String[] wordsArr1 = readLine.split("[^a-zA-Z]"); // 过滤出只含有字母的
			for (String word : wordsArr1) {
				if (word.length() != 0) { // 去除长度为0的行
					list.add(word.toLowerCase()); // 忽略大小写
				}
			}
		}
		br.close();
		Map<String, Integer> wordsCount = new TreeMap<String, Integer>(); // 存储单词计数信息，key值为单词，value为单词数
		// 单词的词频统计
		for (String li : list) {
			if (wordsCount.get(li) != null) {
				wordsCount.put(li, wordsCount.get(li) + 1);
			} else {
				wordsCount.put(li, 1);
			}
		}
		SortMap(wordsCount); // 按值进行排序
	}

	// 按value的大小进行排序
	private static void SortMap(Map<String, Integer> oldmap) {

		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldmap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue(); // 降序
			}
		});
		for (int i = 0; i < 10; i++) {
			System.out.println(list.get(i).getKey() + ": "+ list.get(i).getValue());
		}
	}
}
