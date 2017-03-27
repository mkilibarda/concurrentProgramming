package snakeGame;

import java.util.ArrayList;
import java.util.List;

public class Server {

	private BlockingQueue taskQueue = null;
    private List<ThreadPool> threads = new ArrayList<ThreadPool>();
    private boolean isStopped = false;

    public Server(int noOfThreads, int maxNoOfTasks){
        taskQueue = new BlockingQueue(maxNoOfTasks);

        for(int i=0; i<noOfThreads; i++){
            threads.add(new ThreadPool(taskQueue));
        }
        for(ThreadPool thread : threads){
            thread.start();
        }
    }

    public synchronized void  execute(Runnable task) throws Exception{
        if(this.isStopped) throw
            new IllegalStateException("Server is stopped");

        this.taskQueue.enqueue(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(ThreadPool thread : threads){
           thread.doStop();
        }
    }

}