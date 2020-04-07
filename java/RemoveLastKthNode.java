/*
**移除链表中倒数第几位
*/
public class RemoveLastKthNode{

	private Node node;

	public Node removeLastKth(Integer[] arr, int k){
		Node head = changeToNode(arr);
		Node temp2 = head;
		if (head == null || k < 1) {
			return head;
		}
		Node temp = head;
		int len = 0;
		while(temp != null){
			len++;
			temp = temp.next;
		}
		if (len == k){
			return head.next;
		}
		if (len > k){
			temp = head;
			// 删除第（len - k + 1）个节点
			while (len - k != 1){
				temp = temp.next;
				len--;
			}
			temp.next = temp.next.next;
		}
		return head;
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
		Integer[] arr = new Integer[]{1,2,3,4,5,6};
		System.out.println("初始遍历：");
		for(Integer r : arr){
			System.out.print(r+" ");
		}
		RemoveLastKthNode remove = new RemoveLastKthNode();
		int i = 3;
		Node result = remove.removeLastKth(arr, i);
		System.out.println("\n删除倒数第"+i+"个遍历：");
		while (result != null){
			System.out.print(result.value + " ");
			result = result.next;
		}
	}
}