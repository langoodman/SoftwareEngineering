package task_2;

import java.util.Scanner;

/**
 * 练习数值计算
 * @author wh
 *
 */
public class Task2_2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int x[] = new int[n];
		for( int i = 0 ; i < n ; i++ ) x[i] = scanner.nextInt();
		
		int sum = 0 , ans = java.lang.Integer.MIN_VALUE ;
		for( int i = 0 ; i < n ; i++ ){
			sum += x[i];
			ans = Math.max( sum , ans );
			if( sum < 0 ){
				sum = 0;
			}
		}
		System.out.println(ans);
		scanner.close();
	}
}
