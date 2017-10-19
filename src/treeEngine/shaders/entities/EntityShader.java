package treeEngine.shaders.entities;

import elgin.data.Reference;
import treeEngine.shaders.ShaderProgram;

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
    }

    /**
     * Gets locations for uniform variables
     */
    @Override
    protected void getAllUniformLocations() {

    }

}
