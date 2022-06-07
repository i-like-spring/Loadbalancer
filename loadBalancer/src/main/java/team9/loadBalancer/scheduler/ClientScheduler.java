package team9.loadBalancer.scheduler;
import team9.loadBalancer.domain.Server;

public interface ClientScheduler {
    public Server schedule();
}
