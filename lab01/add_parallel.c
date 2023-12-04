#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

/*
    schelet pentru exercitiul 5
*/

int *arr;
int array_size;
int num_threads;

void *threadJob(int *arg) {

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

  // TODO: aceasta operatie va fi paralelizata cu num_threads fire de executie
  pthread_t threads[num_threads];
  int chunkSize = array_size / num_threads; 
  for (int i = 0; i < num_threads; i++) {
    int start = i * chunkSize;
    
  }
  for (int i = 0; i < array_size; i++) {
    arr[i] += 100;
  }

  for (int i = 0; i < array_size; i++) {
    printf("%d", arr[i]);
    if (i != array_size - 1) {
      printf(" ");
    } else {
      printf("\n");
    }
  }
  free(arr);
  return 0;
}
