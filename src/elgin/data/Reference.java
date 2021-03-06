package elgin.data;

import org.lwjgl.util.vector.Vector4f;

import java.awt.*;

public class Reference {

    //Display Manager
    public static final String VERSION = "0.0.1";
    public static final String WINDOW_TITLE = "Elgin v" + VERSION;
    public static final Dimension WINDOW_SIZE = new Dimension(1280, 720);
    public static final int FPS_CAP = 120;

    //Master Renderer
    //For some reason if there isnt a skybox, this is the color it uses (Default AQUAish)
    public static final Vector4f DEFAULT_BACKGROUND_COLOR = new Vector4f(0.698f, 0.815f, 1f, 1f);
    public static final float FOV = 70;
    public static final float NEAR_PLANE = 0.1f;
    public static final float FAR_PLANE = 1000f;
    public static final float UNIVERSAL_AMBIENT_LIGHT = 0.2f;

    //Camera
    public static final float CAM_SHIFT = 2.5f;

    //Terrains
    public static final float TERRAIN_SIZE = 800;
    public static final int TERRAIN_VERTEX_COUNT = 128;

    //OBJ Loader
    public static final String OBJ_LOADER_RES_LOC = "/assets/models/";

    //Loader
    public static final int LOADER_TEXTURES_FOLDER = 0; // Not needed just a default value.
    public static final int LOADER_MAPS_FOLDER = 1;
    public static final int LOADER_NORMALS_FOLDER = 2;
    public static final int LOADER_FONTS_FOLDER = 3;
    public static final int LOADER_PARTICLES_FOLDER = 4;
    public static final int LOADER_SPECULARS_FOLDER = 5;

    //Shaders
    public static final String ENTITY_SHADER_VERTEX_FILE = "/treeEngine/shaders/entities/entityVSH.glsl";
    public static final String ENTITY_SHADER_FRAGMENT_FILE = "/treeEngine/shaders/entities/entityFSH.glsl";

    public static final String TERRAIN_SHADER_VERTEX_FILE = "/treeEngine/shaders/terrains/terrainVSH.glsl";
    public static final String TERRAIN_SHADER_FRAGMENT_FILE = "/treeEngine/shaders/terrains/terrainFSH.glsl";

}
