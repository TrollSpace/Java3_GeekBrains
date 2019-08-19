package Lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Race {
    private ArrayList<Stage> stages;
    CountDownLatch start;
    CountDownLatch finish;

    public ArrayList<Stage> getStages() { return stages; }
    public Race(int carsCount, Stage... stages) {
        start = new CountDownLatch(carsCount);
        finish = new CountDownLatch(carsCount);
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
