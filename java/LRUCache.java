import java.util.HashMap;
import java.util.Map;
/**
**设计并实现最近最少经⽤用(LRU)缓存的数据结构。它应该⽀支持以下操作:get 和 put。 get(key) - 如果键存在于缓存中，则获取键的值(总是正数)，否则返回 -1。
**put(key, value) - 如果键不不存在，请设置或插⼊入值。当缓存达到其容量量时，它应该在插⼊入新项⽬目之前， 使最近最少使⽤用的项⽬目⽆无效。
*/

public class LRUCache{
	Map<String, LRUNode> map = new HashMap<>();
	LRUNode head;
	LRUNode tail;

	int capacity;

	public LRUCache(int capacity){
		this.capacity = capacity;
	}

	public void put(String key, Object value){
		if (head == null) {
			head = new LRUNode(key, value);
			tail = head;
			map.put(key, head);
		}
		LRUNode node = map.get(key);
		if (node != null){
			node.value = value;
			// 把他从链表删除并且插入到头结点
			removeAndInsert(node);
		}else {
			LRUNode tmp = new LRUNode(key, value);
			// 如果溢出
			if (map.size() >= capacity){
				map.remove(tail.key);
				// 删除尾部节点
				tail = tail.prev;
				tail.next = null;
			}
			map.put(key, tmp);
			// 插入
			tmp.next = head;
			head.prev = tmp;
			head = tmp;
		}
	}

	public Object get(String key){
		LRUNode node = map.get(key);
		if (node != null){
			removeAndInsert(node);
			return node.value;
		}
		return null;
	}

	public void removeAndInsert(LRUNode node){
		// 删除节点
		if (node == head){
			return;
		}else if (node == tail){
			tail = node.prev;
			tail.next = null;
		}else {
			node.prev.next = node.next;
			node.next.prev = node.prev;

		}
		// 插入到头结点
		node.next = head;
		node.prev = null;
		head.prev = node;
		head = node;
	}

	public class LRUNode{
		String key;
		Object value;
		LRUNode next;
		LRUNode prev;

		public LRUNode(String key, Object value){
			this.key = key;
			this.value = value;
		}
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.put("1", 1);
		cache.put("2", 1);
		System.out.println(cache.get("1")); // 1
		cache.put("3",3);
		System.out.println(cache.get("2")); // null
		System.out.println(cache.get("3")); // 3

	}
}