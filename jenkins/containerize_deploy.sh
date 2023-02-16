#!/bin/bash

function containerize() {
    LANGUAGE="$1"
    if test -f "Dockerfile"
    then
        docker build -t "registry/whanos-$LANGUAGE" .
    else
        docker build -t "registry/whanos-$LANGUAGE" -f /images/$LANGUAGE/Dockerfile.standalone .
    fi
}

# function deploy() {
#     ;
# }