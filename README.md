# Task_4 WF 分词功能运行介绍

+ 重新编译方式：在  <strong>Test1/src/task_4/  </strong>下运行:  <strong>javac -encoding UTF-8 WF.java  </strong>
+ 运行目录：<strong>Test1/src/  </strong>
+ -c < file > ：
  + 输出某个英文文本文件中 26 字母出现的频率，由高到低排列，并显示字母出现的百分比，精确到小数点后面5位
  + 例如：<strong>java task_4/WF -c task_4/词频测试数据集/"1.I Have a Dream.txt"</strong>
+ -f < file > 
  + 输出文件中所有不重复的单词，按照出现次数由多到少排列，出现次数同样多的，以字典序排列
  + 例如：<strong>java task_4/WF -f task_4/词频测试数据集/"1.I Have a Dream.txt"</strong>

+ -d < directory > 
  + 指定文件目录，对目录下每一个文件执行  -f <file> 的操作
  + 例如：<strong>java task_4/WF -d task_4/词频测试数据集/</strong>
+ -d -s < directory > 
  - 递归遍历目录下的所有子目录，目录下每一个文件执行  -f <file> 的操作
  - 例如：<strong>java task_4/WF -d -s task_4/词频测试数据集/</strong>
+ -f < file > -n 参数
  + 输出出现次数最多的前 n 个单词
  + 例如：<strong>java task_4/WF -f task_4/词频测试数据集/"1.I Have a Dream.txt" -n 10</strong>

+ -x < stopwordfile > -f < file > 
  + 输出文件中所有不重复的单词，按照出现次数由多到少排列，出现次数同样多的，以字典序排列,但是不会统计stopwordfile中的单词
  + 例如：<strong>java task_4/WF -x task_4/stopWordsFiles/"1.txt" -f task_4/词频测试数据集/"1.I Have a Dream.txt"</strong>
+ -p < file > < number >
  + 参数 < number > 说明要输出多少个词的短语，并按照出现频率排列。同一频率的词组， 按照字典序来排列。
  + 例如：<strong>java task_4/WF -p task_4/词频测试数据集/"1.I Have a Dream.txt" 3 -n 4</strong>

+ -v  < verb file >
  - 把动词的各种变形都归为它的原型来统计。
  - 例如：<strong>java task_4/WF -f task_4/词频测试数据集/"1.I Have a Dream.txt" -v task_4/verbWordsFiles/"1.txt" -n 4</strong>