#version 150 core

in vec2 passTextureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;

out vec4 outColor;

uniform sampler2D textureSampler;
uniform float ambientLight;

uniform vec3 lightColor;
uniform float shineDamper;
uniform float reflectivity;

void main() {

  vec3 unitNormal = normalize(surfaceNormal);
  vec3 unitLightVector = normalize(toLightVector);

  float nDot1 = dot(unitNormal, unitLightVector);
  float brightness = max(nDot1, ambientLight);
  vec3 diffuse = brightness * lightColor;

  vec3 unitVectorToCamera = normalize(toCameraVector);
  vec3 lightDirection = -unitLightVector;
  vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);

  float specularFactor = dot(reflectedLightDirection, unitVectorToCamera);
  specularFactor = max(specularFactor, 0.0);
  float dampedFactor = pow(specularFactor, shineDamper);
  vec3 finalSpecular = dampedFactor * reflectivity * lightColor;

  vec4 textureColor = texture(textureSampler, passTextureCoords);
  if(textureColor.a < 0.5) {
    discard;
  }

  outColor = vec4(diffuse, 1.0) * textureColor + vec4(finalSpecular, 1.0);

}
