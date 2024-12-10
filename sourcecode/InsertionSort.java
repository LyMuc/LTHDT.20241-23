
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
		int minn=1000000;
		if(iSorted==iNbElement-1) bSortDone=true;
		for(int i=iSorted; i<iNbElement; i++)
		{
			//... Tim phan tu lon nhat tai moi buoc
			if(iArray[i]<minn)
			{
				minn=iArray[i];
				iTarget=i;
			}
		}
		stateSorting.setiArg1(iTarget);
		return(stateSorting);
	}
	
	public StateSwap getSwapSorting() // Phuong thức để lấy về chỉ số của 2 phần tử sẽ đổi chỗ tại bước này
	{
		stateSwap.setiArg(iSorted, iTarget); //2 số cần quan tâm tại thời điểm này
		
		int temp=stateSwap.getiArg2();
		iArray[iSorted]=iArray[iTarget]; //Đổi chỗ 2 phần tử này
		iArray[iTarget]=temp;
		
		iSorted++; //Số phần tử đã xét tăng lên 1
		return(stateSwap);
	}
}
