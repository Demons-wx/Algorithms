package fundamentals.unionfind;

import java.io.IOException;
import java.io.Reader;

// �Զ���һ���ַ��������������ڻ����ַ����Ӷ���߲���Ч�ʡ�
/*
 * ������
 * 1. ��Ҫ�����顣
 * 2. ��Ҫ��������в�����Ҫ�нǱꡣ
 * */
public class MyBufferedReader {
	
	private Reader r;
	
	// ����һ���ַ����飬��Ϊ������
	private char[] buf = new char[1024];
	
	// ����һ�����������ڲ��������е�Ԫ�ء�
	private int index = 0;
	
	// ����һ�����������ڼ�¼��ȡ�ַ��ĸ�����
	private int count = 0;
	
	public MyBufferedReader(Reader r){  // ���Զ�Reader������������в���
		this.r = r;
	}
	
	// ������
	public int read() throws IOException{
		// ͨ��������ӵײ��豸�϶�ȡһ�����������ݵ�����������count������
		// ����������û������ʱ���ӵײ��
		if(count == 0){
			count = r.read(buf);
			index = 0;
		}
		// count<0 ��ʾû�������ˡ�
		if(count < 0)
			return -1;
		// �ӻ�������ȡ��һ���ַ����ӵ�һ����ʼ��
		char ch = buf[index];
		index++;
		count--;
		
		return ch;
	}
	
	public String readLine() throws IOException{
		
		// ����һ����ʱ����
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
		
		// ������һ��û����ֹ�����ж�sb����û�����ݣ��оͷ��ء�
		if(sb.length() != 0)
			return sb.toString();
		
		return null;
	}
}
