package org.lema.notasapp.ui.utils;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by igor on 17/04/17.
 */

public class CircularArrayList<E> extends ArrayList<E> {

    private static int next;
    private E data;

    public CircularArrayList() {
        next = 0;
    }

    public E get() {

        if(next >= super.size()) {
            next = 0;
        }
        data = super.get(next);
        next++;
        return data;
    }

}
