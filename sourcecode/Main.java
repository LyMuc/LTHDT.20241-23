public class Main {
	public static void Color(Object state) {
        if (state instanceof StateSorting) {
            StateSorting sortingState = (StateSorting) state;
            // Thực hiện tô màu cho chỉ số i trong SortingState
            System.out.println("Tô màu đỏ ở chỉ số: " + sortingState.getiArg1());
            // (Giả sử bạn sẽ thực hiện tô màu giao diện ở đây)
        } else if (state instanceof StateSwap) {
            StateSwap swapState = (StateSwap) state;
            // Thực hiện tô màu cho cặp chỉ số (i, j) trong SwapState
            System.out.println("Tô màu blue ở chỉ số: " + swapState.getiArg1() + " và " + swapState.getiArg2());
            // (Giả sử bạn sẽ thực hiện tô màu giao diện ở đây)
        } else {
            System.out.println("Không hỗ trợ loại trạng thái này.");
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]= {2,1,3,4,5};
		Sort insertionSort = new InsertionSort(a, 5);
		while(!insertionSort.getStateFinish())
		{
			StateSorting stateSorting = insertionSort.getStateSorting();
			Color(stateSorting);
			StateSwap swapSorting = insertionSort.getSwapSorting();
			Color(swapSorting);
			//show(swapSorting);
			//Swap(swapSorting);
			//show(swapSorting);
			//show(stateSorting);
		}
	}

}