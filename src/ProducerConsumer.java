//生产者消费者问题
public class ProducerConsumer {
    public static void main(String[] args) {
        Stack s=new Stack();
        Producer p=new Producer(s);
        Consumer c=new Consumer(s);
        new Thread(p).start();
       //new Thread(p).start();
        //new Thread(p).start();
        new Thread(c).start();
    }
}

//产品：玩具兔
class Rabbit{
    int id;
    Rabbit(int id){
        this.id=id;
    }
    public String toString(){
        return ""+id;
    }
}

//栈（存放玩具兔的仓库）
class Stack{
    int index=0;
    Rabbit[] rabbitsArray=new Rabbit[6];
    public synchronized void push(Rabbit wt){     //进栈函数
        while (index==rabbitsArray.length){
            try {
                this.wait();                      //栈满，等待消费者消费
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notifyAll();                         //唤醒所有生产者进程
        rabbitsArray[index]=wt;                   //玩具兔进栈
        index++;
    }
    public synchronized Rabbit pop(){             //出栈函数
        while (index==0){
            try {
                this.wait();                      //栈空，等待生产者生产
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notifyAll();                         //唤醒所有消费者进程
        index--;                                  //消费出栈
        return rabbitsArray[index];
    }
}

//生产者
class Producer implements Runnable{
    Stack st=null;
    Producer(Stack st){
        this.st=st;
    }
    public void run(){
        for (int i = 0; i < 20; i++) {
            Rabbit r=new Rabbit(i);
            st.push(r);
            System.out.println("生产一个玩具兔，其id是："+r);
            try {
                Thread.sleep((int)(Math.random()*200));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

//消费者
class Consumer implements Runnable{
    Stack st=null;
    Consumer(Stack st){
        this.st=st;
    }
    public void run(){
        for (int i = 0; i < 20; i++) {
            Rabbit r=st.pop();
            System.out.println("消费一个玩具兔，其id是："+r);
            try {
                Thread.sleep((int)(Math.random()*1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}