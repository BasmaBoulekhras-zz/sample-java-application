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
  securityContext:
    fsGroup: 412
  containers:
  - name: maven
    image: maven:3.3.3
    command:
    - cat
    tty: true
  - name: busybox
    image: busybox:glibc
    command:
    - cat
    tty: true
    volumeMounts:
    - mountPath: /usr/bin/docker
      name: docker-bin
    - mountPath: /var/run/docker.sock
      name: docker-sock
  volumes:
  - name: docker-bin
    hostPath:
      path: /usr/bin/docker
    type: File
  - name: docker-sock
    hostPath:
      path: /var/run/docker.sock
    type: Socket
"""
    }
  }
    stages {
        stage('First') {
            steps {
              container('maven') {
                sh 'ls'
                sh 'echo "hola" > hola.txt'
              }
            }
        }
        
        stage('Second') {
            steps {
                 container('busybox'){
                 sh 'ls & docker info'
                 }     
            }
        }
    }
}
