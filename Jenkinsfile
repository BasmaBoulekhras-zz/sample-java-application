def project = 'green-dispatch-219519'
def  appName = 'sample-app'
def  imageTag = "gcr.io/${project}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline {
  stage 'Build image'
    sh("docker build -t ${imageTag} .")
  
}
