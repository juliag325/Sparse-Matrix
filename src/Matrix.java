// Julia Aiello
/* Determinant of a Sparse Matrix
   Program returns sparse matrix as a linked list, and recursively solves for the determinant.
*/
import java.util.Scanner;
class Node {                // Node class
    private int row;
    private int column;
    private int data;
    private Node next;


    //Constructors
    Node(int x, int y, int z, Node n) {
        row = x;
        column = y;
        data = z;
        next = n;
    }


    Node(Node n) {
        data = 0;
        next = n;

    }

    int element() {             // Element method.
        return data;
    }

    int row() {                 // Row method.

        return row;
    }

    int column() {              // Column method.

        return column;
    }

    void setElement(int x, int y, int z) {
        row = x;
        column = y;
        data = z;
    }

    Node next() {
        return next;
    }

    void setNext(Node n) {
        next = n;
    }

}

    public class Matrix implements SparseInterface {

        private Node head;
        private Node tail;
        private Node curr;
        private int listSize;


        Matrix() {
            clear();
        }

        public Matrix(int size) {
             size = getSize();
        }

        public void clear() {
            curr = tail = new Node(null); // Create trailer
            head = new Node(tail); // Create header
            listSize = 5;

        }

        public void setSize(int size) {             // Set size of matrix; default is 5 x 5
            listSize = size;
        }

        public void addElement(int row, int col, int data) {            // Adds element to the linked list.
            if (row > listSize - 1 || col > listSize - 1)
                System.out.println("Error: out of bounds. ");


            else if (head.element() == 0)

            {
                head = new Node(row, col, data, head.next());
                tail = head;
                head.setNext(null);

            } else

            {
                tail.setNext(new Node(row, col, data, curr.next()));
                tail = tail.next();
                tail.setNext(null);
            }
        }


        public SparseInterface removeElement(int row, int col) {            // Removes element from linked list.

            if (row > getSize() - 1 || col > getSize() - 1) {
                System.out.println("Error: out of bounds. ");
            }

                curr = head;
                boolean remove = false;
                do {
                    if (curr.next() != null) {
                        if (curr.next().row() == row && curr.next().column() == col) {
                            if (curr.next() == tail) {
                                tail = curr;
                                tail.setNext(null);
                                remove = true;
                            } else {
                                curr.setNext(curr.next().next());
                                remove = true;
                            }
                        }
                    } else {
                        System.out.println("Not found.");
                    }
                }
                while (remove == false);
            return null;
        }


        public int getElement(int row, int col) {
            if (row > listSize - 1 || col > listSize - 1) {
                System.out.println("Error: out of bounds. ");
            }
            curr = head;
            boolean quit = false;

            do {
                if (curr.row() == row && curr.column() == col) {
                    return curr.element();
                } else if (curr.next() == null) {
                    quit = true;
                } else curr = curr.next();
            }
            while (quit == false);
            return -1;
        }

        public int determinant() {
            // Recursive method to find the determinant.
            int detMatrix = 0;

            if (getSize()-1 !=1) {


                for (int i = 0; i < getSize()-1; i++) {
                    if (getElement(0, i) != 0) {
                        if (i % 2 == 0) {
                            detMatrix += getElement(0, i) * minor(0, i).determinant();      // Recursive function.
                        } else {
                            detMatrix = (-1) * getElement(0, i) * minor(0, i).determinant();
                        }
                    }
                }
            }
            else {
                detMatrix = getElement(0, 0);       // Base case.
                return detMatrix;
            }

            return detMatrix;

        }


        public String toString() {              // Displays sparse matrix as a string: row column data.
            String matrixString = "";
            curr = head;
            boolean quit = false;

            do {
                matrixString += curr.row() + " "
                        + curr.column() + "  "
                        + curr.element() + "\n";
                if (curr.next() != null) {
                    curr = curr.next();
                } else quit = true;
            }
            while (quit == false);
            return matrixString;
        }


        public int getSize() {

            return listSize;
        }

        public SparseInterface minor(int row, int col) {
            int i = getSize();
            SparseInterface minor = new Matrix(getSize()-1);        // Object SparseInterface.


            boolean done = false;
            int newRow;
            int newCol;
            curr = head;

            do {
                if (curr.row() == row || curr.column() == col) {        // Creates new index, shrinks matrix size

                    if (curr.row() < row) {
                        newRow = curr.row();
                    }

                    else {
                        newRow = curr.row() - 1;
                    }

                    if (curr.column() < col) {
                        newCol = curr.column();
                    }

                    else {
                        newCol = curr.column() - 1;
                    }

                    minor.addElement(newRow, newCol, curr.element());
                    if (curr.next() != null) {
                        curr = curr.next();

                    }
                    else done = true;

                } else {
                    if (curr.next() != null) {
                        curr = curr.next();
                    } else done = true;
                }
            }
            while (done = false);

            return minor;       // Returns new matrix.
        }




    }






