package task_4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WF {

	/**
	 * -c 输出某个英文文本文件中 26 字母出现的频率，由高到低排列，并显示字母出现的百分比，精确到小数点后面两位
	 * 
	 * @param args
	 */
	public static class Letter implements Comparable<Letter> {

		private double appear;
		private char oneLetter;
		
		public double getAppear() {
			return appear;
		}

		public void setAppear(double appear) {
			this.appear = appear;
		}

		public char getOneLetter() {
			return oneLetter;
		}

		public void setOneLetter(char oneLetter) {
			this.oneLetter = oneLetter;
		}
		public Letter(char oneLetter , double appear) {
			super();
			this.appear = appear;
			this.oneLetter = oneLetter;
		}
		public Letter() {
			
		}
		@Override
		public int compareTo(Letter l) {
			if( l.getAppear() == this.getAppear() ){
				if( this.getOneLetter() < l.getOneLetter() ) return 1;
			}
			else{
				if( this.getAppear() < l.getAppear() ) return 1;
				else return -1;
			}
			return 0;
		}

		@Override
		public String toString() {
			return this.getOneLetter() + " " + String.format("%.5f", this.getAppear() );
		}
		
	}
	public static void main(String[] args) {
		if( args.length < 2 ){
			System.out.println("命令不正确!");
		}
		else{
			String option = args[0]; // 操作
			String fileName = args[1]; // 文件名字
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String text = "" , line;
	        try {
				while ( ( line = in.readLine() ) != null ) {
					text += line;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	        // text 为文件中的内容
	        
			if( option.equals("-c") ){
				int sum = 0; // 排除非字母
				int cnt[] = new int[26];//统计每个字母出现的字数
				for( int i = 0 ; i < text.length() ; i++ ){
					char letter = text.charAt(i);
					if( letter >= 'A' && letter <= 'Z' ){
						letter += Math.abs('A' - 'a');
					}
					
					if( letter >= 'a' && letter <= 'z' ){
						sum++; // 计算字母的总数 排除非字母
						cnt[ letter - 'a' ]++;
					}
				}
				List<Letter> letters = new ArrayList<>();
				for( int i = 0 ; i < 26 ; i++ ){
					double p = (double)cnt[i] / (double)sum;
					char ll = (char) (i + 'a');
					letters.add(new Letter( ll , p ));
					
				}
				Collections.sort(letters);
				for( Letter letter : letters ){
					System.out.println(letter.toString());
				}
			}
			else if( option.equals("-f") ){
				text = text.replaceAll("[^0-9a-zA-Z]+", " ");
				String words[] = text.split("\\s+");//分割一个或多个空格
				Map< String , Integer > count = new TreeMap< String , Integer >();
				for( String word : words ){
					int cnt = 1;
					if( count.get(word) != null ) cnt = count.get(word) + 1;
					count.put( word , cnt );
				}
				
		        List<Map.Entry<String, Integer>> printWords = new ArrayList<Map.Entry<String, Integer>>(count.entrySet());
		        Collections.sort(printWords, new Comparator<Map.Entry<String, Integer>>() {
		            @Override
		            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
		                if(o1.getValue().compareTo(o2.getValue()) == 0){
		                    return o1.getKey().compareTo(o2.getKey());
		                }
		                return o2.getValue().compareTo(o1.getValue());
		            }
		        });
		        for( Map.Entry< String , Integer > word : printWords){ 
		        	System.out.println( word.getKey()+":" + word.getValue() ); 
		        }
			}
			else if( option.equals("-d") ){
				
			}
		}
		
	}

}
