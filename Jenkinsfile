pipeline {
    agent any 

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub-msisdev')
        APP_NAME = "msisdev/my-jenkins"
    }

    stages {
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        
        stage('Build docker image') {
            steps {  
                sh 'docker build -t $APP_NAME:$BUILD_NUMBER .'
            }
        }
        
        stage('login to dockerhub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        
         stage('push image') {
            steps {
                sh 'docker push $APP_NAME:$BUILD_NUMBER'
            }
        }
    }
}
