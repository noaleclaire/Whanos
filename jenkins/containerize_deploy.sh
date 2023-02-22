#!/bin/bash

function containerize() {
    LANGUAGE="$1"
    az login --service-principal -u $AZ_APP_ID -p $AZ_PASSWORD --tenant $AZ_TENANT_ID
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