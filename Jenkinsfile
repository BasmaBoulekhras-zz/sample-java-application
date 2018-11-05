def project = 'green-dispatch-219519'
def  appName = 'sample-app'
def  imageTag = "gcr.io/${project}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline {
  agent { docker { image 'maven:3.3.3' } }   
   agent {
    kubernetes {
      label 'sample-app'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: maven
    image: maven:3.3.3
    command:
    - cat
    tty: true
  - name: gcloud
    image: gcr.io/cloud-builders/gcloud
    command:
    - cat
    tty: true
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
"""
}
  }
    stages {
        stage('build') {
            steps {
              container('maven') {
                sh 'mvn --version'
              }}
        }
    
    stage('SCM') {
        steps{
          container('maven') {
    git 'https://github.com/BasmaBoulekhras/sample-java-application.git'
     slackSend color: "46c9ekubectl2", message: "git is working"
          }}
  }
    stage('build with test') {
        steps{
    sh 'mvn test'
        }
      
  }
  
     stage('build && SonarQube analysis') {
            steps {
              container('maven') {
                withSonarQubeEnv('jenkins') {
                    // Optionally use a Maven environment you've configured already
                    withMaven(maven:'Maven 3.5') {
                        sh 'mvn clean package sonar:sonar'
                    }
                }
                sh 'sleep 10'
              }}
        }
        stage("Quality Gate") {
            steps {
              container('maven') {
                timeout(time: 3, unit: 'MINUTES') {
               waitForQualityGate abortPipeline: false
                }
              }}
            
        }
       stage ('Build image') {
      steps {
        container('maven') {
        sh "ls"
        sh "mvn package"
         sh "ls target/*"
        sh("docker build -t ${imageTag} .")
        }}
    }
      
       stage('Deploy') {
      // Canary branch
      
      steps {
        container('kubectl') {
          // Change deployed image in canary to the one we just built
          sh("sed -i.bak 's#gcr.io/cloud-solutions-images/gceme:1.0.0#${imageTag}#' ./deployments/*.yaml")
          sh("kubectl  apply -f deployments/")
          
          sh("echo http://`kubectl  get service/${feSvcName} -o jsonpath='{.status.loadBalancer.ingress[0].ip}'` > ${feSvcName}")
        } 
      }
    }
  }          
}
