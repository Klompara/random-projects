package typer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public class Main {
    private Robot robot;

    public Main() {
        try {
            this.robot = new Robot();
            
            int reply = JOptionPane.showConfirmDialog(null, "Autotyper by Kleinlercher Michael.\n Do you want to type something?", "Autotyper :)", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.NO_OPTION) System.exit(0);
            String text = JOptionPane.showInputDialog("Text der geschrieben wird: ");
            int repeat = Integer.parseInt(JOptionPane.showInputDialog("Wiederholungen: "));
            int waitPause = Integer.parseInt(JOptionPane.showInputDialog("Wartezeit vor start (ms) : "));
            int waitDuring = Integer.parseInt(JOptionPane.showInputDialog("Wartezeit zwischen den Tasten (ms): "));
            if(waitPause < 0) waitPause = 0;
            if(waitDuring < 0) waitDuring = 0;
            boolean isEnter = (JOptionPane.showConfirmDialog(null, "Enter nach text?", "", JOptionPane.YES_NO_OPTION)
            		== JOptionPane.YES_OPTION);
            Thread.sleep(waitPause);
            for(long i = 0; i < repeat; i++) {
                type(text, waitDuring, isEnter);	
            }
        } catch (AWTException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void type(String text, int waitDuring, boolean isEnter) {
        char c;
        for (int ii = 0; ii < text.length(); ii++) {
            c = text.charAt(ii);

            if (c <= 31 || c == 129) {
                pressControlKey(c);
            } else {
                typeAsciiCode(c);
            }
            try {
				Thread.sleep(waitDuring);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        if(isEnter) {
        	robot.keyPress(KeyEvent.VK_ENTER);
        	robot.keyRelease(KeyEvent.VK_ENTER);
        }
    }

    private void pressControlKey(char c) {
        robot.keyPress(c);
        robot.keyRelease(c);
    }

    private void typeAsciiCode(char c) {
        robot.keyPress(KeyEvent.VK_ALT);

        String asciiCode = Integer.toString(c);
        for (int i = 0; i < asciiCode.length(); i++) {
            c = (char) (asciiCode.charAt(i) + '0');
            robot.keyPress(c);
            robot.keyRelease(c);
        }
        robot.keyRelease(KeyEvent.VK_ALT);
    }
    public static void main(String[] args) {new Main();}
}
