package Lesson4;

public class MainHW4 {
    public static void main(String[] args) {
      Printer printer = new Printer();
      Thread t1 = new Thread(()->printer.PrintA());
      Thread t2 = new Thread(()->printer.PrintB());
      Thread t3 = new Thread(()->printer.PrintC());

      t1.start();
      t2.start();
      t3.start();
    }


    public static class Printer {
        public volatile char letter = 'A';

        public synchronized void PrintA() {
            try {

                for (int i = 0; i < 5; i++) {
                    while (letter != 'A') {
                        this.wait();
                    }
                    System.out.print('A');
                    letter = 'B';
                    this.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void PrintB() {
            try {

                for (int i = 0; i < 5; i++) {
                    while (letter != 'B') {
                        this.wait();
                    }
                    System.out.print('B');
                    letter = 'C';
                    this.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void PrintC() {
            try {

                for (int i = 0; i < 5; i++) {
                    while (letter != 'C') {
                        this.wait();
                    }
                    System.out.print('C');
                    letter = 'A';
                    this.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
