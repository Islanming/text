public class SleepTest2 extends Thread {
    public void run() {
        loop();
    }

    public void loop() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "————》刚进入loop()ff");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("name="+name);
        }
        System.out.println(name + "————》离开loop()方法");
    }

    public static void main(String[] args) {
        SleepTest2 t = new SleepTest2();
        Thread.currentThread().setPriority(8);
        t.setName("my worker thread");
        t.start();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
        }
        t.loop();
    }
}