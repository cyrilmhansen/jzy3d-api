package org.jzy3d.plot3d.primitives;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.jzy3d.plot3d.rendering.compat.GLES2CompatUtils;

/**
 * Supports additional settings
 * 
 * @author Martin Pernollet
 */
public class Polygon extends AbstractGeometry {

	/**
	 * Initializes an empty {@link Polygon} with face status defaulting to true,
	 * and wireframe status defaulting to false.
	 */
	public Polygon() {
		super();
	}

	protected void begin(GL gl) {
    	if (gl.isGL2()) {
        gl.getGL2().glBegin(GL2.GL_POLYGON);
    	} else {
    		GLES2CompatUtils.glBegin(GL2.GL_POLYGON);
    	}
    }
}
