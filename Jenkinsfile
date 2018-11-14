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
  - name: source2image
    image: gcr.io/daas-demo/source2image
    command:
    - cat
    tty: true
"""
    }
  }
    stages {
        stage('Checkout & Build') {
            steps {
              //   container('maven'){
              //      mavenBuild() 
              //   }     
              container('source2image') {
                sh '/bin/s2i build https://github.com/jorgemoralespou/s2i-java . s2i-test-image'
              }
            }
        }
        
        stage('Unit & Integration Testing') {
            steps {
                 container('maven'){
             //       mavenTest()   
                 }     
            }
        }
        
        /*stage('Sonar Scan') {
            steps {
                 container('maven'){
            //        mavenSonarScan()
                 }    
            }
        }*/
        
        stage('Build Image') {
            steps {
                 container('gcloud') {
           //         buildDockerImage()
                 }     
            }
        }
       
//       stage('Deploy Canary') {
//       // Canary branch
//       when { branch 'mini_test' }
//      
//       steps{
//          container('kubectl') {
//             deploymentLogic("canary")    
//          }
//       }
//       }
//       
//       stage('Deploy Production') {
//       // Production branch
//       when { branch 'master' }
//      
//         steps{
//           container('kubectl') {
//             deploymentLogic("production")    
//          }
//         }
//       }
//       
//       stage('Deploy Dev') {
//       // Production branch
//          when {
//             not {  branch 'master' }
//             not {  branch 'mini_test' }
//          }
//          
//         steps{
//           container('kubectl') {
//             deploymentLogic("${env.BRANCH_NAME}")    
//          }
//         }
//       }
      
    }
}
