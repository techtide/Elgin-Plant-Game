#version 150 core

in vec2 passTextureCoords;

out vec4 outColor;

uniform sampler2D textureSampler;

void main() {

  outColor = texture(textureSampler, passTextureCoords);


}
