/**
 * @Course: SDEV 350 ~ Java Programming II
 * @Author Name: Adam Flammino
 * @Assignment Name: aflammino_week10
 * @Date: Apr 04, 2017
 * @Subclass Sort Description: Contains actual sorting methods used by
 * Aflammino_week10
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Sort {

    static int size;

    /**
     * Constructor
     * @param a = array to be sorted
     */
    Sort(ArrayList<Integer> a) {
        size = a.size();  // appears to run a bit faster with size() only being called once when possible
    }


    /**
     * Insertion sort with standard size
     * @param a = array to be sorted
     */
    public void insertion(ArrayList<Integer> a) {
        for (int i = 1; i < size; i++) {
            insertionInnards(a, i);
        }
    }


    /**
     * Insertion sort with non-standard size
     * @param b = array to be sorted
     */
    public void insertion2(ArrayList<Integer> b) {
        int size2 = b.size();
        for (int i = 1; i < size2; i++) {
            insertionInnards(b, i);
        }
    }

    /**
     * Inside of loop for both standard and non-standard insertion sorts
     * @param a = array to be sorted
     * @param i
     */
    private static void insertionInnards(ArrayList<Integer> a, int i){
        int index = a.get(i);
        int x = i;
        while (x > 0 && a.get(x - 1) > index) {
            a.set(x, a.get(x - 1));
            x--;
        }
        a.set(x, index);
    }

    /**
     * Bubble Sort
     **/

    public void bubble(ArrayList<Integer> a) {
        boolean nextPass = true;
        for (int i = 1; i < size && nextPass; i++) {
            nextPass = false;
            for (int x = 0; x < size - i; x++) {
                if (a.get(x) > a.get(x + 1)) {
                    //Swap using temp position
                    int tempPos = a.get(x);
                    a.set(x, a.get(x + 1));
                    a.set(x + 1, tempPos);
                    nextPass = true;
                }
            }
        }

    }

    /**
     * merge sort
     *
     * @param list to be sorted
     * @return sorted list
     */
    public ArrayList<Integer> mergeSort(ArrayList<Integer> list) {
        int mergeSize = list.size();
        int half = mergeSize / 2;
        if (mergeSize > 1) {
            // Merge sort first half
            ArrayList<Integer> firstHalf = new ArrayList<>();
            for (int i = 0; i < half; i++) {
                firstHalf.add(list.get(i));
            }
            firstHalf = mergeSort(firstHalf);
            // Merge sort second half
            ArrayList<Integer> secondHalf = new ArrayList<>();
            for (int i = half; i < mergeSize; i++) {
                secondHalf.add(list.get(i));
            }
            secondHalf = mergeSort(secondHalf);

            // Merge two halves into ArrayList
            merge(firstHalf, secondHalf, list);
            return list;
        } else {
            return list;
        }
    }

    /**
     * Merge sort part 2
     *
     * @param f = first half
     * @param s = second half
     * @param l = full list
     */
    public void merge(ArrayList<Integer> f, ArrayList<Integer> s, ArrayList<Integer> l) {
        int indexF = 0;
        int indexS = 0;
        int indexL = 0;
        int sizeF = f.size();
        int sizeS = s.size();

        while (indexF < sizeF && indexS < sizeS) {
            if (f.get(indexF) < s.get(indexS)) {
                l.set(indexL, f.get(indexF));
                indexF++;
            } else {
                l.set(indexL, s.get(indexS));
                indexS++;
            }
            indexL++;
        }
        // Copies leftover numbers to array
        ArrayList<Integer> e = new ArrayList<>();
        int indexE;
        if (indexF == sizeF) {
            e = s;
            indexE = indexS;
        } else {
            e = f;
            indexE = indexF;
        }
        int sizeE = e.size();
        for (int i = indexE; i < sizeE; i++) {
            l.set(indexL, e.get(i));
            indexL++;
        }
    }

    /**
     * quick sort
     *
     * @param a    = ArrayList to be sorted
     * @param low  = Stating element to sort
     * @param high = Last element to sort
     */
    public static void qSort(ArrayList<Integer> a, int low, int high) {
        //   sTime = System.nanoTime();
        /* Index variables */
        int l = low;
        int h = high;

        /* Check for at least 2 array elements */
        if (high - low >= 1) {
            int pivot = a.get(low); //Set pivot to first element in array
            /* Scan left and right */
            while (h > l) {
                /* Find first element from left greater than pivot value */
                while (a.get(l) <= pivot && l < high && h > l) {
                    l++;
                }
                /* Find first element from right less than pivot value */
                while (a.get(h) > pivot && h >= low && h >= l) {
                    h--;
                }
                if (h > l) { // If left value less than right value, call swap method
                    Collections.swap(a, l, h); // Swap high and low values
                }
            }
            /* Once all values have been swapped, swap last element in left with pivot */
            Collections.swap(a, low, h);
            /* Recursively call qSort for both partitions */
            qSort(a, low, h - 1);    //Left partition
            qSort(a, h + 1, high);   //Right partition
        }
    }


    /**
     * Heap sort
     * @param a = array to be sorted
     */
    public void hSort(ArrayList<Integer> a) {

        /* Determine total number of elements */
        int total = size - 1;
        int root = total / 2 - 1;
        /* Loop until root of heap & build heap */
        for (int i = root; i >= 0; i--) {
            heapIt(a, i, total);
        }
        for (int i = total; i > 0; i--) {
            Collections.swap(a, 0, i);
            total--;
            heapIt(a, 0, total);
        }
    }

    /**
     * Builds heaps
     * @param a = array to be sorted
     * @param nums
     * @param total
     */
    public static void heapIt(ArrayList<Integer> a, int nums, int total) {
        int low = nums * 2;
        int high = low + 1;
        int great = nums;

        if (low < total && a.get(low) > a.get(high)) {
            high = low;
        }
        if (high <= total && a.get(high) > a.get(great)) {
            great = high;
        }
        if (great != nums) {
            Collections.swap(a, nums, great);
            heapIt(a, great, total);
        }
    }

    /**
     * Bucket sort
     * @param a = unsorted array
     * @param min
     * @param max
     * @return sorted array
     */
    public ArrayList<Integer> bucketSort(ArrayList<Integer> a, int min, int max) {
        int range = (max + 1) - min;
        int lowHalf = range / 2; // Bottom half of range
        ArrayList<Integer> bucket1 = new ArrayList<>();
        ArrayList<Integer> bucket2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (a.get(i) <= lowHalf) {
                bucket1.add(a.get(i)); // Numbers in bottom half of range go into bucket1
            } else {
                bucket2.add(a.get(i)); // Numbers in top half of range go into bucket2
            }
        }
        insertion2(bucket1); // Sort each bucket
        insertion2(bucket2);
        bucket1.addAll(bucket2); // Combine buckets
        return bucket1;
    }

    /**
     * Radix sort, modified from http://seattlecentral.edu/faculty/flepeint/javaclass/progSamples/SortingAlgorithms.java
     *
     * @param a = array to be sorted
     * @param max = largest value
     */
    public void radixSort(ArrayList<Integer> a, int max) {
        {
            int digits = (int) (Math.log10(max) + 1); // how many places beyond 1s in largest number
            ArrayList[] groups = new ArrayList[10]; // Array of 10 arraylists
            for (int d = 0; d < digits; d++) {
                // Initialize the groups
                for (int i = 0; i < groups.length; i++)
                    groups[i] = new ArrayList();
                // Group the elements of a according to the value of their digit
                for (int i = 0; i < size; i++) {
                    int index = (int) ((a.get(i) / Math.pow(10, d)) % 10);
                    groups[index].add(new Integer(a.get(i)));
                }
                // Copy the result in a
                int index = 0;
                for (int i = 0; i < groups.length; i++) {
                    Iterator it = groups[i].iterator();
                    while (it.hasNext()) {
                        a.set(index, ((Integer) it.next()).intValue());
                        index++;
                    }
                }
            }
        }
    }
}