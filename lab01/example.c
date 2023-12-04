#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS 2

void *functionThreadOne(void *arg) {
    // long id = *(long *)arg;
    // for (int i = 1; i <= 100; i++) {
    //     printf("%d Hello World din thread-ul %ld!\n", i, id);
    // }
    printf("Hello from thread 1\n");
    pthread_exit(NULL);
    return NULL;
}

void *functionThreadTwo(void *arg) {
    printf("Hello from thread 2\n");
    return NULL;
}

int main(int argc, char *argv[]) {
    pthread_t threads[NUM_THREADS];
    int r;
    long id;
    void *status;
    long ids[NUM_THREADS];

    pthread_create(&threads[0], NULL, functionThreadOne,  NULL);
    pthread_create(&threads[1], NULL, functionThreadTwo, NULL);

    // for (id = 0; id < NUM_THREADS; id++) {
    //     ids[id] = id;
    //     r = pthread_create(&threads[id], NULL, f);

    //     if (r) {
    //         printf("Eroare la crearea thread-ului %ld\n", id);
    //         exit(-1);
    //     }
    // }

    for (id = 0; id < NUM_THREADS; id++) {
        r = pthread_join(threads[id], &status);

        if (r) {
            printf("Eroare la asteptarea thread-ului %ld\n", id);
            exit(-1);
        }
    }

    return 0;
}
