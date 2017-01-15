import java.util.concurrent.Semaphore;

public class Zwierzaki {
 	public static void main(String[] args) {
 		for(int i = 1; i <= 10; i++) {
 			new Goscie("G"+i).start();
 			try {
 				Thread.sleep(100);
 			} catch(InterruptedException e) {
 				System.out.println("B³¹d");
 			}
 		}
 	}
}

class Goscie extends Thread {
 	private static final Semaphore semafor = new Semaphore(2, true);
 	private String nazwa;

 	public Goscie(String nazwa) {
 		this.nazwa = nazwa;
 		System.out.println("Goœæ " + nazwa + " przyby³ do hotelu. Aktualna dostêpnoœæ pokoi: " + semafor.availablePermits() + " Aktualna kolejka: " + semafor.getQueueLength());
 }

 @Override
 public void run() {
 try {
 semafor.acquire();
 System.out.println("Goœæ: " + nazwa + " zaj¹³ pokój");
 sleep((long)(Math.random()*10000));

 } catch (InterruptedException e) {
 System.out.println("B³¹d");
 } finally {
 System.out.println("Goœæ: " + nazwa + " opuœci³ pokój");
 semafor.release();
 }
 }
}
