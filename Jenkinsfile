pipeline {
    agent any
    stages {
        stage('First') { 
            agent { docker { image 'maven:3.6.1-jdk-13-alpine'}} 
            steps {
                sh 'mvn --version'
            }
        }
        stage('Second') { 
           agent { docker { image 'busybox:glibc'}}
            steps {
                sh 'docker info'
            }
        }
    }
}
