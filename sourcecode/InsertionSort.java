public class InsertionSort {
    private int[] iArray;
    private int iNbElement;
    private int iSorted; // Chỉ số đã được sắp xếp
    private int iTarget; // Chỉ số phần tử được chọn

    public InsertionSort(int[] iArray, int iNbElement) {
        this.iArray = iArray;
        this.iNbElement = iNbElement;
        this.iSorted = 0;
        this.iTarget = 0;
    }

    public int getiSorted() {
        return iSorted;
    }

    public void sort() {
        for (int i = 1; i < iNbElement; i++) {
            int key = iArray[i];
            int j = i - 1;

            // Di chuyển các phần tử lớn hơn key lên một vị trí phía sau
            while (j >= 0 && iArray[j] > key) {
                iArray[j + 1] = iArray[j];
                j = j - 1;
            }

            iArray[j + 1] = key;
        }
    }

    // Kiểm tra xem mảng đã được sắp xếp hay chưa
    public boolean isSorted() {
        return iSorted >= iNbElement - 1;
    }

    // Trả về mảng hiện tại
    public int[] getArray() {
        return iArray;
    }

    // Lấy trạng thái chỉ số phần tử được chọn trong bước sắp xếp
    public StateSorting getStateSorting() {
        iTarget = iSorted; // Mặc định chọn chính phần tử tại iSorted
        for (int j = iSorted + 1; j < iNbElement; j++) {
            if (iArray[j] < iArray[iTarget]) {
                iTarget = j; // Cập nhật chỉ số phần tử nhỏ nhất
            }
        }
        return new StateSorting(iTarget);
    }

    // Hoán đổi và trả về trạng thái đổi chỗ của mảng
    public StateSwap getSwapSorting() {
        if (iTarget != iSorted) {
            int temp = iArray[iSorted];
            iArray[iSorted] = iArray[iTarget];
            iArray[iTarget] = temp;
        }
        StateSwap swap = new StateSwap(iSorted, iTarget);
        iSorted++; // Chuyển sang phần tử tiếp theo
        return swap;
    }
}
