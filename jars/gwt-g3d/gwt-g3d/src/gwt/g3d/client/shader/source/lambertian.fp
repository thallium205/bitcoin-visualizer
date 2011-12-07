#ifdef GL_ES
precision highp float;
#endif
uniform vec4 uDiffuseColor;

varying vec3 vNormal, vLightDir;

void main() {
  float nDotL = dot(normalize(vNormal), normalize(vLightDir));
  gl_FragColor = vec4(uDiffuseColor.rgb * max(0.0, nDotL), 1.0);
}