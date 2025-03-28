pipeline {
    agent { docker { image 'gradle:8.13.0-jdk21-alpine' } }
    stages {
        stage('build') {
            steps {
                sh 'gradle --version'
            }
        }
    }
}
