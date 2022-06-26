public class ThreadBTest {
    public static void main(String[] args) {
        ThreadB b=new ThreadB();
        Thread.currentThread().setPriority(7);
        b.start();
        synchronized (b)
        {
            try {
                System.out.println(Thread.currentThread().getName()+"线程等待对象b的计算结果......");
                b.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("b对象计算的总和是："+b.total);
            System.out.println("main线程的优先级是："+Thread.currentThread().getPriority());
            System.out.println("b线程的优先级是："+b.getPriority());
        }
    }
}

class ThreadB extends Thread{
    int total;
    @Override
    public void run(){
        synchronized (this)
        {
            for (int i = 0; i < 101; i++) {
                total+=i;
            }
            notify();
        }
    }
}
