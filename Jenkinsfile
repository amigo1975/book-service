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
                   withCredentials([string(credentialsId: 'dhpswid', variable: 'dhpsw')]) {
                        bat 'docker login -u mtisw -p ${dhpsw}'
                   }
                   bat 'docker push mtisw/book_service:latest'
                }
            }
        }
    }
}