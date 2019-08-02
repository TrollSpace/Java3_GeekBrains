package Lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruitsInBox;

    public Box() {
    }

    public void putFruitToBox(T fruit) {
        fruitsInBox.add(fruit);
    }

    public float getWeight() {
        float weight = 0;
        for (T fruit : fruitsInBox) {
            weight += fruit.getWeight();
        }
        return weight;
    }
}
