package com.examplexyy.demo.thread;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/2 17:56
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class Race implements Runnable{
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            boolean isGameOver = isGameOver(i);
            if (isGameOver){
                System.out.println(Thread.currentThread().getName() +"--winner:" + winner);
               break;
            }
            System.out.println(Thread.currentThread().getName() +"--》跑了" + i + "步" );
        }
    }

    private boolean isGameOver(int steps) {
        if(winner != null){
            return true;
        }else{
            if (steps >= 100){
                winner = Thread.currentThread().getName();
                System.out.println("winner是:" + Thread.currentThread().getName());
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();

    }
}
