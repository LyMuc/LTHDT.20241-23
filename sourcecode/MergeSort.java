package Model;

public class MergeSort extends Sort {
    private int currentSize; // Kích thước của các đoạn đang được xử lý
    private int leftStart; // Vị trí bắt đầu của đoạn bên trái trong một lần gộp

    private int stepMid, stepEnd;
    private int stepI, stepJ, stepK;
    private boolean isMerging;

    private int[] tempArray; // Mảng tạm để hỗ trợ việc gộp

    public MergeSort(int[] iArray, int iNbElement) {
        super(iArray, iNbElement);
        this.currentSize = 1; // Bắt đầu với kích thước đoạn là 1
        this.leftStart = 0; // Vị trí bắt đầu ban đầu là 0
        this.isMerging = false; // Chưa bắt đầu gộp
        this.tempArray = new int[iNbElement]; // Khởi tạo mảng tạm với kích thước của mảng đầu vào
    }

    public void sort() {
        currentSize = 1; // Bắt đầu với kích thước đoạn là 1
        leftStart = 0; // Vị trí bắt đầu ban đầu là 0
        isMerging = false; // Chưa bắt đầu gộp
        bSortDone = false; // Đánh dấu chưa hoàn thành sắp xếp

        while (!bSortDone) {
            getStateSorting(); // Gọi để thực hiện một lần gộp
        }
    }

    @Override
    public StateSorting getStateSorting() {
        if (!isMerging) {
            // Chuẩn bị thông tin cho lần gộp mới
            stepMid = Math.min(leftStart + currentSize - 1, iNbElement - 1);
            stepEnd = Math.min(leftStart + 2 * currentSize - 1, iNbElement - 1);
            stepI = leftStart;
            stepJ = stepMid + 1;
            stepK = leftStart;
            isMerging = true;

            // Sao chép đoạn cần merge vào mảng tạm
            for (int i = leftStart; i <= stepEnd; i++) {
                tempArray[i] = iArray[i];
            }
        }

        // Thực hiện từng bước merge
        if (stepI <= stepMid && stepJ <= stepEnd) {
            // Cập nhật trạng thái hoán đổi
            stateSwap.setiArg(stepI, stepJ);
            stateSorting.setiArg1(stepI);

            // So sánh và gộp
            if (tempArray[stepI] <= tempArray[stepJ]) {
                iArray[stepK++] = tempArray[stepI++];

            } else {

                iArray[stepK++] = tempArray[stepJ++];
            }
        } else if (stepI <= stepMid) {
            // Chỉ còn lại phần tử bên trái
            stateSwap.setiArg(stepI, -1);
            stateSorting.setiArg1(-1);
            iArray[stepK++] = tempArray[stepI++];
        } else if (stepJ <= stepEnd) {
            // Chỉ còn lại phần tử bên phải
            stateSwap.setiArg(-1, stepJ);
            stateSorting.setiArg1(-1);
            iArray[stepK++] = tempArray[stepJ++];
        } else {
            if (stepMid == stepEnd) {
                stateSwap.setiArg(-1, -1);
                stateSorting.setiArg1(leftStart);
            }
            // Hoàn thành gộp đoạn hiện tại
            leftStart += 2 * currentSize;
            isMerging = false;
            if (leftStart >= iNbElement) {
                leftStart = 0;
                currentSize *= 2;
                if (currentSize >= iNbElement) {
                    bSortDone = true; // Đánh dấu hoàn thành
                }
            }
        }
        return stateSorting;
    }

    @Override
    public StateSwap getSwapSorting() {
        return stateSwap;
    }

    public void displayFinalArray() {
        if (!isMerging) { // Chỉ hiển thị khi hoàn thành một lần gộp
            for (int i = 0; i < iNbElement; i++) {
                System.out.print(iArray[i] + " ");
            }
            System.out.println();
        }
    }

    // Kiểm tra xem thuật toán đã hoàn thành chưa
    public boolean isSorted() {
        return bSortDone; // Thuộc tính bSortDone có sẵn từ lớp cha Sort
    }

    // Trả về mảng hiện tại
    public int[] getArray() {
        return iArray; // iArray là mảng lưu trữ chính từ lớp cha Sort
    }

}
