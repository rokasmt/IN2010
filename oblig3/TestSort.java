//Programmet skal kjores slik:
//  java TestSort <metode> <antall elementer>
//Eksempel:
//  java TestSort quickSort 10000

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

import java.util.Collections;

class TestSort{
  int numElements = 10;
  Random rand = new Random();
  Sort sort = new Sort();
  int[] arrayToSort;

  public TestSort(int numberOfElements){
    this.numElements = numberOfElements;
    this.arrayToSort = new int[numElements];

    for(int i = 0; i<this.numElements;i++){
      this.arrayToSort[i] = rand.nextInt(10);
    }
  }

  void arrayType(String type){
    if(type == "asc"){
      Arrays.sort(arrayToSort);
    } else if (type == "desc") {
      for(int i=0; i<arrayToSort.length/2; ++i){
        int temp = arrayToSort[i];
        arrayToSort[i] = arrayToSort[arrayToSort.length-i-1];
        arrayToSort[arrayToSort.length-i-1] = temp;
      }
    }
    System.out.println("Unsorted "+type+": ");
  }

  int[] sortTest(String method){
    int[] sorted = new int[this.numElements];

    if(method == "selectionSort"){
      sorted = sort.selectionSort(arrayToSort);
    } else if (method == "inPlaceQuickSort"){
      sorted = sort.inPlaceQuickSort(arrayToSort, 0, arrayToSort.length);
    } else if (method == "quickSort") {
      sort.inPlaceQuickSort(arrayToSort,0,arrayToSort.length-1);
      sorted = arrayToSort;
    } else if (method == "merge") {
      sort.mergeSort(arrayToSort, arrayToSort.length);
      sorted = arrayToSort;
    }

    System.out.println();
    System.out.println("Sorted by "+method+": ");

    return sorted;
  }

  void printArray(int[] array){
    for(int i:array){
      System.out.print(i+", ");
    }
    System.out.println();
  }


  public static void main(String[] args) {
    String type = "";
    int[] sorted = new int[Integer.parseInt(args[1])];

    TestSort test = new TestSort(Integer.parseInt(args[1]));
    if(args[0].equals("selectionSort")){
      type = "selectionSort";
      } else if (args[0].equals("quickSort")){
      type = "quickSort";
      } else if (args[0].equals("mergeSort")){
      type = "mergeSort";
    }

    System.out.println("Args 0 er:"+type);

    test.arrayType("random");
    long t1 = System.nanoTime();
    sorted = test.sortTest(type);
    double tid1 = (System.nanoTime()-t1)/10000000.0;
    System.out.println("Kjoeringen tok: "+tid1+" millisekunder");

    System.out.println();
    System.out.println();

    test.arrayType("asc"); //den som øker
    long t2 = System.nanoTime();
    sorted = test.sortTest(type);
    double tid2 = (System.nanoTime()-t2)/10000000.0;
    System.out.println("Kjoeringen tok: "+tid2+" millisekunder");

    System.out.println();
    System.out.println();


    test.arrayType("desc"); // den som faler ned
    long t3 = System.nanoTime();
    sorted = test.sortTest(type);
    double tid3 = (System.nanoTime()-t3)/10000000.0;
    System.out.println("Kjoeringen tok: "+tid3+" millisekunder");
    System.out.println();
    System.out.println();
  }
}
