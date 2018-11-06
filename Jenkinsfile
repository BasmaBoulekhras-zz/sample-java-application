def project = 'green-dispatch-219519'
def  appName = 'sample-app'
def  imageTag = "gcr.io/${project}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline {
  agent {
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
  }
  stages {
    
     	 stage('build App') {
            steps {
             container('maven'){
              //git 'https://github.com/BasmaBoulekhras/sample-java-application.git'
              sh "mvn install -DskipTests=true"
             }     
            }
        }
    
         stage('Test') {
            steps {
             container('maven'){
                  sh "mvn test"
                  step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
             }     
            }
        } 
       
         /*stage('Code Analysis') {
            steps {
              container('maven'){
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
            
                timeout(time: 3, unit: 'MINUTES') {
               waitForQualityGate abortPipeline: false
                }
              }
            
        }*/
      
    stage('Build and push image with Container Builder') {
      steps {
        container('gcloud') {
          sh "PYTHONUNBUFFERED=1 gcloud builds submit -t ${imageTag} ."
        }
      }
    }
    
    
    stage('Deploy Canary') {
    // Canary branch
      when { branch 'mini_test' }
      
      steps{
        container('kubectl') {
        // Change deployed image in canary to the one we just built
          sh "ls"
          sh("sed -i.bak 's#gcr.io/green-dispatch-219519/sample-app:v1#${imageTag}#' ./k8s/canary/*.yaml")
         
          sh("kubectl --namespace=canary apply -f k8s/canary/")
          //sh("echo http://`kubectl get service/${feSvcName} -o jsonpath='{.status.loadBalancer.ingress[0].ip}'` > ${feSvcName}")
        }
      }
    }
    
     stage('Deploy Production') {
    // Production branch
      when { branch 'master' }
      
      steps{
        container('kubectl') {
        // Change deployed image in canary to the one we just built
          sh("sed -i.bak 's#gcr.io/green-dispatch-219519/sample-app:v1#${imageTag}#' ./k8s/production/*.yaml")
          sh("kubectl --namespace=production apply -f k8s/production/")
          //sh("echo http://`kubectl get service/${feSvcName} -o jsonpath='{.status.loadBalancer.ingress[0].ip}'` > ${feSvcName}")
        }
      }
    }
  }
}
