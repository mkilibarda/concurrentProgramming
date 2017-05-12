package snakeGame;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
	
	private BlockingQueue taskQueue = null;
    private List<WorkerThread> threads = new ArrayList<WorkerThread>();
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads, int maxNoOfTasks){
        taskQueue = new BlockingQueue(maxNoOfTasks);

        for(int i=0; i<noOfThreads; i++){
           threads.add(new WorkerThread(taskQueue));
        }
        for(WorkerThread thread : threads){
            thread.start();
        }
    }
    public synchronized void  execute(Snake task) throws Exception{
        if(this.isStopped) throw
            new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.enqueue(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(WorkerThread thread : threads){
           thread.doStop();
        }
    }

}

    
