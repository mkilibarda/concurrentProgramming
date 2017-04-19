package snakeGame;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
	
	private BlockingQueue taskQueue = null;
    private List<Players> threads = new ArrayList<Players>();
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads, int maxNoOfTasks){
        taskQueue = new BlockingQueue(maxNoOfTasks);

        for(int i=0; i<noOfThreads; i++){
            threads.add(new Players(taskQueue));
        }
        for(Players thread : threads){
            thread.start();
        }
    }
    
    //to place the threads back into the queue to be reused
    public synchronized void  execute(Runnable task) throws Exception{
        if(this.isStopped) throw
            new IllegalStateException("Server is stopped");

        this.taskQueue.enqueue(task);
    }
    
    //use this code when time is finished for players to make move
    public synchronized void stop(){
        this.isStopped = true;
        for(Players thread : threads){
           thread.doStop();
        }
    }


    
}