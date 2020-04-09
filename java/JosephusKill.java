/**
**约瑟夫环：每数到m个就移除，直到最后一个
**输⼊:⼀个环形单向链表的头节点 head 和报数 m. 
**返回:最后⽣生存下来的节点，且这个节点⾃自⼰己组成环形单向链表，其他节点都删除掉。
**
**递归，找到f(n)和f(n-1)的关系
**删除前   删除后
**m-2		n-2
**m-1		n-1
**m  		被删除
**m+1   	1(重新报数)
**m+2 		2
**f(n) = (f(n-1) + m - 1) % n + 1。（-1是因为编号从1开始）
*/

public class JosephusKill{

	public Node kill(Integer[] arr, int m){
		Node head = changeToNode(arr);
		if (head == null || m < 1) {
			return head;
		}
		int n = 1;
		Node last = head;
		while(last.next!=head){
			n++;
			last = last.next;
		}
		int des = getDes(n, m);
		while(--des != 0){
			head = head.next;
		}
		head.next = head;
		return head;
	}

	private int getDes(int n, int m){
		if (n == 1) {
			return 1;
		}
		return (getDes(n-1, m) + m - 1) % n + 1;
	}

	private Node changeToNode(Integer[] arr){
		if (arr.length == 0){
			return null;
		}
		Node head = new Node(arr[0]);
		Node temp = head;
		for (int i=1; i < arr.length; i++) {
			temp.next = new Node(arr[i]);
			temp = temp.next;
			if(i == arr.length-1 ){
				temp.next = head;
			}
		}
		return head;
	}

	class Node{
		private int value;
		private Node next;
		public Node(int value){
			this.value = value;
		}
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[]{1,2,3,4,5,6,7};
		System.out.println("初始遍历：");
		for(Integer r : arr){
			System.out.print(r+" ");
		}
		JosephusKill remove = new JosephusKill();
		int i = 3;
		Node result = remove.kill(arr, i);
		System.out.println("\n每次删除第"+i+"个：");
		System.out.print(result.value + " ");
	}

}