/**
 * @(#)TestBlokadRW.java
 *
 *
 * @author 
 * @version 1.00 2017/1/15
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
class StacjaPogodowa extends Thread {
	private Dane dane;

 	public StacjaPogodowa(Dane dane) {
 		this.dane = dane;
 	}
  	public void run() {
 		while(true) {
 			dane.odczyt();

 		try {
 			Thread.sleep((int)(Math.random()*2000));
 		} catch (InterruptedException e) {
 			System.out.println("B³¹d");
 			}
 		}
 	}
}
class Sensor extends Thread {
 	private Dane dane;

 	public Sensor(Dane dane) {
 		this.dane = dane;
 	}
 	public void run() {
 		while(true) {
 			dane.zapis(Math.random()*100);

 			try {
 				Thread.sleep(10000);
 			} catch (InterruptedException e) {
 				System.out.println("B³¹d");
 			}
 		}
 	}
}
class Dane {
 	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
 	private Lock rl = rwl.readLock();
 	private Lock wl = rwl.writeLock();
 	private double temperatura;

 public void zapis(double temperatura) {
 	wl.lock();
 	try {

 		System.out.println("Zapisuje now¹ temperaturê: " + temperatura);

 		try {
 			Thread.sleep(2000);
 		} catch (InterruptedException e) {
 			System.out.println("B³¹d");
 		}

 		this.temperatura = temperatura;
 	} finally {
 		wl.unlock();
 	}
 }

 public double odczyt() {
 rl.lock();
 try{
 System.out.println("Odczyt temperatury: " + temperatura);
 return temperatura;
 } finally {
 rl.unlock();
  }
 }
}
public class TestBlokadRW {

 public static void main(String[] args) {
	Dane dane = new Dane();

 	new Sensor(dane).start();
 	new StacjaPogodowa(dane).start();
 	}
}