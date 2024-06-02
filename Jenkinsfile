pipeline {
    environment {
        registry = "asaidi/asa-user-crud-demo"
        registryCredential = 'cred_docker_hub_id'
        dockerImage = ''
    }
    agent any
    stages {
        stage('Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/asa-7670/asa-user-crud-demo'
            }
        }
        stage('Compile') {
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
        }
        stage('Package') {
            steps {
                sh "mvn package"
            }
        }
        stage('Docker image') {
            steps{
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage('Push image'){
           steps{
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
           }
        }
        stage('Cleaning up') {
            steps {
                sh 'docker rmi $registry:$BUILD_NUMBER'
            }
        }
    }
    post {
        always {
             script {
                 echo "I am execute always"
             }
        }
        success {
              script {
                 echo "I am execute on success"
                 //send mail to PO
              }
        }
        failure {
              script {
                 echo "I am execute on failure"
                 //Send mail to developer
              }
        }
    }
}