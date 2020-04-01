/*
** 根据前序遍历中序遍历后序遍历构造二叉树
** 前序遍历 preorder = [3,9,20,15,7] 
** 中序遍历 inorder = [9,3,15,20,7]
** 后序遍历 postorder = [9,15,7,20,3]
*/
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MakeTree{
	public TreeNode buildTree(int[] preorder, int[] inorder){
		//⽤ HashMap 存储中序遍历，⽬的是查找⽅便。因为我们从前序遍历找到根节点后，还要寻找根节点在中序遍历的哪个位置
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length ; i++) {
			map.put(inorder[i], i);
		}
		return build(preorder, map, 0, preorder.length - 1, 0);
	}

	// 先序遍历，中序遍历，先序遍历开始，结束，中序遍历开始
	public TreeNode build(int[] preorder, HashMap<Integer, Integer> map, int preStart, int preEnd, int inStart){
		// 递归边界
		if (preStart > preEnd) {
			return null;
		}
		// 先序遍历第一位为根节点
		TreeNode root = new TreeNode(preorder[preStart]);
		// 找到中序遍历根节点，左半边为左子树，右半边为右子树
		int rootIndex = map.get(root.value);
		// len 代表左子树节点个数
		int len = rootIndex - inStart;
		root.left = build(preorder, map, preStart + 1, preStart + len, inStart);
		root.right = build(preorder, map, preStart + len + 1, preEnd, rootIndex + 1);
		return root;
	}

	public TreeNode buildTreePost(int[] postorder, int[] inorder){
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++){
			map.put(inorder[i], i);
		}
		return buildPost(postorder, map, 0, postorder.length - 1, 0);
	}

	public TreeNode buildPost(int[] postorder, HashMap<Integer, Integer> map, int postStart, int postEnd, int inStart){
		if (postStart > postEnd){
			return null;
		}
		TreeNode root = new TreeNode(postorder[postEnd]);
		int rootIndex = map.get(root.value);
		int len = rootIndex - inStart;
		root.left = buildPost(postorder, map, postStart, postStart + len - 1, inStart);
		root.right = buildPost(postorder, map, postStart + len, postEnd - 1, rootIndex + 1);
		return root;
	}

	// 层序遍历显示
	public List<Integer> TopToBottomDisplay(TreeNode root){
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

	public class TreeNode{
		int value;
		TreeNode left;
		TreeNode right;
		public TreeNode(int i){
			this.value = i;
		}
	}
	public static void main(String[] args) {
		int[] preorder = new int[]{3,9,20,15,7};
		int[] inorder = new int[]{9,3,15,20,7};
		int[] postorder = new int[]{9,15,7,20,3};
		System.out.println("先序遍历：");
		for(Integer r : preorder){
			System.out.print(r+" ");
		}
		System.out.println("\n中序遍历：");
		for(Integer r : inorder){
			System.out.print(r+" ");
		}
		System.out.println("\n后序遍历：");
		for(Integer r : postorder){
			System.out.print(r+" ");
		}
		MakeTree tree = new MakeTree();
		TreeNode root = tree.buildTree(preorder, inorder);
		tree.TopToBottomDisplay(root);
		TreeNode rootPost = tree.buildTreePost(postorder, inorder);
		tree.TopToBottomDisplay(rootPost);
	}
}