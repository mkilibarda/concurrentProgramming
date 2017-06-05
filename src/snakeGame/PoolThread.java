
package snakeGame;

public class PoolThread extends Thread {
	
	//Declare blocking queue
	private BlockingQueue taskQueue = null;
	private boolean isStopped = false;
	
	/**
	 * Constructor for the PoolThread
	 * @param queue
	 */
	public PoolThread(BlockingQueue queue) {
		taskQueue = queue;
	}
	
	/**
	 * Extracts the threads from the queue to execute a runnable
	 */
	public void run() {
		while (!isStopped()) {
			try {
				Runnable runnable = (Runnable) taskQueue.dequeue();
				runnable.run();
			}
			catch (Exception e) {
				// log or otherwise report exception,
				// but keep pool thread alive.
			}
		}
	}
	
	/**
	 * Stops the threads
	 */
	public synchronized void doStop() {
		isStopped = true;
		// break pool thread out of dequeue() call.
		this.interrupt();
	}
	/*
	 * Boolean to check if the thread is stopped
	 */
	public synchronized boolean isStopped() {
		return isStopped;
	}
}
