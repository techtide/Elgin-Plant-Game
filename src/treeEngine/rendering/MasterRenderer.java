package treeEngine.rendering;

import treeEngine.entities.Camera;
import treeEngine.entities.Entity;
import treeEngine.entities.Light;
import treeEngine.models.TexturedModel;
import treeEngine.shaders.UniformList;
import treeEngine.shaders.entities.EntityShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private EntityShader entityShader = new EntityShader();
    private EntityRenderer entityRenderer = new EntityRenderer(entityShader);

    private HashMap<TexturedModel, List<Entity>> entities = new HashMap<>();

    /**
     * Renders scene, handles all rendering here.
     * @param sun - Light source in scene
     * @param camera - Camera that views scene
     */
    public void render(Light sun, Camera camera) {
        entityRenderer.prepare();
        entityShader.start();

        UniformList.entityLightPosition.loadData(sun.getPosition());
        UniformList.entityLightColor.loadData(sun.getColor());
        entityShader.loadCameraViewMatrix(camera);
        entityRenderer.render(entities);

        entityShader.stop();
        entities.clear();
    }

    public void processEntity(Entity entity) {
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch != null) {
            batch.add(entity);
        } else {
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }
    }

    /**
     * Cleans up rendering.
     */
    public void cleanUp() {
        entityShader.cleanUp();
    }
}
