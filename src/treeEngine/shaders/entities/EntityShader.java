package treeEngine.shaders.entities;

import elgin.data.Reference;
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
 * Shader for all entities in world. (Non-normal mapped ones atleast)
 */
public class EntityShader extends ShaderProgram {

    public EntityShader() {
        super(Reference.ENTITY_SHADER_VERTEX_FILE, Reference.ENTITY_SHADER_FRAGMENT_FILE);
    }

    /**
     * Binds attributes for entityVSH.glsl
     */
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    /**
     * Calls UniformList.initEntityUniforms(programID) to init uniforms correctly.
     * You can do it here too, but its all in one spot over there.
     */
    @Override
    protected void initUniforms() {
        UniformList.initEntityUniforms(this.getProgramID());
    }

    /**
     * Loads the camera's view matrix to the shader to transform objects.
     * @param camera - the camera that is used for the view matrix.
     */
    public void loadCameraViewMatrix(Camera camera) {
        Matrix4f matrix = Maths.createViewMatrix(camera);
        UniformList.entityViewMatrix.loadData(matrix);
    }

}
