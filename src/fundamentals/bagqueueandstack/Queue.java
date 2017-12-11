package fundamentals.bagqueueandstack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 要将一个元素入列enqueue()，我们要将它添加到表尾
 * 要将一个元素出列dequeue()，我们要删除表头的结点
 * @author Demons
 *
 * @param <Item>
 */
public class Queue<Item> {

	private Node first; // 指向最早添加的结点的链接
	private Node last; // 指向最近添加的结点链接
	private int N; // 队列中的元素数量
	private class Node{
		Item item;
		Node next;
	}
	public boolean isEmpty(){
		return first == null; // 或N == 0
	}
	public int size(){
		return N;
	}
	public void enqueue(Item item){
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()){
			first = last;
		}else{
			oldlast.next = last;
		}
		N++;
	}
	public Item dequeue(){
		Item item = first.item;
		first = first.next;
		if(isEmpty()){
			last = null;
		}
		N--;
		return item;
	}
	
	public static void main(String[] args) {
		Queue<String> q = new Queue<String>();
		while(!StdIn.isEmpty()){
			String item = StdIn.readString();
			if(!item.equals("-")){
				q.enqueue(item);
			}else if(!q.isEmpty()){
				StdOut.print(q.dequeue()+ " ");
			}
		}
		StdOut.println("("+q.size()+" left on queue)");
	}
}
