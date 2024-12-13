public class Main {
    public static void Color(Object state) {
        if (state instanceof StateSorting) {
            StateSorting sortingState = (StateSorting) state;
            System.out.println(sortingState.getiArg1());
        } else if (state instanceof StateSwap) {
            StateSwap swapState = (StateSwap) state;
            if (swapState.getiArg1() >= 0 && swapState.getiArg2() >= 0) {
                System.out.println(swapState.getiArg1() + " " + swapState.getiArg2());
            }
        } else {
            System.out.println("Không hỗ trợ loại trạng thái này.");
        }
    }

    public static void main(String[] args) {
        int[] arr = {6 ,5, 3, 2 ,8 ,7, 1 ,4};
        ShellSort shellSort = new ShellSort(arr, arr.length);

        //System.out.println("Mảng trước khi sắp xếp:");
       // printArray(arr);

        while (!Sort.bSortDone) {
            shellSort.nextStep();
            StateSorting sortingState = shellSort.getStateSorting();
            StateSwap swapState = shellSort.getSwapSorting();
         
            if(sortingState.getiArg1()!=-12)
            {
           Main.Color(sortingState); // Tô đỏ phần tử hiện tại
            }
           if(swapState.getiArg1()!=-1)
           {
           Main.Color(swapState); // Tô xanh phần tử hoán đổi
           }
        }

        /*System.out.println("Mảng sau khi sắp xếp:");
        printArray(arr);
    }

   // In mảng
    static void printArray1(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
    // In mảng
    static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
        */
}
}

