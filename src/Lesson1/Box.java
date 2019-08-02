package Lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fullBox;

    public Box() {
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
}
