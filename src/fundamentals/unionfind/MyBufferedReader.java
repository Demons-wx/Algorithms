package fundamentals.unionfind;

import java.io.IOException;
import java.io.Reader;

// 自定义一个字符流缓冲区。用于缓冲字符，从而提高操作效率。
/*
 * 分析：
 * 1. 需要有数组。
 * 2. 需要对数组进行操作，要有角标。
 * */
public class MyBufferedReader {
	
	private Reader r;
	
	// 定义一个字符数组，作为缓冲区
	private char[] buf = new char[1024];
	
	// 定义一个索引，用于操作数组中的元素。
	private int index = 0;
	
	// 定义一个变量，用于记录读取字符的个数。
	private int count = 0;
	
	public MyBufferedReader(Reader r){  // 可以对Reader的所有子类进行操作
		this.r = r;
	}
	
	// 读方法
	public int read() throws IOException{
		// 通过流对象从底层设备上读取一定数量的数据到缓冲区，用count计数。
		// 当缓冲区中没有数据时，从底层读
		if(count == 0){
			count = r.read(buf);
			index = 0;
		}
		// count<0 表示没有数据了。
		if(count < 0)
			return -1;
		// 从缓冲区中取出一个字符，从第一个开始。
		char ch = buf[index];
		index++;
		count--;
		
		return ch;
	}
	
	public String readLine() throws IOException{
		
		// 定义一个临时容器
		StringBuilder sb = new StringBuilder();
		
		int ch = 0;
		while((ch = this.read()) != -1){
			if(ch == '\r'){
				continue;
			}
			if(ch == '\n'){
				return sb.toString();
			}
			sb.append((char)ch);
		}
		
		// 如果最后一行没有终止符，判断sb中有没有内容，有就返回。
		if(sb.length() != 0)
			return sb.toString();
		
		return null;
	}
}
