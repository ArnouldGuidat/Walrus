/*
Copyright (C) 2015  Arnould GUIDAT

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation; version 3 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package fr.beaftech.walrus;

import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player {

	private String tmpDir = "";
	private static final String tmpFile = "tmp";

	private String dir = "";
	private static final String dot = "dot";
	private static final String dash = "line";
	private static final String symb = "blank_e";
	private static final String letter = "blank_l";
	private static final String word = "blank_w";

	private static final String ext = ".wav";

	public void generateSound(String message) {

		AudioInputStream stream = initStream(dir + symb + ext);
		for (char c : message.toCharArray()) {

			switch (c) {

			case '.':
				;
				stream = concat(stream, dir + dot + ext);
				break;

			case '_':

				stream = concat(stream, dir + dash + ext);
				break;

			case '\'':

				stream = concat(stream, dir + letter + ext);
				break;

			case '|':

				stream = concat(stream, dir + word + ext);
				break;

			}

			// stream = concat(stream, dir+symb+ext);

		}
		saveStream(stream);

		
	}
	
	public void playSound(){
		playSound(tmpDir + tmpFile + ext);
	}

	public String getTmpDir() {
		return tmpDir;
	}

	public void setTmpDir(String tmpDir) {
		this.tmpDir = tmpDir;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
		if (tmpDir == null || tmpDir.isEmpty()) {
			tmpDir = dir;
		}
	}

	private void saveStream(AudioInputStream stream) {
		try {
			AudioSystem.write(stream, AudioFileFormat.Type.WAVE, new File(
					tmpDir + tmpFile + ext));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private AudioInputStream initStream(String path) {
		try {
			return AudioSystem.getAudioInputStream(new File(path));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private AudioInputStream concat(AudioInputStream clip1,
			AudioInputStream clip2) {

		AudioInputStream appendedFiles = new AudioInputStream(
				new SequenceInputStream(clip1, clip2), clip1.getFormat(),
				clip1.getFrameLength() + clip2.getFrameLength());

		return appendedFiles;
	}

	private AudioInputStream concat(AudioInputStream clip1, String path) {
		AudioInputStream clip2;
		try {
			clip2 = AudioSystem.getAudioInputStream(new File(path));
			return concat(clip1, clip2);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void playSound(String path) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(path));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			//TODO this is hazardous !!
			while(clip.getFramePosition()<clip.getFrameLength()){}
			

		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

}
