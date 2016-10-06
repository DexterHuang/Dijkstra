/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement2.SortingHandler;

import java.util.Collection;

/**
 *
 * @author Huang
 */
public class SortingHandler {

    Collection<SortableData> list;
    int biggest;
    int smallest;

    public SortingHandler() {
    }

    public void buildMaxheap(SortableData[] l) {
        int count = list.size() - 1;
        for (int i = count / 2; i >= 0; i--) {
            maxheap(l, i, count);
        }
    }

    public void maxheap(SortableData[] l, int i, int count) {
        int leftIndex = 2 * i;
        int rightIndex = 2 * i + 1;
        if (leftIndex <= count && l[leftIndex].getValue() > l[i].getValue()) {
            biggest = leftIndex;
        } else {
            biggest = i;
        }

        if (rightIndex <= count && l[rightIndex].getValue() > l[biggest].getValue()) {
            biggest = rightIndex;
        }
        if (biggest != i) {
            swap(l, i, biggest);
            maxheap(l, biggest, count);
        }
    }

    public void swap(SortableData[] l, int i, int j) {
        SortableData t = l[i];
        l[i] = l[j];
        l[j] = t;
    }

    public SortableData[] sort(Collection<SortableData> list) {
        this.list = list;
        SortableData[] l = list.toArray(new SortableData[list.size()]);
        buildMaxheap(l);
        int count = list.size() - 1;
        for (int i = count; i > 0; i--) {
            swap(l, 0, i);
            count = count - 1;
            maxheap(l, 0, count);
        }
        return l;
    }
}
