def project = 'green-dispatch-219519'
def  appName = 'sample-app'
def  imageTag = "gcr.io/${project}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline {
  agent { docker { image 'maven:3.3.3' } }   
 
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    
    stage('SCM') {
        steps{
    git 'https://github.com/BasmaBoulekhras/sample-java-application.git'
     slackSend color: "46c9e2", message: "git is working"
        }
  }
    stage('build with test') {
        steps{
    sh 'mvn test'
        }
      
  }
  
     stage('build && SonarQube analysis') {
            steps {
                withSonarQubeEnv('jenkins') {
                    // Optionally use a Maven environment you've configured already
                    withMaven(maven:'Maven 3.5') {
                        sh 'mvn clean package sonar:sonar'
                    }
                }
                sh 'sleep 10'
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 3, unit: 'MINUTES') {
               waitForQualityGate abortPipeline: false
                }
            }
            
        }
       stage ('Build image') {
      steps {
        sh "ls"
        sh "mvn package"
         sh "ls target/*"
        sh("docker build -t ${imageTag} .")
      }
    }
       stage('Build and push image with Container Builder') {
      steps {
        container('gcloud') {
          sh "PYTHONUNBUFFERED=1 gcloud builds submit -t ${imageTag} ."
        }
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
