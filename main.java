package cn.hdu.workthread.day1;

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorkers();
        // 下一步, 如何根据系统动态的控制Worker的数量.
        // 第二步: Request的数量, 第五章 Producer-Consumer模式
        new ClientThread("Alice", channel).start();
        new ClientThread("Bobby", channel).start();
        new ClientThread("Chris", channel).start();
    }
}

// 调用和执行分离有什么意义:
// 1. 提高响应速度
// 2. 控制执行顺序(调度), 分离之后, 可以给Request设置优先级, 并控制Channel角色将Request角色传递给Worker角色的顺序.
// 3. 可以取消和反复执行. 因为调用的是Request角色对象, 既可以保存也可以重复执行.
// 4. 通往分布式之路.(通过网络把Request角色).

// java.lang.Runnable接口有时会被用作Worker Thread模式的Request角色
// Runnable接口的意义: 可以作为方法参数传递, 可以被放入到队列, 可以跨越网络传递, 也可以保存至文件中.

// 多态的Request角色
// ClientThread传递给Channel的只是Request实例
// 但是WorkerThread 并不知道Request类的详细信息. 只是单纯的接受, 然后调用execute方法而已.
// 也就是用Request的子类传递给WorkerThread, 同样可以正常调用execute方法.
// 可以使用多态.

//    实验: https://www.cnblogs.com/alinainai/p/10409606.html
//    wait是Object中的方法，而sleep则是Thread中的方法。
//    sleep可以在任何地方使用，而wait只可以在synchronized方法或synchronized块中使用。
//    sleep方法只会让出当前线程持有的时间片，而wait方法除了让出时间片还会让出当前线程持有的锁。