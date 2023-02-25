#!/bin/bash

function containerize() {
    LANGUAGE="$1"
    APP_NAME="$2"
    AZ_APP_ID="$(grep -zoP '(\s*)az_app_id: \K.*' /jenkins/env.yaml)"
    AZ_PASSWORD="$(grep -zoP '(\s*)az_password: \K.*' /jenkins/env.yaml)"
    AZ_TENANT_ID="$(grep -zoP '(\s*)az_tenant_id: \K.*' /jenkins/env.yaml)"
    AZ_REGISTRY_NAME="$(grep -zoP '(\s*)az_registry_name: \K.*' /jenkins/env.yaml)"
    az login --service-principal -u $AZ_APP_ID -p $AZ_PASSWORD --tenant $AZ_TENANT_ID
    az acr login --name $AZ_REGISTRY_NAME
    if test -f "Dockerfile"
    then
        docker build -t $AZ_REGISTRY_NAME.azurecr.io/$APP_NAME-$LANGUAGE .
    else
        docker build -t $AZ_REGISTRY_NAME.azurecr.io/$APP_NAME-$LANGUAGE -f /images/$LANGUAGE/Dockerfile.standalone .
    fi
    docker push $AZ_REGISTRY_NAME.azurecr.io/$APP_NAME-$LANGUAGE
}

# function deploy() {
#     ;
# }