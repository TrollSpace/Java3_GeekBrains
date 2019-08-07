package Lesson1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fullBox;

    public Box() {
        fullBox = new ArrayList<>();
    }

    public void putFruitToBox(T fruit) {
        fullBox.add(fruit);
    }

    public float getWeight() {
        float weight = 0;
        for (T fruit : fullBox) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public boolean compare(Box box){
        return this.getWeight() == box.getWeight();
    }

    public void replaceFruits(Box box){
        box.fullBox.addAll(fullBox);
        fullBox.clear();
    }
}
