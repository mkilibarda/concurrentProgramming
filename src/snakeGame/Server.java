package snakeGame;

import java.util.ArrayList;
import java.util.List;

public class Server {

	private BlockingQueue taskQueue = null;
    private List<Player> threads = new ArrayList<Player>();
    private boolean isStopped = false;

    public Server(int noOfThreads, int maxNoOfTasks){
        taskQueue = new BlockingQueue(maxNoOfTasks);

        for(int i=0; i<noOfThreads; i++){
            threads.add(new Player(taskQueue));
        }
        for(Player thread : threads){
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
        for(Player thread : threads){
           thread.doStop();
        }
    }

}