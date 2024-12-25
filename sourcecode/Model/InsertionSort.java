package Model;

import org.omg.CORBA.IRObject;

public class InsertionSort extends Sort{
    private int iSorted; // Chỉ số đã được sắp xếp
    private int iTarget; // Chỉ số phần tử được chọn

    public InsertionSort(int[] iArray, int iNbElement) {
       super(iArray, iNbElement);
       bSortDone=false;
    }

    public int getiSorted() {
        return iSorted;
    }

    public void sort()
    {
    	for(int i=0; i<iNbElement-1; i++)
    	{
    		int minn=1000000;
        	int index=0;
    		for(int j=i+1; j<iNbElement; j++ )
    		{
    			if(iArray[j]<minn) 
    			{
    				minn=iArray[j];
    				index=j;
    			}
    		}
    		if(minn<iArray[i])
    		{
    			int tmp=iArray[i];
    			iArray[i]=minn;
    			iArray[index]=tmp;
    		}
    	}
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
        if (iSorted >= iNbElement - 1) bSortDone=true;
        return swap;
    }
}