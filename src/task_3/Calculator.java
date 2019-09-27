package task_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

/**
* @author lan_wh
*
* @Description TODO
*
* @Title: Calculator.java 
*
* @date 创建时间：2019年9月26日 下午9:17:56
*
* @Package task_3
*
* @ClassName Calculator
*
* @version 1.0.0
*
*/

public class Calculator {
	private static String path = "D:/allsoft/临时/软件工程/Test1/src/task_3/problemsTxt/";
	private static String[] operates = { "+", "-", "*", "/" };
	public static void main(String[] args) {
		int n = Integer.valueOf(args[0]);
		String fileName = createFile();
		Vector<String>problems = new Vector<>();
		for( int i = 0 ; i < n ; i++ ){
			String problemString = creatProblem(fileName);
			problems.add(problemString);
		}
		writeProblem( fileName , problems);
	}
	/** 
	 * 产生一个 0 - end 的随机整数
	 * @param end
	 * @return
	 */
	public static int creatRandomNumber( int end ) {
		long seed = System.nanoTime();
		Random random = new Random(seed);
		int num = random.nextInt(end);
		return num;
	}
	/**
	 * 产生一个随机的 操作
	 * @return
	 */
	public static String creatRandomOperate() {
		int num = creatRandomNumber(4);
		return operates[num];
	}
	/**
	 * 返回运算符的数量
	 * @return
	 */
	public static int creatOperateNumber() {
		int num = creatRandomNumber(100);
		if( num % 2 == 1 ) return 2;
		else return 3;
	}
	/**
	 * 创造一个问题
	 */
	public static String creatProblem(String fileName) {
		String problemString = "";
		Stack<Integer>numbers = new Stack<Integer>();
		Stack<String>ops = new Stack<String>();
		int oprNumber = creatOperateNumber();
		
		boolean flag = false , mark = false;
		for( int i = 0 ; i < oprNumber ; i++ ){
			int x = 0;
			if( !mark ) x = creatRandomNumber( 100 );
			else{
				while(true){
					x = creatRandomNumber( 100 );
					if( x == 0 ) continue;
					if( numbers.peek() % x == 0 ) break;
				}
				mark = false;
			}
			numbers.add(x);
			problemString += String.valueOf(x);
			if( i != oprNumber - 1 ){
				String opr = creatRandomOperate();
				
				if( flag ){
					if( opr.equals("+") || opr.equals("-") ){
						if( ops.peek().equals("*") || ops.peek().equals("/") ){
							String oprs = ops.pop();
							int a = numbers.pop();
							int b = numbers.pop();
							int c = 0;
							if( oprs.equals("*") ) c = a * b;
							else if( oprs.equals("/") ) c = b / a;
							numbers.add(c);
						}
					}
				}
				ops.add(opr);
				problemString += opr;
				if( opr.equals("/") ) mark = true;
				flag = true;
			}
		}
		int result = solveProblem( numbers , ops );
		problemString += "=" + String.valueOf(result);
		return problemString;
		
	}
	/**
	 * 解决一个问题
	 * @param numbers
	 * @param ops
	 */
	public static int solveProblem( Stack<Integer>numbers , Stack<String>ops ) {
		int result = 0;
		while( numbers.size() > 1 ){
			String oprs = ops.pop();
			int a = numbers.pop();
			int b = numbers.pop();
			if( oprs.equals("*") ) result = b * a;
			else if( oprs.equals("/") ) result = b / a;
			else if( oprs.equals("+") ) result = b + a;
			else if( oprs.equals("-") ) result = b - a;
			numbers.add(result);
		}
		return result;
	}
	/**
	 * 创造一个文件
	 * @return
	 */
	public static String createFile() {
		SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmss");
        Date date = new Date();// 获取当前时间 
		String name = sdf.format(date);
		String filenameTemp = path + name + ".txt";
		File filename = new File(filenameTemp);
		try {
			filename.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filenameTemp;
	}
	/**
	 * 向文件中存入一个问题
	 * @param fileName
	 * @param problemString
	 */
	public static void writeProblem( String fileName , Vector<String> problemString ) {
//		System.out.println(problemString);
		OutputStream os = null;
		try {
			os = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(os);
		for( String string : problemString ) pw.println(string);
		pw.close();
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
