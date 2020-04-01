// 非递归遍历
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;

public class TreeDisplay{
	private TreeNode root;

	public TreeDisplay(Integer[] arr){
		TreeNode root = makeTree(arr, 0);
		this.root = root;
	}

	// 先序遍历
	public List<Integer> DLRDisplay(){
		List<Integer> result = new ArrayList<>();
		Stack<TreeNode>  stack = new Stack<>();
		if (root == null){
			return result;
		}
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode tmp = stack.pop();
			result.add(tmp.value);
			if (tmp.right != null){
				stack.push(tmp.right);
			}
			if (tmp.left != null){
				stack.push(tmp.left);
			}
		}
		System.out.println("\n前序遍历：");
		for(Integer r : result){
			System.out.print(r+" ");
		}
		return result;
	}

	// 中序遍历
	public List<Integer> LDRDisplay(){
		List<Integer> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode tmp = root;
		while(tmp != null || !stack.isEmpty()){
			if (tmp != null){
				stack.push(tmp);
				tmp = tmp.left;
			} else {
				tmp = stack.pop();
				result.add(tmp.value);
				tmp = tmp.right;
			}
		}
		System.out.println("\n中序遍历：");
		for(Integer r : result){
			System.out.print(r+" ");
		}
		return result;
	}

	// 后序遍历
	public List<Integer> LRDDisplay(){
		LinkedList<Integer> result = new LinkedList<>();
		Stack<TreeNode> stack = new Stack<>();
		if (root == null){
			return result;
		}
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode tmp = stack.pop();
			result.addFirst(tmp.value);
			if (tmp.left != null){
				stack.push(tmp.left);
			}
			if (tmp.right != null){
				stack.push(tmp.right);
			}
		}
		System.out.println("\n后序遍历：");
		for(Integer r : result){
			System.out.print(r+" ");
		}
		return result;
	}

	// 层序遍历
	public List<Integer> TopToBottomDisplay(){
		List<Integer> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (root == null){
			return result;
		}
		queue.offer(root);
		while(!queue.isEmpty()){
			TreeNode tmp = queue.poll();
			result.add(tmp.value);
			if (tmp.left != null){
				queue.offer(tmp.left);
			}
			if (tmp.right != null){
				queue.offer(tmp.right);
			}
		}
		System.out.println("\n层序遍历：");
		for(Integer r : result){
			System.out.print(r+" ");
		}
		return result;
	}

	public TreeNode makeTree(Integer[] arr, int index){
		TreeNode tn = null;
        if (index < arr.length) {
            Integer value = arr[index];
            if (value == null) {
                return null;
            }
            tn = new TreeNode(value);
            tn.left = makeTree(arr, 2*index+1);
            tn.right = makeTree(arr, 2*index+2);
            return tn;
        }
        return tn;
	}
	

	public class TreeNode{
		Integer value;
		TreeNode left;
		TreeNode right;
		public TreeNode(Integer i){
			this.value = i;
		}
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[]{1,2,3,4,5,6};
		System.out.println("树遍历：");
		for(Integer r : arr){
			System.out.print(r+" ");
		}
		TreeDisplay treeDisplay = new TreeDisplay(arr);
		treeDisplay.DLRDisplay();
		treeDisplay.LDRDisplay();
		treeDisplay.LRDDisplay();
		treeDisplay.TopToBottomDisplay();
	}
}