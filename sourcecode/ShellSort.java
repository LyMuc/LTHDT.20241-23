public class ShellSort extends Sort {

    private int gap; // Khoảng cách giữa các phần tử trong nhóm
    private int i; // Chỉ số hiện tại trong vòng lặp ngoài
    private int j; // Chỉ số hiện tại trong vòng lặp trong
    private int currentElement; // Phần tử hiện tại cần chèn
    private boolean stepInitialized; // Ghi nhận trạng thái khởi tạo từng bước

    public ShellSort(int[] iArray, int iNbElement) {
        super(iArray, iNbElement);
        this.gap = iNbElement / 2;
        this.setI(gap);
        this.j = i;
        this.setStepInitialized(false);
    }

    @Override
    public StateSorting getStateSorting() {
        nextStep();
        return stateSorting;
    }

    @Override
    public StateSwap getSwapSorting() {
        return stateSwap;
    }

    public void nextStep() {
        // Nếu chưa khởi tạo bước, thiết lập phần tử hiện tại
        if (!stepInitialized) {
            if (i < iNbElement) {
                currentElement = iArray[i];
                j = i;
                stepInitialized = true;
            } else {
                gap /= 2; // Giảm khoảng cách gap
                if (gap == 0) {
                    stateSwap.setiArg(-1, -1); // xóa trạng thái thừa
                    stateSorting.setiArg1(-1); // xóa trạng thái thừa
                    bSortDone = true;
                    return;
                }
                i = gap;
                stateSwap.setiArg(-1, -1);
                stateSorting.setiArg1(-1);
                return;
            }

        }
        if (j >= gap) {
            if (iArray[j - gap] > currentElement) {
                stateSwap.setiArg(j, j - gap); // Cập nhật trạng thái hoán đổi (màu xanh)
                stateSorting.setiArg1(j); // Cập nhật trạng thái phần tử hiện tại (màu đỏ)
                iArray[j] = iArray[j - gap];
                j -= gap;
            } else {
                stateSwap.setiArg(j, j - gap); // Xóa trạng thái không cần thiết
                stateSorting.setiArg1(j);
                iArray[j] = currentElement;
                stepInitialized = false;
                i++;
            }
        } else {
            stateSwap.setiArg(-1, -1); // Xóa trạng thái không cần thiết
            stateSorting.setiArg1(-1);
            iArray[j] = currentElement;
            stepInitialized = false;
            i++;
        }
    }

    public boolean isStepInitialized() {
        return stepInitialized;
    }

    public void setStepInitialized(boolean stepInitialized) {
        this.stepInitialized = stepInitialized;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}