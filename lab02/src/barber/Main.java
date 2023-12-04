package barber;

import java.util.concurrent.Semaphore;

public class Main {
    public final static int TOTAL_CHAIRS = 3;
    public final static int TOTAL_CLIENTS = 7;

    // a client can leave the shop served
    public final static int SERVED_CLIENT = 1;
    // a client can leave the shop unserved, as the barber was too busy and there no seats available
    public final static int UNSERVED_CLIENT = 2;

    public static int[] leftClients = new int[TOTAL_CLIENTS];

    // TODO: add semaphores
    public static Semaphore chairsSemaphore = new Semaphore(TOTAL_CHAIRS);
    public static Semaphore barberSemaphore = new Semaphore(1); // Un singur client poate intra la coafor în același timp


    public static int clients = TOTAL_CLIENTS;
    public static int chairs = TOTAL_CHAIRS;

    public static void main(String[] args) throws InterruptedException {
        Thread barberThread = new Barber(barberSemaphore);
        Thread[] clientThreads = new Client[clients];

        for (int i = 0; i < clients; i++) {
            clientThreads[i] = new Client(i, chairsSemaphore);
        }

        barberThread.start();
        for (Thread clientThread : clientThreads) {
            clientThread.start();
            Thread.sleep(100);
        }

        barberThread.join();
        for (var thread: clientThreads) {
            thread.join();
        }

        int unservedClients = 0;
        for (var client: leftClients) {
            if (client == UNSERVED_CLIENT) {
                unservedClients++;
            }
        }

        System.out.println("There were " + unservedClients + " unserved clients");
    }
}
