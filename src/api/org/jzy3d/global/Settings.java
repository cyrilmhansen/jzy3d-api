package org.jzy3d.global;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import jogamp.newt.driver.awt.AWTDisplay;

import org.jzy3d.plot3d.rendering.canvas.ICanvas;

import com.jogamp.newt.Display;
import com.jogamp.newt.NewtFactory;
import com.jogamp.newt.Screen;

/**
 * {@link Settings} is a singleton that holds general settings that configure
 * Imaging classes instantiation.
 */
public class Settings {
	private Settings() {

		if (!(GLProfile.isAvailable(GLProfile.GL2) || GLProfile
				.isAvailable(GLProfile.GL2ES2))) {
			throw new UnsupportedOperationException(
					"Jzy3d requires OpenGL 2 or OpenGL 2 ES 2");
		}

		if (GLProfile.isAvailable(GLProfile.GL2)) {
			// Preferred profile = GL2
			glProfile = GLProfile.get(GLProfile.GL2);
		} else {
			// second option for Android = GL2ES2
			glProfile = GLProfile.get(GLProfile.GL2ES2);
		}

		glCapabilities = new GLCapabilities(glProfile);

		// glCapabilities.setAlphaBits(8);
		setHardwareAccelerated(false);

		// TODO Choose here somehow between NewtDisplay & AWTDisplay		
		display = NewtFactory.createDisplay(null);
		screen = NewtFactory.createScreen(display, 0);
		

	} // disable default public constructor

	/** Return the single allowed instance of Settings. */
	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}

	/**
	 * Modifies the acceleration status for all {@link ICanvas.Canvas}
	 * instantiations. This doesn't modify the status of canvases that have
	 * allready been instantiated.
	 */
	public void setHardwareAccelerated(boolean hardwareAccelerated) {
		glCapabilities.setHardwareAccelerated(hardwareAccelerated);
	}

	/** Returns true if hardware acceleration is used for 3d graphics. */
	public boolean isHardwareAccelerated() {
		return glCapabilities.getHardwareAccelerated();
	}

	/** Returns a copy of the current GL2 capabilities. */
	public GLCapabilities getGLCapabilities() {
		return (GLCapabilities) glCapabilities.clone();
	}
	
	public Screen getScreen() {
		return screen;
	}

	public String toString() {
		return "HardwareAcceleration = " + isHardwareAccelerated() + "\n";
	}

	private static Settings instance;

	// members
	private GLCapabilities glCapabilities;

	private GLProfile glProfile;

	private Display display;

	private Screen screen;


}
