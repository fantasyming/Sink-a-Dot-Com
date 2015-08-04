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
 开始游戏
- 创建3个.com的词条
- 把词条放到网格中

玩家猜词条
- 玩家开始猜词条的位置(比如输入`A5`,`B3`) 
- 检测玩家输入结果,看是3种情况的哪一种
   A.  如果是`hit`,那么把击中的网格删除 (`A5`,`B3`)  
   B. 如果是`kill`,把整个.com词条删除
   C. A,B两项交替进行
   
游戏结束
- 由玩家猜测次数来决定玩家的分数

游戏流程图设计如下

![p3v](https://raw.githubusercontent.com/fantasyming/Sink-a-Dot-Com/master/image/3.PNG) 
 
####类的设计
设计原则
- 知道每个类应该做什么
- 列出`instance variables and methods`
- 为每个方法写`precode`
- 为每个方法写`test code`
- 实现类
- 测试类
- 调试和重构(`reimplement`)
