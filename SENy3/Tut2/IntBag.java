package Tut2;

import java.util.HashSet;
import java.util.Set;

// Base class representing an IntSet
class IntSet {
    private Set<Integer> elements;

    public IntSet() {
        elements = new HashSet<>();
    }

    public void insert(int element) {
        elements.add(element);
    }

    public void remove(int element) {
        elements.remove(element);
    }

    public boolean contains(int element) {
        return elements.contains(element);
    }

    public int size() {
        return elements.size();
    }
}

// Subclass representing an IntBag (a subtype of IntSet)
class IntBag extends IntSet {
    public void insertMultiple(int element, int count) {
        for (int i = 0; i < count; i++) {
            insert(element);
        }
    }
}


