package willow.train.kuayue.event.client;

import net.minecraftforge.event.TickEvent;

import java.util.ArrayList;

public class ClientTickScheduler {
    public static ArrayList<Runnable> runnableList = new ArrayList<>();

    public static void onClientEarlyTick(TickEvent.ClientTickEvent tick){
        if(tick.phase != TickEvent.Phase.START)
            return;
        for (Runnable runnable : runnableList) {
            try{
                runnable.run();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        runnableList.clear();
    }

    public static void onNextTick(Runnable runnable) {
        runnableList.add(runnable);
    }
}
