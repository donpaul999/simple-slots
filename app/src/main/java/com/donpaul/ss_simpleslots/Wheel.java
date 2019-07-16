package com.donpaul.ss_simpleslots;

public class Wheel extends Thread {
    interface WheelListener{
        void newImage(int img);
    }
    private static int[] imgs={R.drawable.slot1, R.drawable.slot2, R.drawable.slot3, R.drawable.slot4, R.drawable.slot5, R.drawable.slot6};
    public int currentIndex;
    private WheelListener wheelListener;
    private long frameDuration;
    private long startIn;
    private boolean isStarted;

    public Wheel(WheelListener wheelListener, long frameDuration, long startIn)
    {
        this.wheelListener = wheelListener;
        this.frameDuration = frameDuration;
        this.startIn = startIn;
        isStarted = true;
        currentIndex = 0;
    }

    public void nextImg()
    {
        currentIndex++;
        if (currentIndex == imgs.length)
            currentIndex = 0;
    }

    @Override

    public void run(){
        try{
            Thread.sleep(startIn);
        }
        catch (InterruptedException e){
        }
        while(isStarted) {
            try {
                Thread.sleep(frameDuration);
            } catch (InterruptedException e) {
            }
            nextImg();
            if (wheelListener != null) {
                wheelListener.newImage(imgs[currentIndex]);
            }
        }
    }

    public void stopWheel()
    {
        isStarted = false;
    }
}

