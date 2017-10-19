package treeEngine.rendering;

import elgin.data.Reference;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import treeEngine.models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for loading data needed to render.
 * Ensure DisplayManager.init() is called to avoid errors.
 */
public class Loader {

    //VAO and VBO list for tracking
    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();
    private List<Integer> textures = new ArrayList<>();

    /**
     * Creates primitive model with given vertex positions to render.
     * @param positions - Vertex coordinates for model
     * @param textureCoords - Coordinates used for texturing a model.
     * @param indices - Indices for model (improves performace up to 40%)
     * @return - RawModel created.
     */
    public RawModel loadToVAO(float[] positions, float[] textureCoords, int[] indices) {
        int vaoID = createVAO();
        bindIndicesVBO(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1, 2, textureCoords);
        unbindVAO();
        return new RawModel(vaoID, indices.length);
    }

    /**
     * Loads a texture from file, under resources/assets/ folder type / file name.png
     * @param fileName - Name of the texture
     * @param folderType - Used to find the texture since it's organized.
     * @return - TextureID used for program
     */
    public int loadTexture(String fileName, int folderType) {
        Texture texture = null;

        String folderName = "textures";
        if(folderType == Reference.LOADER_MAPS_FOLDER) folderName = "maps";
        if(folderType == Reference.LOADER_NORMALS_FOLDER) folderName = "normals";
        if(folderType == Reference.LOADER_FONTS_FOLDER) folderName = "fonts";
        if(folderType == Reference.LOADER_PARTICLES_FOLDER) folderName = "particles";
        if(folderType == Reference.LOADER_SPECULARS_FOLDER) folderName = "speculars";

        try {
            texture = TextureLoader.getTexture("PNG", Class.class.getResourceAsStream("/assets/" + folderName + "/" + fileName + ".png"));
        } catch (IOException e) {
            System.err.println("Failed to load texture: " + "/assets/" + folderName + "/" + fileName + ".png");
        }

        int textureID = texture.getTextureID();
        textures.add(textureID);
        return textureID;
    }

    /**
     * Creates VAO
     * @return - The VAO ID used in the model for rendering.
     */
    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    /**
     * Deletes all VAOs and VBOs
     */
    public void cleanUp() {
        for(int vao: vaos) {
            GL30.glDeleteVertexArrays(vao);
        }

        for(int vbo: vbos) {
            GL15.glDeleteBuffers(vbo);
        }

        for(int texture: textures) {
            GL11.glDeleteTextures(texture);
        }
    }

    /**
     * This method stores data to a VBO, with the index, coord size, and data.
     * @param attributeNumber - The index of the VBO (VAO? not sure)
     * @param dimensions - The dimensions of the data
     * @param data - The data to store
     */
    private void storeDataInAttributeList(int attributeNumber, int dimensions, float[] data) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, dimensions, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /**
     * Binds model indices to a VBO for use in VAO
     * @param indices
     */
    private void bindIndicesVBO(int[] indices) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    /**
     * Stores given data to a int buffer for use
     * @param data - Data to store into buffer.
     * @return - Returns buffer with data in it.
     */
    private IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * Stores given data to a float buffer for use
     * @param data - Data to store into buffer.
     * @return - Returns buffer with data in it.
     */
    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * Unbinds VAO so no conflicts in future.
     */
    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

}
