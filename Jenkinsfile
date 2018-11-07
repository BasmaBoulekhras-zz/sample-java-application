//@Library('sample-test')
pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('build') {
            steps {
                "Parse WebHook1": {
                    hello_world()
                }
                
            }
        }
      
    }
}
