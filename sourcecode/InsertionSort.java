
public class InsertionSort extends Sort{
	
	private int iTarget;
	private int iSorted;
	public InsertionSort(int[] iArray, int iNbElement) {
		super(iArray, iNbElement);
		// TODO Auto-generated constructor stub
		this.iSorted=0;
		this.iTarget=0;
	}

	public StateSorting getStateSorting() // Phương thức để lấy về chỉ số của phần tử lớn nhất trong số các phần tử còn lại
										  // Đây là với trường hợp của InsertionSort, còn các sort khác thì dựa trên thuật toán
	{
		for(int i=iSorted; i<iNbElement; i++)
		{
			//... iTarget
		}
		stateSorting.setiArg1(iTarget);
		return(stateSorting);
	}
	
	public StateSwap getSwapSorting() // Phuong thức để lấy về chỉ số của 2 phần tử sẽ đổi chỗ tại bước này
	{
		stateSwap.setiArg(iSorted, iTarget);
		
		int temp=stateSwap.getiArg2();
		iArray[iSorted]=iArray[iTarget];
		iArray[iTarget]=temp;
		
		iSorted++;
		return(stateSwap);
	}
}
