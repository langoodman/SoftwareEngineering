package task_2;

//import java.util.Vector;
/**
 * 素数输出
 * @author wh
 *
 */
public class Task2_3 {
	public static void main(String[] args) {
//		Vector<Integer>sum = new Vector<Integer>();
		int index = 0;
		for( int i = 2 ; i <= 20000 ; i++ ){
			boolean flag = true;
			for( int j = 2 ; j <= Math.sqrt(i) ; j++ ){
				if( i % j == 0 ){
					flag = false;
					break;
				}
			}
			if( flag ){
				System.out.print( i + " " );
				index++;
				if( index == 5 ){
					System.out.println();
					index = 0;
				}
			}
		}
		
	}
	/**
	 * 最耗时的是双重for循环 ，时间复杂度为 nlogn 即 20000log20000
	 * 优化
	 */
	public static void prime (){
	    int maxn = 20001;
	    boolean number[] = new boolean[maxn];
	    int prime[] = new int[maxn];
	    int j = 1;
	    prime[0] = 2;
	    number[4] = true;
	    for( int i = 3 ; i < maxn ; i += 2 ){
	        if(!number[i]) prime[j++] = i;
	        for( int k = 0 ; k < j && i * prime[k] < maxn ; k++ ){
	            number[i * prime[k]] = true;
	            if( i % prime[k] == 0 ) break;
	        }
	    }
	    for( int i = 1 ; i < j ; i++ ){
	        System.out.print(prime[i] + " ");
	        if( i % 5 == 0 ) System.out.println();
	    }
	}
}














