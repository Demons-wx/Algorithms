package searching.wordcount;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class FileWordCount {
	 public static void main(String[] args) {
         try {
             BufferedReader br = new BufferedReader(new FileReader("F://tale.txt"));
             String s;
             StringBuffer sb = new StringBuffer();
             while ((s = br.readLine()) != null) {
                 sb.append(s);
             }
             Map<String,Integer> map = new HashMap<String, Integer>();
             StringTokenizer st = new StringTokenizer(sb.toString(),",.! ;'\"\n");
             while (st.hasMoreTokens()) {
                 String letter = st.nextToken();
                 int count;
                 if (map.get(letter) == null) {
                     count = 1;
                 } else {
                     count = map.get(letter).intValue() + 1;
                 }
                 map.put(letter,count);
             }
             
             Set<WordEntity> set = new TreeSet<WordEntity>();
             for (String key : map.keySet()) {
                 set.add(new WordEntity(key,map.get(key)));
             }
             
             // �Լ�ƴ���ַ��������������Ҫ���ַ�����ʽ
             printFormat_3(set);
             
         } catch (FileNotFoundException e) {
             System.out.println("�ļ�δ�ҵ�~��");
         } catch (IOException e) {
             System.out.println("�ļ����쳣~��");
         }
     }
	 
	 private static void printFormat_1(Set<WordEntity> set){
		 System.out.println("�����ʽһ��");
         for (Iterator<WordEntity> it = set.iterator(); it.hasNext(); ) {
             WordEntity w = it.next();
             System.out.println("����:" + w.getKey() + " ���ֵĴ���Ϊ�� " + w.getCount());
         }
	 }
	 
	 private static void printFormat_2(Set<WordEntity> set){
		// ֱ�Ӵ�ӡ WordEntity ����ʵ��������Ҫ�����Ч����ֻ����WordEntity������дtoString()����
         System.out.println("�����ʽ����");
         for (Iterator<WordEntity> it = set.iterator(); it.hasNext(); ) {
             WordEntity w = it.next();
             System.out.println(w);
         }
	 }
	 
	 private static void printFormat_3(Set<WordEntity> set){
		// ���ǿ��Կ���ֻ���ǰ������
         System.out.println("�����ʽ����");
         int count = 1;
         for (Iterator<WordEntity> it = set.iterator(); it.hasNext(); ) {
             WordEntity w = it.next();
             System.out.println("��" + count + "��Ϊ����:" + w.getKey() + " ���ֵĴ���Ϊ�� "
                     + w.getCount());
             if (count == 3)// �����3��������ѭ��
                 break;
             count++;
         }
	 }
}
