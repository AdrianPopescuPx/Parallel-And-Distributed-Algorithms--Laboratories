package barber;

import java.util.concurrent.Semaphore;

public class Client extends Thread {
    private final int id;
    private Semaphore clientSemaphore;

    public Client(int id, Semaphore clientSemaphore) {
        super();
        this.id = id;
        this.clientSemaphore = clientSemaphore;
    }

    @Override
    public void run() {
        if (Main.chairs > 0) {
            // client occupies a seat
            Main.chairs--;
            System.out.println("Client " + id + " is waiting for haircut");
            System.out.println("Available seats: " + Main.chairs);
            System.out.println("Client " + id + " is served by the barber");

            Main.leftClients[id] = Main.SERVED_CLIENT;
        } else {
            System.out.println("Client " + id + " left unserved");
            Main.leftClients[id] = Main.UNSERVED_CLIENT;
        }
    }
}
