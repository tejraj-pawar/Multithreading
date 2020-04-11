package com.yolo.multithreading;

class Counter
{
	int count = 0;
	
	// synchronized keyword is compulsory else final count would be always less than 10
	synchronized void incrementCount()
	{
		count = count + 1;
	}
}

public class MultithreadingRunnableSynchronized {

	public static void main(String[] args) throws InterruptedException {
		Counter c = new Counter();
		
		// here we're using runnable with lambda(java 1.8 feature)
        Runnable obj1 = () -> 
			 {
				for(int i=0; i<5; i++)
				{
					c.incrementCount();
					try { Thread.sleep(100); } catch (InterruptedException e) {}
				}
			};
		
		Runnable obj2 = () ->
			{
				for(int i=0; i<5; i++)
				{
					c.incrementCount();
					try { Thread.sleep(100); } catch (InterruptedException e) {}
				}
			};
		
		// create a thread with runnable object
		Thread t1 = new Thread(obj1);
		Thread t2 = new Thread(obj2);
		
		// manipulating threading properties
		System.out.println(t1.getName());
		System.out.println(t2.getName());

		t1.setName("t1");
		t2.setName("t2");
		
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		
		System.out.println("Thread t1 deafult priority:" + t1.getPriority());
		t1.setPriority(Thread.MAX_PRIORITY);
		System.out.println("Thread t1 user-defined priority:" + t1.getPriority());
		//
		
		// start the thread (it will call run() method of thread)
		t1.start();
		t2.start();
		
		// this will tell main thread to join after their execution completes.
		t1.join();
		t2.join();
		
		System.out.println("Count: " + c.count);
	}
}




