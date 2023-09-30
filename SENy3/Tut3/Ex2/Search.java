package Tut3.Ex2;

public class Search {

    public static class NotFoundException extends Exception {
        public NotFoundException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws NullPointerException, NotFoundException {
        int[] a = {1, 2, 3, 4, 5};
        int x = 3;

        // Implementation using a for loop
        System.out.println("Implementation using a for loop:");
        int index = search(a, x);
        if (index >= 0) {
            System.out.println("The element x is found at index " + index);
        } else {
            System.out.println("The element x is not found in the array");
        }

        // Implementation using a while loop
        System.out.println("\nImplementation using a while loop:");
        index = searchWhile(a, x);
        if (index >= 0) {
            System.out.println("The element x is found at index " + index);
        } else {
            System.out.println("The element x is not found in the array");
        }
    }

    /**
     * Implementation of search using a for loop.
     *
     * @param a The array to search.
     * @param x The element to search for.
     * @return The index of the element x, or -1 if the element is not found.
     * @throws NullPointerException if the array is null.
     * @throws NotFoundException if the element x is not found in the array.
     */
    public static int search(int[] a, int x) throws NullPointerException, NotFoundException {
        if (a == null) {
            throw new NullPointerException("The array is null");
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                return i;
            }
        }

        throw new NotFoundException("The element x is not found in the array");
    }

    /**
     * Implementation of search using a while loop.
     *
     * @param a The array to search.
     * @param x The element to search for.
     * @return The index of the element x, or -1 if the element is not found.
     * @throws NullPointerException if the array is null.
     * @throws NotFoundException if the element x is not found in the array.
     */
    public static int searchWhile(int[] a, int x) throws NullPointerException, NotFoundException {
        if (a == null) {
            throw new NullPointerException("The array is null");
        }

        int i = 0;

        while (i < a.length && a[i] != x) {
            i++;
        }

        if (i < a.length) {
            return i;
        } else {
            throw new NotFoundException("The element x is not found in the array");
        }
    }
}
