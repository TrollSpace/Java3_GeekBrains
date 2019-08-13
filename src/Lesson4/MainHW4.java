package Lesson4;

public class MainHW4 {
    public static void main(String[] args) {

    }


    public class Printer {
        public volatile char letter = 'A';

        public synchronized void PrintA() {
            try {

                for (int i = 0; i < 5; i++) {
                    while (letter != 'A') {
                        this.wait();
                    }
                    System.out.println('A');
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
                    System.out.println('B');
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
                    System.out.println('C');
                    letter = 'A';
                    this.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
