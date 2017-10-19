#version 150 core

//Inputs
in vec3 position;

//Outputs
out vec3 color;

void main() {

  gl_Position = vec4(position, 1.0f);
  color = vec3(position.x + 0.5, 1.0, position.y + 0.5);

}