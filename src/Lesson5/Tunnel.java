package Lesson5;

import java.util.concurrent.Semaphore;


public class Tunnel extends Stage {
    Semaphore barrier;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        barrier =   new Semaphore(MainClass.CARS_COUNT/2);
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                barrier.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                barrier.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
