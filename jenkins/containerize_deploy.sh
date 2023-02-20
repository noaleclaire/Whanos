#!/bin/bash

function containerize() {
    LANGUAGE="$1"
    az login --service-principal -u $APP_ID -p $PASSWORD --tenant $TENANT_ID
    if test -f "Dockerfile"
    then
        az acr build --image "whanos-$LANGUAGE" --file Dockerfile .
    else
        az acr build --image "whanos-$LANGUAGE" --file /images/$LANGUAGE/Dockerfile.standalone .
    fi
}

# function deploy() {
#     ;
# }