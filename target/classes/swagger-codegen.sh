#!/bin/sh
mkdir ./swagger-codegen-output/
swagger-codegen generate -i swagger30.json -l jaxrs-jersey -o ./swagger-codegen-output/
