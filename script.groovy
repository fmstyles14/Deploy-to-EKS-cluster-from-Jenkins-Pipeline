def buildJar() {
    echo 'building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerCred', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t fmstyles/my-app:jma-2.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push fmstyles/my-app:jma-2.0'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
