package barber;

import java.util.concurrent.Semaphore;

public class Barber extends Thread {

    private Semaphore barberSemaphore;

    public Barber(Semaphore sem) {
        this.barberSemaphore = sem;
    }
    @Override
    public void run() {
        int servedClients = 0;

        do {
            try {
                // Așteaptă un client să ocupe o scaun
                barberSemaphore.acquire();
                Main.chairs--;
                // Tunde clientul
                System.out.println("Barber is cutting hair");
                // Eliberează scaunul
                Main.chairsSemaphore.release();
                Main.chairs++;
                System.out.println("Barber served client");
                // Simulează o pauză între clienți
                Thread.sleep(1000);
                ++servedClients;
                barberSemaphore.release();
                if (servedClients == Main.TOTAL_CLIENTS) {
                    System.out.println("Barber served all clients. Exiting.");
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (checkIfThereAnyClients());
    }

    private boolean checkIfThereAnyClients() {
        int count = 0;
        for (var client: Main.leftClients) {
            if (client == 0) {
                count++;
            }
        }

        return count != 0;
    }
}
