package apolyakov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            logger.info("This is the {} time I say 'Hello World'. Writing some extra text to exceed 100 KB limit.", i);
            Thread.sleep(10); // Небольшая задержка
        }

        System.out.println("Logging complete. Please check the folder: target/log4j/roll-by-size/");
    }
}