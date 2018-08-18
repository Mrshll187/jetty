package xxx.xxx.util;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Notifier {

	public static void playSound() throws Exception {
		
		Thread x = new Thread(new Runnable() {
			
			public void run() {
			
				try {
					
					File file = new File(getClass().getResource("/sound.wav").getFile());
					
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
					AudioFormat format = audioInputStream.getFormat();
					
					DataLine.Info line = new DataLine.Info(Clip.class, format);
					
					Clip clip = (Clip)AudioSystem.getLine(line);
					
					clip.addLineListener(new LineListener() {
						
						@Override
						public void update(LineEvent event) {
						
							if(event.getType() == LineEvent.Type.STOP)
								event.getLine().close();
						}
					});
					
					clip.open(audioInputStream);
					clip.start();
				}
				catch(Exception e) {}
			}
		});
		
		x.setName("Sound-Thread");
		x.start();
	}
}
