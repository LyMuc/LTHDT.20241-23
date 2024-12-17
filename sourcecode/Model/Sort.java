package Model;


public abstract class Sort {
	public static final int MAX_ELEMENT= 1000000; //So luong phan tu tối đa của mảng
	public static boolean bSortDone=false;
	protected int[] iArray = new int[MAX_ELEMENT];
	protected int iNbElement;
	protected StateSorting stateSorting;
	protected StateSwap stateSwap;
	
	public Sort(int[] iArray, int iNbElement) {
		super();
		this.iArray = iArray;
		this.iNbElement = iNbElement;
		this.stateSorting = new StateSorting();
		this.stateSwap = new StateSwap();
	}

	public boolean getStateFinish() //Phuong thuc de lay trang thai da sort xong hay chua
	{
		return(bSortDone);
	}
	
	public abstract StateSorting getStateSorting(); 
    public abstract StateSwap getSwapSorting();

}
