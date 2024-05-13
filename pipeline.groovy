pipeline {
    agent any
    
    environment {
        DIRECTORY_PATH = "C:/Program Files/Jenkins"
        TESTING_ENVIRONMENT = "AWS EC2 instance"
        PRODUCTION_ENVIRONMENT = "AWS EC2 instance"
    }
    //adding comments
    stages {
        stage('Build') {
            steps {
                echo "Fetching source code from: ${env.DIRECTORY_PATH}"
                echo "Compiling the code and generating necessary artifacts"
            }
        }
        stage('Test') {
            steps {
                echo "Running unit tests"
                echo "Running integration tests"
            }
        }
        stage('Code Quality Check') {
            steps {
                echo "Checking the quality of the code"
            }
        }
        stage('Security Scan') {
            steps {
                echo "Scanning for the security of the application"
            }
        }
        stage('Deploy to a staging/testing server') {
            steps {
                echo "Deploying the application to testing environment: ${env.TESTING_ENVIRONMENT}"
            }
        }
        stage('Integration Tests after staging') {
            steps {
                echo "Running integration tests"
            }
        }
        stage('Deploy to Production') {
            steps {
                echo "Deploying the application to production environment: ${env.PRODUCTION_ENVIRONMENT}"
            }
        }
        
    }
    post {
        success {
            // Send success email notification
            emailext body: 'Pipeline ran successfully in jenkins',
                     subject: 'Pipeline Success',
                     to: 'optional2001@gmail.com'
        }
        failure {
            // Send failure email notification
            emailext body: 'Pipeline failed',
                     subject: 'Pipeline Failure',
                     to: 'optional2001@gmail.com'
        }
    }
}
