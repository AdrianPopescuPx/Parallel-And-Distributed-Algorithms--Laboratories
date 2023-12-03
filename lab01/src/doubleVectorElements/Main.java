package doubleVectorElements;

public class Main {

    public static void main(String[] args) {
        int N = 100000013;
        int[] v = new int[N];
        int P = 4; // the program should work for any P <= N

        for (int i = 0; i < N; i++) {
            v[i] = i;
        }
        Thread[] threads = new Thread[P];
        // Parallelize me using P threads
        int chunkSize = N / P;
        for (int i = 0; i < P; i++) {
            if (i == P-1) {
                threads[i] = new Thread(new Worker(v, chunkSize * i, N));
            }   else threads[i] = new Thread(new Worker(v, chunkSize * i, Math.min((i + 1) * chunkSize, N)));
            threads[i].start();
        }

        for (int i = 0; i < P; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < N; i++) {
            if (v[i] != i * 2) {
                System.out.println("Wrong answer");
                System.exit(1);
            }
        }
        System.out.println("Correct");
    }

}
