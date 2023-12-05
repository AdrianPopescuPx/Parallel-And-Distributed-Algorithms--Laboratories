#include <math.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int *arr;
int array_size;
int num_threads;

struct ThreadArgs {
    int start;
    int end;
};

void *threadJob(void *arg) {
    struct ThreadArgs *args = (struct ThreadArgs *)arg;
    for (int i = args->start; i < args->end; i++) {
        arr[i] += 100;
    }
    return NULL;
}

int main(int argc, char *argv[]) {
    if (argc < 3) {
        fprintf(stderr, "Specificati dimensiunea array-ului si numarul de thread-uri\n");
        exit(-1);
    }

    array_size = atoi(argv[1]);
    num_threads = atoi(argv[2]);

    arr = (int *)malloc(array_size * sizeof(int));
    for (int i = 0; i < array_size; i++) {
        arr[i] = i;
    }

    for (int i = 0; i < array_size; i++) {
        printf("%d", arr[i]);
        if (i != array_size - 1) {
            printf(" ");
        } else {
            printf("\n");
        }
    }

    struct ThreadArgs *threadArgs = (struct ThreadArgs *)malloc(num_threads * sizeof(struct ThreadArgs));

    pthread_t threads[num_threads];
    int chunkSize = array_size / num_threads;
    for (int i = 0; i < num_threads; i++) {
        int start = i * (array_size / num_threads);
        int end = fmin((i + 1) * chunkSize, array_size);
        if (end == array_size - 1)
          end++;
        struct ThreadArgs ThreadArgs;
        threadArgs[i].start = start;
        threadArgs[i].end = end;
        pthread_create(&threads[i], NULL, threadJob, &threadArgs[i]);
    }
    // for (int i = 0; i < array_size; i++) {
    //   arr[i] += 100;
    // }
    for (int i = 0; i < num_threads; i++) {
        pthread_join(threads[i], NULL);
    }
    for (int i = 0; i < array_size; i++) {
        printf("%d", arr[i]);
        if (i != array_size - 1) {
            printf(" ");
        } else {
            printf("\n");
        }
    }
    // Free dynamically allocated memory
    free(arr);
    free(threadArgs);
    return 0;
}
