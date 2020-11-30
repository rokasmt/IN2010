import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Sort{

  int[] selectionSort(int[] array){
    int n = array.length;
    for(int i=0; i < n-1; i++){
          int s = i;
      for(int j=i+1; j<n; j++){
            if(array[j] < array[s]){
                  s = j;
        }
      }
      swap(array,i,s);
    }
    return array;
  }

  int[] inPlaceQuickSort(int[] array, int fraIndex, int tilIndex){
    int left;
    while(fraIndex < tilIndex){
      left = inPlacePartition(array, fraIndex, tilIndex);

      if(left - fraIndex < tilIndex - left){
        inPlaceQuickSort(array, fraIndex, left-1);
        fraIndex = left+1;
      } else {
        inPlaceQuickSort(array, left+1, tilIndex);
        tilIndex = left-1;
      }
    }
    return array;
  }

  int inPlacePartition(int[] array, int fraIndex, int tilIndex){
    int pivot = array[tilIndex];
    int left = fraIndex;
    int right = tilIndex-1;
    while(left <= right){
      while(left <= right && array[left]<= pivot){
        left++;
      }
      while(right >= left && array[right]>=pivot){
        right--;
      }
      if(left<right){
        swap(array,left,right);
      }
    }
    swap(array,left,tilIndex);
    this.printArray(array);
    return left;
  }

  int[] mergeSort(int[] arr, int lengde) {
        long t = System.nanoTime(); //nanosekunder

        if (lengde < 2) {
          return arr;
        }

        int midten = lengde / 2;
        int[] left = new int[midten];
        int[] right = new int[lengde - midten];

        for (int i = 0; i < midten; i++) {
          left[i] = arr[i];
        }

        for (int i = midten; i < lengde; i++) {
          right[i - midten] = arr[i];
        }

        mergeSort(left, midten);
        mergeSort(right, lengde - midten);
        merge(arr, left, right, midten, lengde - midten);

        double tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder

        return arr;
 }

  void merge(int[] array, int[] left, int[] right, int leftLength, int rightLength) {

      int i = 0;
      int j = 0;
      int indeks = 0;

      while (i < leftLength && j < rightLength) {
          if (left[i] <= right[j]) {
            array[indeks++] = left[i++];
          }
          else {
            array[indeks++] = right[j++];
          }
      }
      while (i < leftLength) {
        array[indeks++] = left[i++];
      }
      while (j < rightLength) {
        array[indeks++] = right[j++];
      }
  }

void swap(int[] array, int indeks1, int indeks2){
    int temp = array[indeks1];
    array[indeks1] = array[indeks2];
    array[indeks2] = temp;
  }
  void printArray(int[] array){
    for(int i:array){
      System.out.print(i+", ");
}
    System.out.println();
  }
}
