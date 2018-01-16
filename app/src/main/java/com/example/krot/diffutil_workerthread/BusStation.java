package com.example.krot.diffutil_workerthread;

import com.squareup.otto.Bus;

/**
 * Created by Krot on 1/15/18.
 */

public class BusStation {

    static Bus eventBus = new Bus();

    public static Bus getEventBus() {
        return eventBus;
    }
}
