/**
 * @(#)Parking.java
 *
 *
 * @author 
 * @version 1.00 2017/1/15
 */


import java.util.concurrent.Semaphore;

public class Parking {
 	public static void main(String[] args) {
	  new Zwierze("Kot").start();
	  new Zwierze("Pies").start();
 	}
}

class Zwierze extends Thread {
 	private static final Semaphore semafor = new Semaphore(1, true);
 	private String nazwa;

 	public Zwierze (String nazwa) {
 		this.nazwa = nazwa;
 	}

 @Override
 public void run() {
 	while(true) {
	 try {	 	
	 	System.out.println(nazwa + " usiluje dostac sie do ogrodu");
	 	semafor.acquire();
	 	System.out.println(nazwa + " jest w ogrodzie");
	 	sleep((long)(Math.random()*10000));
	
	 } catch (InterruptedException e) {
	 	System.out.println("B³¹d");
	 } finally {
		 System.out.println(nazwa + " opuœci³ ogrod");
		 semafor.release();
	 }
	 try {	 	
	 	System.out.println(nazwa + " wraca do domu");
	 	System.out.println(nazwa + " odpoczywa do domu");
	 	sleep((long)(Math.random()*10000));
	
	 } catch (InterruptedException e) {
	 	System.out.println("B³¹d");
 	 }
  	}
 }
}
