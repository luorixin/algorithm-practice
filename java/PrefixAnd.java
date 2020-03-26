/* 前缀和
有⼀个班级有 n 个人，给出 n 个元素，第 i 个元素代表 第 i 位同学的考试成绩，接下进⾏ m 次 问，每次询问给出⼀个数值t ，表示第 t 个同学，
然后需要我们输出第 t 个同学的成绩超过班级百 分之几的⼈，
百分数 p 可以这样算:p = (不超过第 t 个同学分数的⼈数 ) / n * 100%。
输出的时候 保留到⼩数点后 6 位，并且需要四舍五⼊。
输⼊入描述:
第⼀行 输入两个数 n 和 m，两个数以空格隔开，表示 n 个同学和 m 次询问。
第⼆行 输入 n 个数值 ni，表示每个同学的分数，
第三⾏ 输入 m 个数值 mi，表示每次询问是询问第⼏个 同学。(注意，这⾥里里 2<=n，m<=100000，0<=ni<=150，1<=mi<=n)
输出描述:
输出 m ⾏，每行输出⼀个百分数 p，代表超过班级百分之⼏几的⼈人。 示例例1:
输⼊入 :
3 2
50 60 70
1 2
输出 33.333333% 66.666667%
*/
import java.util.Scanner;
public class PrefixAnd{

	private int[] a;
	private int[] m;

	public PrefixAnd(int a[], int m[]){
		this.a = a;
		this.m = m;
	}

	public void doSum(){
		int n = a.length;
		int[] arr = new int[151];
		// 先统计分数为i的有多少人
		for (int i = 0; i < n; i++){
			arr[a[i]]++;
		}
		// 构造前缀和
		for (int i = 1; i < 151; i++){
			arr[i] = arr[i] + arr[i-1];
		}

		// 进行m次询问
		for (int i = 0; i < m.length; i++) {
			// 第1个同学，索引为0
			int score = a[m[i]-1];
			int sum = arr[score];
			System.out.println(score);
			System.out.printf("%.6f\n", sum * 1.0 / n * 100);
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();

		int[] nArr = new int[n];
		int[] mArr = new int[m];
		for (int i = 0; i < n; i++){
			nArr[i] = in.nextInt();
		}
		for (int j = 0; j < m; j++){
			mArr[j] = in.nextInt();
		}

		PrefixAnd prefixAnd = new PrefixAnd(nArr, mArr);
		prefixAnd.doSum();

	}
}