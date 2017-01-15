import java.util.concurrent.*;

public class Litreki {
 	public static void main(String[] args) { 		
		Semaphore a = new Semaphore(1);
		Semaphore b = new Semaphore(0);
		Semaphore c = new Semaphore(0);
		
		new Literka("A", a, b).start();
		new Literka("B", b, c).start();
		new Literka("C", c, a).start();
 	}
}

class Literka extends Thread {
 	private String nazwa;
 	private Semaphore semafor1;
	private Semaphore semafor2;
	
 	public Literka (String nazwa, Semaphore semafor1, Semaphore semafor2) {
 		this.nazwa = nazwa;
 		this.semafor1 = semafor1;
 		this.semafor2 = semafor2;
 	}

 @Override
 public void run() {
 	while(true) {
	 try {	 	
	 	semafor1.acquire();
		 	System.out.println(nazwa);
		 	sleep((long)(Math.random()*1000));
		
	 } catch (InterruptedException e) {
	 	System.out.println("B³¹d");
	 } finally {
		semafor2.release();
	 }
 	} 
 }
}
