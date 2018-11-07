pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('Checkout & Build') {
            steps {
                    mavenBuild()    
            }
        }
        
        stage('Unit & Integration Testing') {
            steps {
                    mavenTest()    
            }
        }
        
        stage('Sonar Scan') {
            steps {
                    mavenSonarScan()
            }
        }
      
    }
}
