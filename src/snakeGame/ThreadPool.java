
package snakeGame;

import java.util.ArrayList;
import java.util.List;


public class ThreadPool {
	
	//Declare blocking queue
	private BlockingQueue taskQueue = null;
	private List<PoolThread> threads = new ArrayList<PoolThread>();
	private boolean isStopped = false;
	
	/**
	 * Constructor for the threadPool. Passes through the number of
	 * threads used and the max amount of tasks
	 * @param noOfThreads
	 * @param maxNoOfTasks
	 */
	public ThreadPool(int noOfThreads, int maxNoOfTasks) {
		taskQueue = new BlockingQueue(maxNoOfTasks);
		
		for (int i = 0; i < noOfThreads; i++) {
			threads.add(new PoolThread(taskQueue));
		}
		for (PoolThread thread : threads) {
			thread.start();
		}
	}
	/**
	 * Executes task if threads are full the task is placed onto the blocking queue
	 * @param task
	 * @throws Exception
	 */
	public synchronized void execute(Runnable task) throws Exception {
		if (this.isStopped) throw new IllegalStateException("ThreadPool is stopped");
		
		this.taskQueue.enqueue(task);
	}
	
	/**
	 * Stops the threads
	 */
	public synchronized void stop() {
		this.isStopped = true;
		for (PoolThread thread : threads) {
			thread.doStop();
		}
	}
	
}
