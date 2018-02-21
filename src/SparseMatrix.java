import java.util.Scanner;

public class SparseMatrix {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);


        Matrix matrix = new Matrix();
        /* Printing out Linked List
         Should print out as such: row, column, data. Row and column
         are equal to each other (it's a matrix!)

        */

        System.out.println("Determine the size of the matrix: ");
        int listSize = input.nextInt(); // User enters size of matrix (default is 5x5)
        matrix.setSize(listSize);

        System.out.println("Please enter values");          // Initializing values.
        int d;
        int i;
        int j;




        for (i = 0; i < matrix.getSize(); i++) {            // Displaying matrix as row, column, data.
            for (j = 0; j < matrix.getSize(); j++) {
                    d = input.nextInt();

                if (d!=0) {
                    matrix.addElement(i,j,d);
                }
            }
            input.nextLine();
        }

        System.out.println(matrix.toString());      // Printing out matrix (as a string).

      System.out.println("Determinant of Matrix: ");
      System.out.println(matrix.determinant());         // Calling determinant method; printing out determinant.


    }
}
