public class Qsort {
    public static int[] quikSort(int[] arr, int low, int high) {
        int i, j;
        if (low > high){
            return null;
        }
        i = low;
        j = high;
        while (i < j) {
            while (arr[low] <= arr[j] && i < j) {
                j--;
            }
            while (arr[low] >= arr[i] && i < j) {
                i++;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, low ,i);
        quikSort(arr, low, j-1);
        quikSort(arr, j+1, high);
        return arr;
    }
    public static void swap(int[] arr, int i, int j) {
        if (i == j)
            return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

    }
    public static void main(String[] args) {
        int[] arr = {10,32918,18,2381,238,201};
        quikSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
