#Sink a Dot Com游戏设计实现

###游戏描述

- 在7x7的格子中击落.com的词条,这些词条占用3个格子游戏场景如下图所示

![game layer1](https://raw.githubusercontent.com/fantasyming/Sink-a-Dot-Com/master/image/1.PNG)

- 在游戏中(这个版本不使用GUI)需要在command-line中输入格子的位置来猜测是否是词条所在的位置(例如`A3`，`B5`)
1. 如果击中,输出`hit`
2. 如果没有击中,输出`miss`
3. 如果把词条的三个格子都击中,输出`kill`

- 游戏过程模拟

![p2](https://raw.githubusercontent.com/fantasyming/Sink-a-Dot-Com/master/image/2.PNG)

###游戏设计

####设计简单游戏流程
#####开始游戏
- 创建3个.com的词条
- 把词条放到网格中

#####玩家猜词条
- 玩家开始猜词条的位置(比如输入`A5`,`B3`) 
- 检测玩家输入结果,看是3种情况的哪一种
   A.  如果是`hit`,那么把击中的网格删除 (`A5`,`B3`)  
   B. 如果是`kill`,把整个.com词条删除
   C. A,B两项交替进行
   
#####游戏结束
- 由玩家猜测次数来决定玩家的分数

#####游戏流程图

![p3v](https://raw.githubusercontent.com/fantasyming/Sink-a-Dot-Com/master/image/3.PNG) 
 
####类的设计
#####设计原则
- 知道每个类应该做什么
- 列出`instance variables and methods`
- 为每个方法写`precode`
- 为每个方法写`test code`
- 实现类
- 测试类
- 调试和重构(`reimplement`)
#####Pre Code
- 变量声明(`instance variable declarations`)
- 方法声明(`method declarations`)
- 方法逻辑(`method logic` )
#####SimpleDotCom method描述
#####声明
- `locationCells`
  声明一个`int`数组保存网格的位置
- `numOfHits`
  击中词条数目
- `checkYourself()`
  从用户输入获取字符串,返回`hit`,`miss`,`kill`
- `setLocationCells()`
  设置词条位置
#####方法
- `String checkYourself(String userGuess)`
1. 获取用户猜测的次数字符串`String userGuess`
2. 把字符串转化为`int`
3.  从`locationCells`取猜的的位置
4.  如果猜中
5.  `numOfHits`+1
6.  如果`numOfHits`是3,返回`kill`
7.  否则返回`hit`
8.  没猜中,返回`miss`
- `void setLocationCells(int[] cellLocations)`
1. 从整数数组得到网格位置
2. 把参数赋值给`locationCells`
#####test code
- 为SimpleDotCom写的测试代码
```
public class SimpleDotComTestDrive{
	public static void main(String[] args){
		SimpleDotCom dot = new SimpleDotCom();

		int[] locations = {2,3,4};
		dot.setLocationCells(locations);

		String userGuess = "2";//输入
		String result = dot.checkYourself(userGuess);
		String testResult = "failed";
		if(result.equals("hit")){
			testResult = "passed"//验证输入
		}
		System.out.println(testResult);

	}
}
```

####checkYourself实现
```
public class SimpleDotCom{
		public int[] locationCells;
		public int numOfHits;

		public void setLocationCells(int[] celllocations){
			locationCells = celllocations;
		} 

		public String checkYourself(String stringguess){
			int guess = Integer.parseInt(stringguess);

			String result = "miss";

			for(int cell : locationCells){
				if(cell == guess){
					result = "hit";
					numOfHits++;
					break;
				}
			}

			if(numOfHits == locationCells.length){
				result = "kill";
			}

			return result;
		}
}
```
####SimpleDotGame 描述
- 声明一个变量`numOfGuess`,初始值为0
- `SimpleDotCom`实例
- 生成0-4之间的随机数作为词条的位置
- 生出一个大小为3的数组放随机数
- 调用	`setLocationCells()`
- 声明一个变量`isAlive`,表明游戏状态

```
public class SimpleDotComGame{

	public static void main(String[] args){
		int numOfGuess = 0;
		boolean isAlive = true;
		int randomNum = (int)(Math.random()*5);
		int[] locations = {randomNum,randomNum+1,randomNum+2};

		GameHelper helper = new GameHelper();
		SimpleDotCom dot = new SimpleDotCom();
		dot.setLocationCells(locations);
		

		while(isAlive == true){
			String guess = helper.getUserInput("enter a number");
			String result = dot.checkYourself(guess);
			numOfGuess++;
			if(result.equals("kill")){
				isAlive = false;
				System.out.println("You took "+numOfGuess+" guesses");
			}
		}
	}
}
```

```
import java.io.*;

public class GameHelper{
	public String getUserInput(String prompt){
		String inputLine = null;
		System.out.print(prompt+" ");
		try{

			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			if(inputLine.length() == 0)
				return null;
		}catch(IOException e){
			System.out.println("IOException"+e);
		}
		return inputLine;
	}
}
```
####程序Bug
- 运行时输入同一个数都击中
![p4](https://raw.githubusercontent.com/fantasyming/Sink-a-Dot-Com/master/image/4.PNG) 

- 这段代码说明，每次位置和猜测的数一直就会输出`hit`,并且`numOfHits`也在变

```
        for(int cell : locationCells){
			if(cell == guess){
				result = "hit";
					numOfHits++;
					break;
				}
		}
```
- 解决思路
1. 在生成一个数组,每个位置对应数的位置,初始为`false`,每次击中置为`true`,第二次在访问先看对应位置是不是false，如果不是那么表示这个已经被`hit`,就不能输出`hit
![p5](https://raw.githubusercontent.com/fantasyming/Sink-a-Dot-Com/master/image/5.PNG) 

2. 直接在原数组上改,`hit`过后的位置置为`-1`
![p6](https://raw.githubusercontent.com/fantasyming/Sink-a-Dot-Com/master/image/7.PNG) 

- 最终版本

```
import java.util.*;

public class DotComBust{

	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
	private int numOfGuesses = 0;

	private void setUpGame(){
		//first make some dot and give them locations
		DotCom one = new DotCom();
		one.setName("Pet.com");
		DotCom two = new DotCom();
		one.setName("eToys.com");
		DotCom three = new DotCom();
		one.setName("Go2.com");
		dotComList.add(one);
		dotComList.add(two);
		dotComList.add(three);

		System.out.println("Your goal is to sink three dot coms.");
		System.out.println("Pet.com,eToys.com,Go2.com");
		System.out.println("Try to sink them all in the fewest");

		for(DotCom dotComToSet : dotComList){
			ArrayList<String> newLocation = helper.placeDotCom(3);
			dotComToSet.setLocationCells(newLocation);
		}
	} 

	private void startPlaying(){
		while(!dotComList.isEmpty()){
			String userGuess = helper.getUserInput("Enter a guess");
			checkUserGuess(userGuess);
		}
		finishGame();
	}

	private void checkUserGuess(String userGuess){
		numOfGuesses++;
		String result = "miss";

		for(DotCom dotComToTest : dotComList){
			result = dotComToTest.checkYourself(userGuess);
			if (result.equals("hit")) {
				break;
			}
			if(result.equals("kill")){
				dotComList.remove(dotComToTest);
				break;
			}
		}
		System.out.println(result);
	}

	private void finishGame(){
		System.out.println("all dot dead");
	}

	public static void main(String[] args){
		DotComBust game = new DotComBust();
		game.setUpGame();
		game.startPlaying();
	}
}
```