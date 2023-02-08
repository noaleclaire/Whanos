folder('Whanos base images') {
        displayName('Whanos base images')
        description('Folder for whanos based images')
}
folder('Projects') {
        displayName('Projects')
        description('Folder projects')
}

// fresstyle job linked to based image 

freeStyleJob('/Whanos base images/whanos-c') {
    parameters {
    }
    steps {
        // build the corresponding base image (C langage)
    }
}
freeStyleJob('/Whanos base images/whanos-java') {
    parameters {
    }
    steps {
        // build the corresponding base image (java langage)
    }
}
freeStyleJob('/Whanos base images/whanos-javascript') {
    parameters {
    }
    steps {
        // build the corresponding base image (javascript langage)
    }
}
freeStyleJob('/Whanos base images/whanos-python') {
    parameters {
    }
    steps {
        // build the corresponding base image (python langage)
    }
}
freeStyleJob('/Whanos base images/whanos-befunge') {
    parameters {
    }
    steps {
        // build the corresponding base image (befunge langage)
    }
}

freeStyleJob('/Whanos base images/Build all base images') {
    parameters {
    }
    steps {
        // triggers all base images build jobs.
    }
}

freeStyleJob('link-project') {
    parameters {
    }
    steps {
        // create jobs with specifications
    }
}