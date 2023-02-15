folder('Whanos base images') {
        displayName('Whanos base images')
        description('Folder for whanos based images')
}
folder('Projects') {
        displayName('Projects')
        description('Folder projects')
}

// fresstyle job linked to based images

freeStyleJob('/Whanos base images/whanos-c') {
    description('build the C base image so that it is available for the Jenkins instance host to use.')
    steps {
        sh 'docker build -t whanos-c -f /images/c/Dockerfile.base .'
    }
}
freeStyleJob('/Whanos base images/whanos-java') {
    description('build the Java base image so that it is available for the Jenkins instance host to use.')
    steps {
        sh 'docker build -t whanos-java -f /images/java/Dockerfile.base .'
    }
}
freeStyleJob('/Whanos base images/whanos-javascript') {
    description('build the Javascript base image so that it is available for the Jenkins instance host to use.')
    steps {
        sh 'docker build -t whanos-javascript -f /images/javascript/Dockerfile.base .'
    }
}
freeStyleJob('/Whanos base images/whanos-python') {
    description('build the Python base image so that it is available for the Jenkins instance host to use.')
    steps {
        sh 'docker build -t whanos-python -f /images/python/Dockerfile.base .'
    }
}
freeStyleJob('/Whanos base images/whanos-befunge') {
    description('build the Befunge base image so that it is available for the Jenkins instance host to use.')
    steps {
        sh 'docker build -t whanos-befunge -f /images/befunge/Dockerfile.base .'
    }
}

freeStyleJob('/Whanos base images/Build all base images') {
    description('build all base images so that it is available for the Jenkins instance host to use.')
    agent any
    stages {
        stage('info') {
            steps {
                echo "Build all base image"
            }
        }
        stage('triggerWhanosC') {
            steps {
                build job: "/Whanos base images/whanos-c", wait: true
            }
        }
        stage('triggerWhanosJava') {
            steps {
                build job: "/Whanos base images/whanos-java", wait: true
            }
        }
        stage('triggerWhanosJavascript') {
            steps {
                build job: "/Whanos base images/whanos-javascript", wait: true
            }
        }
        stage('triggerWhanosPython') {
            steps {
                build job: "/Whanos base images/whanos-python", wait: true
            }
        }
        stage('triggerWhanosBefunge') {
            steps {
                build job: "/Whanos base images/whanos-befunge", wait: true
            }
        }
    }
}

freeStyleJob('link-project') {
    description('')
    parameters {
    }
    steps {
        // create jobs with specifications
    }
}