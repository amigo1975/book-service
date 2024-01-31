pipeline {
    agent any
    tools{
        maven 'maven_3_8_1'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/amigo1975/book-service']])
                sh 'mvnw clean package'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t mtisw/book_service:latest .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        sh 'docker login -u mtisw -p ${dockerhubpwd}'
                   }
                   sh 'docker push mtisw/book_service:latest'
                }
            }
        }
    }
}