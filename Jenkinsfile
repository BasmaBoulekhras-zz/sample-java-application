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
    git 'https://github.com/ThomasJaspers/java-junit-sample.git'
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
        sh("docker build -t ${imageTag} .")
      }
    }  
  }          
}
