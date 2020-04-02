/*
**输⼊⼀个整数数组，判断该数组是不是某⼆叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出 No。
**假设输⼊的数组的任意两个数字都互不相同
*/
public class VerifySequenceOfBST{
	public static boolean verify(int[] sequence){
		if (sequence == null || sequence.length < 1) {
			return false;
		}
		return judge(sequence, 0, sequence.length - 1);
	}
	private static boolean judge(int[] sequence, int left, int right){
		// 递归结束条件 只有一个节点
		if (left >= right) {
			return true;
		}
		// 最右节点相当于根节点
		int t = sequence[right];
		// 用来记录序列中第一个比根节点大的下标
		int index = right;
		for (int i = left; i <= right - 1; i++) {
			// 找到根节点的右孩子
			if (sequence[i] > t){
				index = i;
				i++;
				// 如果右子树中有比根节点还小的树，则不成立
				while(i <= right - 1){
					if (sequence[i] < t){
						return false;
					}
					i++;
				}
			}
		}
		// 递归检查左右子树
		return judge(sequence, left, index - 1) && judge(sequence, index, right - 1);
	}

	public static void main(String[] args) {
		int[] postorder = new int[]{9,15,7,20,3};
		boolean isTrue = VerifySequenceOfBST.verify(postorder);
		System.out.print("是不是？"+isTrue);
	}
}