import java.text.SimpleDateFormat;
import java.util.Date;

public class MainThread {
    private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyy-MM-dd HH:mm:SSS");

    private static String getLog(String msg) {
        return "[" + sdfDate.format(new Date()) + "] Main thread: " + msg;
    }

    public static void main(String[] args) {
        Thread t = new ServerThread();
        t.start();
        System.out.println(getLog("server thread started"));

        boolean flag = true;
        while (flag) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(getLog("server still alive"));
        }
    }
}
