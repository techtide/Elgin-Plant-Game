package treeEngine.terrains;

import elgin.data.Reference;
import treeEngine.models.ModelTexture;
import treeEngine.models.RawModel;
import treeEngine.rendering.Loader;

public class Terrain {

    private float x;
    private float z;
    private RawModel model;
    private ModelTexture texture;

    public Terrain(int gridX, int gridZ, Loader loader, ModelTexture texture) {
        this.texture = texture;
        this.x = gridX * Reference.TERRAIN_SIZE;
        this.z = gridZ * Reference.TERRAIN_SIZE;
        this.model = generateTerrain(loader);
    }

    public float getX() {
        return x;
    }

    public float getZ() {
        return z;
    }

    public RawModel getModel() {
        return model;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    private RawModel generateTerrain(Loader loader){
        int count = Reference.TERRAIN_VERTEX_COUNT * Reference.TERRAIN_VERTEX_COUNT;
        float[] vertices = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count*2];
        int[] indices = new int[6*(Reference.TERRAIN_VERTEX_COUNT-1)*(Reference.TERRAIN_VERTEX_COUNT-1)];
        int vertexPointer = 0;
        for(int i=0;i<Reference.TERRAIN_VERTEX_COUNT;i++){
            for(int j=0;j<Reference.TERRAIN_VERTEX_COUNT;j++){
                vertices[vertexPointer*3] = (float)j/((float)Reference.TERRAIN_VERTEX_COUNT - 1) * Reference.TERRAIN_SIZE;
                vertices[vertexPointer*3+1] = 0;
                vertices[vertexPointer*3+2] = (float)i/((float)Reference.TERRAIN_VERTEX_COUNT - 1) * Reference.TERRAIN_SIZE;
                normals[vertexPointer*3] = 0;
                normals[vertexPointer*3+1] = 1;
                normals[vertexPointer*3+2] = 0;
                textureCoords[vertexPointer*2] = (float)j/((float)Reference.TERRAIN_VERTEX_COUNT - 1);
                textureCoords[vertexPointer*2+1] = (float)i/((float)Reference.TERRAIN_VERTEX_COUNT - 1);
                vertexPointer++;
            }
        }
        int pointer = 0;
        for(int gz=0;gz<Reference.TERRAIN_VERTEX_COUNT-1;gz++){
            for(int gx=0;gx<Reference.TERRAIN_VERTEX_COUNT-1;gx++){
                int topLeft = (gz*Reference.TERRAIN_VERTEX_COUNT)+gx;
                int topRight = topLeft + 1;
                int bottomLeft = ((gz+1)*Reference.TERRAIN_VERTEX_COUNT)+gx;
                int bottomRight = bottomLeft + 1;
                indices[pointer++] = topLeft;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = topRight;
                indices[pointer++] = topRight;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = bottomRight;
            }
        }
        return loader.loadToVAO(vertices, textureCoords, normals, indices);
    }

}
