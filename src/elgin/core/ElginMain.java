package elgin.core;

import org.lwjgl.opengl.Display;
import treeEngine.rendering.EngineManager;

public class ElginMain {

    public static void main(String[] args) {
        EngineManager.init();

        //Main loop
        while(!Display.isCloseRequested()) {
            EngineManager.update();
        }

        EngineManager.stop();
    }

}
