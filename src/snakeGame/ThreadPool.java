package snakeGame;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
	
	//private BlockingQueue taskQueue = null;
    private List<Thread> threads = new ArrayList<Thread>();
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads, int maxNoOfTasks){
        //taskQueue = new BlockingQueue(maxNoOfTasks);

        for(int i=0; i<noOfThreads; i++){
           threads.add(new Thread(new Player()));
        }
        for(Thread thread : threads){
            thread.start();
        }
    }
//    
//    //to place the threads back into the queue to be reused
//    public synchronized void  execute(Runnable task) throws Exception{
//        if(this.isStopped) throw
//            new IllegalStateException("Server is stopped");
//
//        //this.taskQueue.enqueue(task);
//    }
    


    
}