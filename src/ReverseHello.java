/**
 * Created by Olivi on 29-09-2016.
 */
public class ReverseHello implements Runnable {

    public static int count = 0;
    public static void main(String[] args) {
        Thread t = new Thread(new ReverseHello());
        t.start();
    }
    public ReverseHello() {
        count++;
    }

    @Override
    public void run() {
        if (count <= 49) {
            Thread t = new Thread(new ReverseHello());
            t.start();
            t.yield();
            System.out.println("Hello from thread :" + count);
        }
    }
}
