package top.crossrun.net.api;

import java.util.ArrayList;
import java.util.List;

import top.crossrun.net.interf.RecycleAble;

public class CompositeRecycle implements RecycleAble {
    List<RecycleAble> recycleAbles;

    public CompositeRecycle() {
        recycleAbles = new ArrayList<>();
    }

    public void add(RecycleAble recycleAble) {
        recycleAbles.add(recycleAble);
    }


    @Override
    public void recycle() {
        for (RecycleAble recycle :
                recycleAbles) {
            if (recycle != null) {
                recycle.recycle();
            }
        }
        recycleAbles.clear();
        recycleAbles = null;
    }
}
