pipeline {
    agent any
    
    environment {
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: "master", credentialsId: "conf-git", url: "https://github.com/conf312/issueMoa-eureka-server.git"
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    sh "chmod 777 gradlew && ./gradlew bootJar"
                }
            }
        }
        stage('Dokcer Image Build') {
            steps {
                script {
                    sh "docker build -t issuemoa/eureka ."
                }
            }
        }
        stage('Dokcer Run Conatiner') {
            steps {
                script {
                    def containerRunning = sh(script: 'docker inspect -f \'{{.State.Running}}\' issuemoa-eureka 2>/dev/null', returnStatus: true) == 0

                    if (containerRunning) {
                        echo 'Container is already running'
                        sh "docker stop issuemoa-eureka"
                        sh "docker rm issuemoa-eureka"
                    }
                    
                    sh "docker run -d --name issuemoa-eureka -p 8761:8761 --network issuemoa issuemoa/eureka"
                }
            }
        }
    }

}
