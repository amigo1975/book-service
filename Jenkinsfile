pipeline {
    agent any
    tools{
        maven 'maven_3_8_1'
    }
    stages{
        stage('Build maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/amigo1975/book-service']])
                bat 'mvn clean package'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    bat 'docker build -t mtisw/book_service:latest .'
                }
            }
        }
        stage('Push image to Docker Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhub-pwd')]) {
                        bat 'docker login -u mtisw -p ${dockerhub-pwd}'
                   }
                   bat 'docker push mtisw/book_service:latest'
                }
            }
        }
    }
}