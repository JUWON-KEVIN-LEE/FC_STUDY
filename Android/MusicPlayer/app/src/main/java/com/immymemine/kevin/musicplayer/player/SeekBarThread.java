package com.immymemine.kevin.musicplayer.player;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by quf93 on 2017-10-12.
 */

public class SeekBarThread extends Thread{
    private SeekBarThread() { }
    private static SeekBarThread thread;
    public static SeekBarThread getInstance() {
        if(thread == null)
            thread = new SeekBarThread();
        return thread;
    }

    private boolean runFlag = true;
    public void setStop() {
        runFlag = false;
    }

    public void run() {
        while(runFlag) {
            for(IObserver o : observers)
                o.setProgress();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<IObserver> observers = new CopyOnWriteArrayList<>();

    public void add(IObserver observer) {
        observers.add(observer);
    }

    public void remove(IObserver observer) {
        observers.remove(observer);
    }

    public interface IObserver {
        public void setProgress();
    }
}
