package task_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class WF {

	/**
	 * -c <file> 输出某个英文文本文件中 26 字母出现的频率，由高到低排列，并显示字母出现的百分比，精确到小数点后面两位
	 * -f <file> 输出文件中所有不重复的单词，按照出现次数由多到少排列，出现次数同样多的，以字典序排列
	 * -d <directory> 指定文件目录，对目录下每一个文件执行  wf.exe -f <file> 的操作
	 * -d -s <directory>  同上， 但是会递归遍历目录下的所有子目录
	 * -f <file> -n 参数，输出出现次数最多的前 n 个单词
	 * -x <stopwordfile> -f <file> 输出文件中所有不重复的单词，按照出现次数由多到少排列，出现次数同样多的，以字典序排列,但是不会统计stopwordfile中的单词
	 *  -p < file > < number >参数 < number > 说明要输出多少个词的短语，并按照出现频率排列。同一频率的词组， 按照字典序来排列
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
	
	/**
	 * 获取txt文件中的文本内容
	 * @param fileName
	 * @return
	 */
	public static String fileText( String fileName ){
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
		return text;
	}
	
	/**
	 * 获取文件的后缀名
	 * @param file
	 * @return
	 */
	public static String getFileExtension(File file) {
		String fileName = file.getName();
		if(fileName.lastIndexOf(".") != -1)
			return fileName.substring(fileName.lastIndexOf("."));
		else {
			return null;
		}
	}
	
	/**
	 * 完成 -f 的功能
	 * @param text
	 */
	public static void printF( String text ) {
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
	
	/**
	 * 递归查找文件
	 * @param file
	 */
	public static Vector<String>needFilesPath = new Vector<String>();
	private static void findAllFiles(File file , String filePath , Vector<String>needFilesPath){
		File[] fs = file.listFiles();
		for(File f:fs){
			if(f.isDirectory()){
				findAllFiles( f , filePath + "\\" + f.getName() + "\\" , needFilesPath );//若是目录，则递归打印该目录下的文件
			}
			if(f.isFile()){
				needFilesPath.add(filePath + f.getName());//若是文件，直接保存路径
			}
		}
	}
	
	/**
	 * 完成 -n 的功能
	 * @param text
	 */
	public static void printN( String text , Integer n ) {
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
        int index = 0;
        for( Map.Entry< String , Integer > word : printWords){ 
        	System.out.println( word.getKey()+":" + word.getValue() ); 
        	index++;
        	if( index >= n ) break;
        }
	}
	
	public static void main(String[] args) {
		if( args.length < 2 ){
			System.out.println("命令不正确!");
		}
		else{
			String option = args[0]; // 操作
	        
			if( option.equals("-c") ){
				String fileName = args[1]; // 文件名字
				String text = fileText( fileName );// text 为文件中的内容
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
			else if( option.equals("-f") && args.length == 2 ){
				String fileName = args[1]; // 文件名字
				String text = fileText( fileName );// text 为文件中的内容
		        printF(text);
			}
			else if( option.equals("-f") && args.length > 2 ){
				String fileName = args[1]; // 文件名字
				String text = fileText( fileName );// text 为文件中的内容
				if( Arrays.asList(args).contains("-n") ){
					int i = 0;
					for( i = 0 ; i < args.length ; i++ ){
						if( args[i].equals("-n") ) break;
					}
					Integer n = Integer.valueOf(args[i + 1]);
			        printN(text , n );
				}
				
			}	
			else if( option.equals("-d") && !args[1].equals("-s") ){
				String directoryPath = args[1];
				File file = new File(directoryPath);//获取其file对象
				File[] files = file.listFiles();//遍历path下的文件和目录，放在File数组中
				for( File f : files ){//遍历File[]数组
					if(!f.isDirectory() && getFileExtension(f).equals(".txt") ){ // 后缀名为 txt 文件
						String text = fileText(directoryPath + f.getName());
						System.out.println(f.getName() + "的单词统计功能如下:");
						if( Arrays.asList(args).contains("-n") ){
							int i = 0;
							for( i = 0 ; i < args.length ; i++ ){
								if( args[i].equals("-n") ) break;
							}
							Integer n = Integer.valueOf(args[i + 1]);
							printN(text , n);
						}
						else printF(text);
					}
				}
			}
			else if( option.equals("-d") && args[1].equals("-s") ){
				String directoryPath = args[2];
				File file = new File(directoryPath);//获取其file对象
				String filePath = directoryPath;
				findAllFiles(file , filePath , needFilesPath);
				for( String filesPath : needFilesPath ){
					String text = fileText(filesPath);
					System.out.println(filesPath + "的单词统计功能如下:");
					if( Arrays.asList(args).contains("-n") ){
						int i = 0;
						for( i = 0 ; i < args.length ; i++ ){
							if( args[i].equals("-n") ) break;
						}
						Integer n = Integer.valueOf(args[i + 1]);
						printN(text , n);
					}
					else printF(text);
				}
			}
			else if( option.equals("-x") ){
				String stopFilePath = args[1]; //黑名单
				String fileName = args[3]; // 文件名字
				String text = fileText( fileName );// text 为文件中的内容
				String stopText = fileText(stopFilePath);
				
				text = text.replaceAll("[^0-9a-zA-Z]+", " ");
				stopText = stopText.replaceAll("[^0-9a-zA-Z]+", " ");
				
				String words[] = text.split("\\s+");//分割一个或多个空格
				String stopWords[] = stopText.split("\\s+");//分割一个或多个空格
				
				Map< String , Integer > count = new TreeMap< String , Integer >();
				Map< String , Integer > flagStop = new TreeMap< String , Integer >();
				
				for( String stopWord : stopWords ){
					flagStop.put(stopWord, 1);
				}
				
				for( String word : words ){
					if( flagStop.get(word) != null ) continue;
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
		        int index = 0;
		        int i = 0 , n = -1;
		        if( Arrays.asList(args).contains("-n") ){
					for( i = 0 ; i < args.length ; i++ ){
						if( args[i].equals("-n") ) break;
					}
					n = Integer.valueOf(args[i + 1]);
				}
		        for( Map.Entry< String , Integer > word : printWords){ 
		        	System.out.println( word.getKey()+":" + word.getValue() ); 
		        	index++;
		        	if( n != -1 ){
						if( index >= n ) break;
					}
		        }
			}			
			else if( option.equals("-p") ){
				String fileName = args[1]; // 文件名字
				String text = fileText( fileName );// text 为文件中的内容
				Integer n = Integer.valueOf(args[2]);
				
				Map< String , Integer > count = new TreeMap< String , Integer >();
				text = text.replaceAll("[^0-9a-zA-Z\\s+]+", " ThisIsASplitToGet ");
//				System.out.println(text);
				String words[] = text.split("\\s+");//分割一个或多个空格
				int sum = 0;
				for( int i = 0 ; i < words.length ; i++ ){
					String sentence = "";
					for( int j = i ; j < n + i && j < words.length ; j++ ){
						sentence += words[j] + " ";
					}
					if( sentence.indexOf("ThisIsASplitToGet") >= 0 ) continue;
					else{
						sum++;
						int cnt = 1;
						if( count.get(sentence) != null ) cnt = count.get(sentence) + 1;
						count.put( sentence , cnt );
					}
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
		        
		        int index = 0;
		        int ii = 0 , nn = -1;
		        if( Arrays.asList(args).contains("-n") ){
					for( ii = 0 ; ii < args.length ; ii++ ){
						if( args[ii].equals("-n") ) break;
					}
					nn = Integer.valueOf(args[ii + 1]);
				}
		        for( Map.Entry< String , Integer > word : printWords){ 
		        	index++;
		        	System.out.println( word.getKey()+":" + String.format("%.5f", (double)word.getValue() / (double)sum) ); 
		        	if( nn != -1 ){
						if( index >= nn ) break;
					}
		        }
			}
			
		}
		
	}

}
