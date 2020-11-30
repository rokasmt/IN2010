/*Algorithm arrayMax(A, n):
Input: An array A storing n ≥ 1 integers.
 Output: The maximum element in A.
currentMax ← A[0] for i ← 1 to n − 1 do
if currentMax < A[i] then currentMax ← A[i]
return currentMax*/

class Oppgave2 {

      public static void main(String[] args) {
            int[] array = {2, 4, 5, 79, 69};
            int n = 5;

            System.out.println("Høyeste verdi i lista er " + ArrayMax(array, n));
      }

      public static int ArrayMax(int[] a, int n) {

            int currentMax = a[0];
            for (int i = 1; i < n; i++){
                  if(currentMax < a[i]){
                        currentMax = a[i];
                  }
            }
            return currentMax;
      }
}
