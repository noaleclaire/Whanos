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
        shell('docker build -t whanos-c -f /images/c/Dockerfile.base .')
    }
}
freeStyleJob('/Whanos base images/whanos-java') {
    description('build the Java base image so that it is available for the Jenkins instance host to use.')
    steps {
        shell('docker build -t whanos-java -f /images/java/Dockerfile.base .')
    }
}
freeStyleJob('/Whanos base images/whanos-javascript') {
    description('build the Javascript base image so that it is available for the Jenkins instance host to use.')
    steps {
        shell('docker build -t whanos-javascript -f /images/javascript/Dockerfile.base .')
    }
}
freeStyleJob('/Whanos base images/whanos-python') {
    description('build the Python base image so that it is available for the Jenkins instance host to use.')
    steps {
        shell('docker build -t whanos-python -f /images/python/Dockerfile.base .')
    }
}
freeStyleJob('/Whanos base images/whanos-befunge') {
    description('build the Befunge base image so that it is available for the Jenkins instance host to use.')
    steps {
        shell('docker build -t whanos-befunge -f /images/befunge/Dockerfile.base .')
    }
}

freeStyleJob('/Whanos base images/Build all base images') {
    description('build all base images so that it is available for the Jenkins instance host to use.')
    publishers {
        downstream (
            "/Whanos base images/whanos-c"
        )
        downstream (
            "/Whanos base images/whanos-java"
        )
        downstream (
            "/Whanos base images/whanos-javascript"
        )
        downstream (
            "/Whanos base images/whanos-python"
        )
        downstream (
            "/Whanos base images/whanos-befunge"
        )
    }
}

freeStyleJob('link-project') {
    description('links the specified project in the parameters to the Whanos infrastructure')
    parameters {
        stringParam("GITHUB_NAME", "", "Git repository url links to the Whanos infrastructure")
        stringParam("JOB_NAME", "", "Name of the job to be created")
    }
    steps {
        dsl {
			text('''freeStyleJob("Projects/$JOB_NAME") {
					scm {
						github($GITHUB_NAME)
					}
					triggers {
						scm("* * * * *")
					}
					wrappers {
						preBuildCleanup()
					}
					steps {
						shell("/jenkins/check_language.sh")
					}
				}'''.stripIndent())
		}
    }
}