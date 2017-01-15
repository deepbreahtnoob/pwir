/**
 * @(#)Parking.java
 *
 *
 * @author 
 * @version 1.00 2017/1/15
 */


import java.util.concurrent.*;

public class Parking {
 	public static void main(String[] args) {
 		
		for(int i=1; i <=100 ;i++) {
			new Samochod("S"+i).start();			
			try {	 	
			 	Thread.sleep((long)(Math.random()*500));		
			} catch (InterruptedException e) {
				System.out.println("B³¹d");
		 	}
		}
 	}
}

class Samochod extends Thread {
 	private static final Semaphore semafor = new Semaphore(20, true);
 	private String nazwa;

 	public Samochod (String nazwa) {
 		this.nazwa = nazwa;
 	}
 	public static Semaphore getSemaphore(){
 		return semafor;
 	}

 @Override
 public void run() {
 	
 	 boolean flaga = false;
 	 
	 try {	 	
	 	System.out.println(nazwa + " jedzie na parking...");
	 	if (semafor.tryAcquire()) {
		 	System.out.println(nazwa + " zaparkowa³");
		 	sleep((long)(Math.random()*1000));
		} else {
			if(Math.random() < 0.75) {
				System.out.println(nazwa + " ustawi³ siê w kolejce");
				semafor.acquire();
				System.out.println(nazwa + " zaparkowa³");
				sleep((long)(Math.random()*1000));
				flaga = true;
			} else {
				System.out.println(nazwa + " pojecha³ na inny parking");
				flaga = false;
			}
		}
	
	 } catch (InterruptedException e) {
	 	System.out.println("B³¹d");
	 } finally {
		 System.out.println(nazwa + " opuszcza parking");
		 if(flaga){semafor.release();};
	 }
 }
}
