import java.util.concurrent.*;


class Klient implements Runnable {
  private String nazwa;
  public Klient(String nazwa){
    this.nazwa = nazwa;
  }
  public void czynnosc(String napis, int czas) {
    
    System.out.println("Klient" + nazwa + napis);
    try {
     Thread.sleep((int)(Math.random()*czas));
    } catch (InterruptedException e) {
     System.out.println("Blad");
    }
  }

  public void run() {
     czynnosc("wchodzi", 10000);
     czynnosc("siada", 4000);
     czynnosc("zamawia", 5000);
     czynnosc("zjada", 15000);
     czynnosc("wychodzi", 10000);
   }
}



public class Restauracja {

	public static void main(String[] args){
 
		ExecutorService executor = Executors.newFixedThreadPool(10);

	 	for (int i = 1; i < 49; i++) {
 			Runnable watek = new Klient("K" + i);
			 executor.execute(watek);
	 	}
		 executor.shutdown();
		 while(!executor.isTerminated()) {}
		 System.out.println("Wszystkie klienci zostali obsluzeni.");


	}
}
