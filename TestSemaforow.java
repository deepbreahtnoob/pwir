import java.util.concurrent.Semaphore;

public class Zwierzaki {
 	public static void main(String[] args) {
 		for(int i = 1; i <= 10; i++) {
 			new Goscie("G"+i).start();
 			try {
 				Thread.sleep(100);
 			} catch(InterruptedException e) {
 				System.out.println("B��d");
 			}
 		}
 	}
}

class Goscie extends Thread {
 	private static final Semaphore semafor = new Semaphore(2, true);
 	private String nazwa;

 	public Goscie(String nazwa) {
 		this.nazwa = nazwa;
 		System.out.println("Go�� " + nazwa + " przyby� do hotelu. Aktualna dost�pno�� pokoi: " + semafor.availablePermits() + " Aktualna kolejka: " + semafor.getQueueLength());
 }

 @Override
 public void run() {
 try {
 semafor.acquire();
 System.out.println("Go��: " + nazwa + " zaj�� pok�j");
 sleep((long)(Math.random()*10000));

 } catch (InterruptedException e) {
 System.out.println("B��d");
 } finally {
 System.out.println("Go��: " + nazwa + " opu�ci� pok�j");
 semafor.release();
 }
 }
}
