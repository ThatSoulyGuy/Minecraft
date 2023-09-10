#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec3 aColor;
layout (location = 2) in vec2 aTexCoords;

out vec3 fragPos;
out vec2 texCoords;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

void main()
{
    fragPos = vec3(model * vec4(aPos, 1.0));
    texCoords = vec2(aTexCoords.x - 1.0, aTexCoords.y - 1.0);

    gl_Position = projection * view * vec4(fragPos, 1.0);
}