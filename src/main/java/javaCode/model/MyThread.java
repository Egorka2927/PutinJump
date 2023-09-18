package javaCode.model;

public class MyThread extends Thread{
    private Map map;
    public MyThread(Map map) {
        this.map = map;
    }



    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                map.updatePutinPosition();
                try {
                    Thread.sleep(1000 / 240);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                delta--;
            }
        }
    }
}
