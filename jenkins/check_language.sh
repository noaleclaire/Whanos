#!/bin/bash

source ./containerize_deploy.sh

LANGUAGE_NAME = ""
LANGUAGE_NB = 0

if test -f Makefile
then
    LANGUAGE_NAME = "c"
    LANGUAGE_NB = `expr $LANGUAGE_NAME + 1`
fi

if test -f app/pom.xml
then
    LANGUAGE_NAME = "java"
    LANGUAGE_NB = `expr $LANGUAGE_NAME + 1`
fi

if test -f package.json
then
    LANGUAGE_NAME = "javascript"
    LANGUAGE_NB = `expr $LANGUAGE_NAME + 1`
fi

if test -f requirements.txt
then
    LANGUAGE_NAME = "python"
    LANGUAGE_NB = `expr $LANGUAGE_NAME + 1`
fi

if test -f app/main.bf
then
    LANGUAGE_NAME = "befunge"
    LANGUAGE_NB = `expr $LANGUAGE_NAME + 1`
fi

if (($LANGUAGE_NB == 0 || $LANGUAGE_NB > 1))
then
    echo "Build error: no or multiple language(s) found"
    exit 1
fi

containerize $LANGUAGE_NAME