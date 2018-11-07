pipeline {
     agent { docker { image 'maven:3.3.3' } }
     /*agent {
    kubernetes {
      label 'kubernetes-pod'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    some-label: some-label-value
spec:
  containers:
  - name: maven
    image: maven:3.3.3
    command:
    - cat
    tty: true
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
  - name: gcloud
    image: gcr.io/cloud-builders/gcloud
    command:
    - cat
    tty: true
"""
    }
  }*/
    stages {
        stage('Checkout & Build') {
            steps {
                 //container('maven'){
                    mavenBuild() 
                // }     
            }
        }
        
        stage('Unit & Integration Testing') {
            steps {
                 //container('maven'){
                    mavenTest()   
                // }     
            }
        }
        
        stage('Sonar Scan') {
            steps {
                 //container('maven'){
                    mavenSonarScan()
                 //}    
            }
        }
        
        /*stage('Build Image') {
            steps {
                 container('gcloud') {
                    buildDockerImage()
                 }     
            }
        }*/
      
    }
}
