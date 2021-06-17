# 多线程与并发

# 一、多线程

## 1. 基本概念：程序、进程、线程

### 1.1 程序（program）

是为了完成特定任务、用某种语言编写的一组指令的集合。即指一段静态的代码，静态对象。

### 1.2 进程（process）

程序的一次执行过程，系统运行程序的基本单位，因此进程是动态的；比如打开一个软件就是一次进程（所以进程有生命周期），进程是资源分配的单位，系统在运行时会为每个进程分配不同的内存区域。

![image.png](https://cdn.nlark.com/yuque/0/2021/png/12759906/1623855740687-fec8a091-cbcb-44db-a992-8892f5c84c4d.png)

### 1.3 线程（thread）

线程：是轻量级的进程，但是线程是比进程更小的执行单位。一个进程在执行过程中可以产生多个线程。与进程不同的是，线程作为调度和执行的单位，线程之间共享进程的**堆**和**方法区**；但是每个线程又有自己的**程序计数器**，**虚拟机栈**，**本地方法栈**。

# 二、JUC（并发）
