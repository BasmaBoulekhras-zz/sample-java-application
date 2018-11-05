def project = 'green-dispatch-219519'
def  appName = 'sample-app2'
def  imageTag = "gcr.io/${project}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline {
  agent { docker { image 'maven:3.3.3' } }   
  stages {
    stage ('Build image') {
      steps {
        sh("docker build -t ${imageTag} .")
      }
    }  
  }          
}
