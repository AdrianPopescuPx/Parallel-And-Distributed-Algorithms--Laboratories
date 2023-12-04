package doubleVectorElements;

import java.nio.charset.MalformedInputException;

public class Worker implements Runnable {

    private final int[] v;
    private final int start;
    private final int end;

    public Worker(int[] v, int start, int end) {
        this.v = v;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            synchronized (v) {
                v[i] = v[i] * 2;
            }
        }
    }
}
