import java.util.*;


class MakingArray extends Thread {
	
	int start;
	int finish;
	int[] list;
	Random r;
	boolean loop = true;
	
	public MakingArray (int start, int finish, int[] list, Random r) {
		this.start = start;
		this.finish = finish;
		this.list = list;
		this.r = r;
		
	}

	public synchronized void addArray() {
		try {
		//System.out.print(start);
		for(int i= start; i < finish; i++) {
		list[i] = (r.nextInt(10)+1);
		if (i % 1000 == 0) {
			System.out.print(" Current spot \n" + i);
		}
		//sleep(5);
		}
		}catch (Exception e) {
		e.printStackTrace();
		}
		System.out.print(" This thread " + finish);
	}

	
	@Override
	public void run() {
		System.out.print("Starting...");
		addArray();

	}
	
}



class ParallelSum extends Thread {
	
	int start;
	int finish;
	int[] list;
	
	public ParallelSum(int begin, int end, int [] list) {  //List<Map.Entry<Integer, Integer>> list) {
		start = begin;
		finish = end;
		this.list = list;
		
	}
	
	public static int parallelTotal = 0;
	
	public synchronized void addRandom(int i) {
	
		parallelTotal += list[i];
		//parallelTotal += 1;
	}
	
	@Override
	public void run() {
		for(int i= start; i < finish; i++) {
			addRandom(i);
		}
	}
	
	public int getParallelSum() {
		
		return parallelTotal;
	}
	
	
}


public class ThreadSummation {
   	static int[] list = new int[200000000];
	
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Random r = new Random();
		r.nextInt(10);
		boolean loop = true;
		


	

		MakingArray ma1 = new MakingArray(0, 20000000, list, r);
		MakingArray ma2 = new MakingArray(20000000, 40000000, list, r);
		MakingArray ma3 = new MakingArray(40000000, 60000000, list, r);
		MakingArray ma4 = new MakingArray(60000000, 80000000, list, r);
		MakingArray ma5 = new MakingArray(80000000, 100000000, list, r);
		MakingArray ma6 = new MakingArray(100000000, 120000000, list, r);
		MakingArray ma7 = new MakingArray(120000000, 140000000, list, r);
		MakingArray ma8 = new MakingArray(140000000, 160000000, list, r);
		MakingArray ma9 = new MakingArray(160000000, 180000000, list, r);
		MakingArray ma10 = new MakingArray(180000000, 200000000, list, r);
		ma1.start();
		ma2.start();
		ma3.start();
		ma4.start();
		ma5.start();
		ma6.start();
		ma7.start();
		ma8.start();
		ma9.start();
		ma10.start();
		
		try {
			ma1.join();
			ma2.join();
			ma3.join();
			ma4.join();
			ma5.join();
			ma6.join();
			ma7.join();
			ma8.join();
			ma9.join();
			ma10.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long start1;
		long end1;
		long start2;
		long end2;
//
		ParallelSum t1 = new ParallelSum(0, 20000000, list);
		ParallelSum t2 = new ParallelSum(20000000, 40000000, list);
		ParallelSum t3 = new ParallelSum(40000000, 60000000, list);
		ParallelSum t4 = new ParallelSum(60000000, 80000000, list);
		ParallelSum t5 = new ParallelSum(80000000, 100000000, list);
		ParallelSum t6 = new ParallelSum(100000000, 120000000, list);
		ParallelSum t7 = new ParallelSum(120000000, 140000000, list);
		ParallelSum t8 = new ParallelSum(140000000, 160000000, list);
		ParallelSum t9 = new ParallelSum(160000000, 180000000, list);
		ParallelSum t10 = new ParallelSum(180000000, 200000000, list);
		start1 = System.nanoTime();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
			t10.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		end1 = System.nanoTime();
		ParallelSum singleThread = new ParallelSum(0, 200000000, list);
		start2 = System.nanoTime();
		singleThread.start();
		try {
			singleThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		end2 = System.nanoTime();
		
		System.out.print("\nThe time for the multi Thread takes " + (end1 - start1) + " nanoseconds and the total is " + t1.getParallelSum());
		System.out.print("\n The time for the single Thread takes " + (end2 - start2)+ " nanoseconds and the total is " + singleThread.getParallelSum());
	}

}
