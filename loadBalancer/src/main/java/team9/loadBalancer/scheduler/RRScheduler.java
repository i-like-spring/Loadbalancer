package team9.loadBalancer.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import team9.loadBalancer.domain.Server;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class RRScheduler implements ClientScheduler{

    private Queue<Server> serverList; //lb에서 받을지 아니면 init을 할지 이건 아직 못정했음

    public RRScheduler() { //의존성을 주입을 받을건지 아니면 그냥 만들어서 쓸건지~ 아직 결정을 못하겠음
        this.serverList = new LinkedList<>();
        initServerList();
    }

    @Override
    public Server schedule() {
        Server front = serverList.poll();
        serverList.offer(front);
        return front;
    }


    public void initServerList(){
        Server s1 = new Server("http://localhost:8081");
        Server s2 = new Server("http://localhost:8082");
        serverList.offer(s1);
        serverList.offer(s2);
    }



}
