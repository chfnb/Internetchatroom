import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test implements Runnable {
    public void f(){
        Thread t2 = new Thread (new B ());
        while (true){
            try {
                System.out.println("A");
                Thread.sleep (5000);
                if ( !t2.isAlive () ){
                    t2.start ();
                }


            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
    }
    @Override
    public void run() {
        f ();
    }
    public static void main(String[]args){
        new Thread (new Test ()).start ();
        ExecutorService es = Executors.newFixedThreadPool (5);

    }
}
class B implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("B");
            try {
                Thread.sleep (3000);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
    }
}