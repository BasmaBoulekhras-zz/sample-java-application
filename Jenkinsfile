def project = 'green-dispatch-219519'
def  appName = 'sample-app'
def  imageTag = "gcr.io/${project}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline {
    agent { docker { image 'maven:3.3.3' } }
  
    
    
    stages {
        stage('Build and push image with Container Builder') {
            steps {
                container('gcloud') {
                    sh "PYTHONUNBUFFERED=1 gcloud container builds submit -t ${imageTag} ."
                }
            }
        }
        
        
        
  
    }
}
