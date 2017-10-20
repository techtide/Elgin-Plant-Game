package treeEngine.rendering;

import elgin.data.Reference;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.newdawn.slick.util.Log;

public class DisplayManager {

    /**
     * Creates display for program, sets title and size among other things.
     */
    public static void createDisplay() {

        ContextAttribs attribs = new ContextAttribs(3, 3).withForwardCompatible(true).withProfileCore(true);

        try {

            Display.setDisplayMode(new DisplayMode(Reference.WINDOW_SIZE.width, Reference.WINDOW_SIZE.height));
            Display.create(new PixelFormat().withDepthBits(24), attribs);
            Display.setTitle(Reference.WINDOW_TITLE);

        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, Reference.WINDOW_SIZE.width, Reference.WINDOW_SIZE.height);
        Log.setVerbose(false);

    }

    /**
     * Updates display, sets FPS max to 120
     */
    public static void updateDisplay() {

        Display.sync(Reference.FPS_CAP);
        Display.update();

    }

    /**
     * Destroys display and closes window.
     */
    public static void closeDisplay() {
        Display.destroy();
    }

}
