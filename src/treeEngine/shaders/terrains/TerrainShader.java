package treeEngine.shaders.terrains;

import elgin.data.Reference;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import treeEngine.entities.Camera;
import treeEngine.shaders.ShaderProgram;
import treeEngine.shaders.UniformList;
import treeEngine.shaders.uniforms.Uniform;
import treeEngine.shaders.uniforms.UniformMat4;
import treeEngine.toolbox.Maths;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Shader for all terrains in world.
 */
public class TerrainShader extends ShaderProgram {

    public TerrainShader() {
        super(Reference.TERRAIN_SHADER_VERTEX_FILE, Reference.TERRAIN_SHADER_FRAGMENT_FILE);
    }

    /**
     * Binds attributes for terrainVSH.glsl
     */
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    /**
     * Calls UniformList.initTerrainUniforms(programID) to init uniforms correctly.
     * You can do it here too, but its all in one spot over there.
     */
    @Override
    protected void initUniforms() {
        UniformList.initTerrainUniforms(this.getProgramID());
    }

    /**
     * Loads the camera's view matrix to the shader to transform objects.
     * @param camera - the camera that is used for the view matrix.
     */
    public void loadCameraViewMatrix(Camera camera) {
        Matrix4f matrix = Maths.createViewMatrix(camera);
        UniformList.terrainViewMatrix.loadData(matrix);
    }

}
