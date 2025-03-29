#!groovy
pipeline {
    // agent { docker { image 'gradle:8.13.0-jdk21-alpine' } }
    agent any

    environment {
        DOCKER_IMAGE = 'my-jenkins'
        DOCKER_TAG = '${env.BUILD_NUMBER}'
        DOCKER_REGISTRY = 'msisdev/public'
        DOCKER_CREDENTIALS = credentials('docker-hub-msisdev')
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    def imageName = 'my-jenkins' // Replace with your desired image name
                    def imageTag = "latest-${BUILD_NUMBER}" // Using build number as part of the tag

                    // Build the Docker image
                    sh "docker build -t ${imageName}:${imageTag} ."

                    // Optional: Push the Docker image to a registry
                    // You'll need to configure Docker credentials in Jenkins
                    // sh "docker login -u '$DOCKER_REGISTRY_USERNAME' -p '$DOCKER_REGISTRY_PASSWORD' your-docker-registry.com"
                    // sh "docker push ${imageName}:${imageTag}"

                    println "Docker image built: ${imageName}:${imageTag}"
                }
            }
        }
    }
}
