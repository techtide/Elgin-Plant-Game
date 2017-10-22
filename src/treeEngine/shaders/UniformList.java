package treeEngine.shaders;

import org.lwjgl.util.vector.Vector3f;
import treeEngine.shaders.uniforms.Uniform1f;
import treeEngine.shaders.uniforms.Uniform3f;
import treeEngine.shaders.uniforms.UniformBool;
import treeEngine.shaders.uniforms.UniformMat4;

public class UniformList {

    /* Entity Shader */
    //Matricies
    public static UniformMat4 entityTransformationMatrix;
    public static UniformMat4 entityProjectionMatrix;
    public static UniformMat4 entityViewMatrix;

    //Lighting
    public static Uniform3f entityLightPosition;
    public static Uniform3f entityLightColor;
    public static Uniform1f entityShineDamper;
    public static Uniform1f entityReflectivity;
    public static UniformBool entityUsesFakeLighting;
    public static Uniform1f universalAmbientLight;

    //

    /* Terrain Shader */
    public static UniformMat4 terrainTransformationMatrix;
    public static UniformMat4 terrainProjectionMatrix;
    public static UniformMat4 terrainViewMatrix;

    //Lighting
    public static Uniform3f terrainLightPosition;
    public static Uniform3f terrainLightColor;
    public static Uniform1f terrainShineDamper;
    public static Uniform1f terrainReflectivity;

    //

    /**
     * Initializes the entity shader uniform variables.
     * @param programID
     */
    public static void initEntityUniforms(int programID) {
        entityTransformationMatrix = new UniformMat4("transformationMatrix", programID);
        entityProjectionMatrix = new UniformMat4("projectionMatrix", programID);
        entityViewMatrix = new UniformMat4("viewMatrix", programID);

        entityLightPosition = new Uniform3f("lightPosition", programID);
        entityLightColor = new Uniform3f("lightColor", programID);
        entityShineDamper = new Uniform1f("shineDamper", programID);
        entityReflectivity = new Uniform1f("reflectivity", programID);
        entityUsesFakeLighting = new UniformBool("useFakeLighting", programID);

        universalAmbientLight = new Uniform1f("ambientLight", programID);
    }

    /**
     * Initializes the terrain shader uniform variables.
     * @param programID
     */
    public static void initTerrainUniforms(int programID) {
        terrainTransformationMatrix = new UniformMat4("transformationMatrix", programID);
        terrainProjectionMatrix = new UniformMat4("projectionMatrix", programID);
        terrainViewMatrix = new UniformMat4("viewMatrix", programID);

        terrainLightPosition = new Uniform3f("lightPosition", programID);
        terrainLightColor = new Uniform3f("lightColor", programID);
        terrainShineDamper = new Uniform1f("shineDamper", programID);
        terrainReflectivity = new Uniform1f("reflectivity", programID);

        universalAmbientLight = new Uniform1f("ambientLight", programID);
    }

}
