package snippet;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

public class Snippet {
	public static void main(String[] args) throws LineUnavailableException {
		Mixer mixer = null;
		Line line = mixer.getLine(mixer.getLineInfo());
		boolean opened = line.isOpen() || line instanceof Clip;
		if(!opened){
		     System.out.println("Line is not open, trying to open it...");
		     line.open();
		     opened = true;
		}
		if(line.isControlSupported(FloatControl.Type.VOLUME)){
		          FloatControl volumeCtrl = (FloatControl)line.getControl(FloatControl.Type.VOLUME);
		               System.out.println("Current volume is: "+volumeCtrl.getValue());
		}
	}
	
}

