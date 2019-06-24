### Admin service

* Para correrlo interactivamente, primero `npm install`, y luego `nodemon app.js` dentro de admin-service
* Se utiliza el método de compilación estático, para lo cual se debe instalar `grpc-tools`, luego `cd protos` y finalmente
```grpc_tools_node_protoc --js_out=import_style=commonjs,binary:../src/generated-pbs  --grpc_out=../src/generated-pbs --plugin=protoc-gen-grpc=`which grpc_tools_node_protoc_plugin` admin_service.proto```
