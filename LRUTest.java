package thread;

public class LRUTest<T> {
	// 内部节点是一个双向链表节点
	public static class Node<T> {
		private Node pre;
		private Node next;
		private T value;

		public Node(Node pre, Node next, T value) {
			this.pre = pre;
			this.next = next;
			this.value = value;
		}
	}

	public Node head; // 链表头
	public Node tail; // 链表尾部
	private int size; // 节点个数
	private int capacity; // 链表的容量

	public LRUTest(int capacity) {
		this.capacity = capacity;
	}

	public int size() {
		return size;
	}

	// 输出链表结果
	public void print() {
		Node node = head;
		while (((node)) != null) {
			System.out.println(node.value);
			node = node.next;
		}
	}

	private void insertHead(Node node) {
		Node tem = head;
		head = node;
		head.pre = null;
		head.next = tem;
		tem.pre = head;
	}

	public void add(T value) {
		addNode(new Node(null, null, value));
	}

	private void addNode(Node item) {
		// 若链表为空,初始化链表
		if (head == null && tail == null) {
			head = item;
			tail = item;
			size++;
			return;
		}

		Node cursor = head; // 用于遍历链表的游标
		Node tem = head; // 保存当前的头节点

		// 遍历节点，查看是否命中缓存
		while ((cursor) != null) {
			// 命中缓存
			if (cursor.value.equals(item.value)) {
				// 当命中的node节点位于链表中，不在头部和尾部时,剔除当前节点，并在后续操作重新插入头部
				if (cursor.next != null && cursor.pre != null) {
					cursor.next.pre = cursor.pre; // 将当前node节点的next节点向前指向node节点的pre节点
					cursor.pre.next = cursor.next; // 将当前node节点的pre节点向后指向node节点的next节点
				} else if (cursor.next == null && cursor.pre != null) {
					// 当node节点位于尾部,剔除当前节点，并在后续操作重新插入头部
					tail = cursor.pre; // 将node节点的pre节点设为尾部tail，并向后指向null
					cursor.pre.next = null;
				} else {
					// 当node节点位于头部时，说明当前节点已经时最新节点,不进行操作
					return;
				}
				// 将当前节点插入头部
				insertHead(cursor);
				return;
			}
			cursor = cursor.next;
		}
		// 当插入的节点不在缓存中时
		// 检查边界，超过链表容量时，剔除尾部节点，并插入新节点到头部
		if ((size() + 1) > capacity) {
			Node end = tail;
			tail = end.pre;
			tail.next = null;
			end = null;
			insertHead(item);
		} else {
			// 正常插入头部
			insertHead(item);
			size++;
		}
	}

	public static void main(String[] args) {
		LRUTest<Integer> lru = new LRUTest(4);
		lru.add(1);
		lru.add(1);
		lru.add(1);
		lru.add(2);
		lru.add(3);
		lru.add(4);
		lru.add(4);
		lru.add(3);
		lru.add(5);
		System.out.println("size:" + lru.size());
		lru.print();
	}
}
