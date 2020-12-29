pipeline {
    agent any
    triggers {
    githubPush()
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..!!'
            }
        }
        stage('Test') {
            steps {
                echo 'Test!'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....!!!!!'
            }
        }
    }
}
