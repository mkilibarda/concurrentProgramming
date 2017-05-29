
package snakeGame;

public class Buffer {
	// Size of the buffer
	private int N ;
	// The buffer is implemented as an array
	private int[] B;
	// The pointers to the append and take positions
	private int InPtr = 0, OutPtr = 0;
	// The number of items in the buffer
	private int Count = 0;
	// Constructor takes the size as a parameter
	public Buffer(int size) {
		N = size;

		// initialise the array
		B = new int[N];
	} 
	// we need to make sure only one thread is appending at a time
	// so we use the built in monitor
	public synchronized void append(int value) {
		// If the buffer is full we cannot append to it
		if (Count == N) {
			// block this thread because the buffer is full
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//			}
		}
		 else {
		// Place the value in the buffer
		B[InPtr] = value;
		// print out a debug message
		System.out.println(" "+Thread.currentThread().getName()+" added "+value+" at "+InPtr+" Count was= "+Count);
		// increment the pointer
		// the pointer must wrap around to the start
		InPtr = (InPtr + 1) % N;
		// Update the count
		Count = Count + 1;
		// If this is the first item added will the consumer thread know?
		this.notifyAll(); // } closing brace no longer needed
		 }
	}

	public synchronized int take () {
		if (Count==0) {
			return 0;
//			try { wait();
//			} catch (InterruptedException e) {}
		}else{
		int I = B[OutPtr];
		System.out.println(" "+Thread.currentThread().getName()+" removed "+I+" at "+OutPtr+" Count was= "+Count);
		OutPtr = (OutPtr+1) % N;
		Count = Count-1;
		notifyAll();
		return I;
		}
	}
}