pipeline {
    agent any

    environment {
        GIT_URL = 'https://github.com/phwebcloud/maven_sample.git'
        EMAIL_REPORT = 'noc@minhaempresa.com.br'
        WORK_DIR = 'app'
    }

    tools {
        maven 'maven-3.2.3'
    }

    stages {

        stage('Checkout') {
            steps {
                sh 'rm -rf ./*'
                retry(3){
                    git branch:'main' , url: "${GIT_URL}"
                }
            }
        }

        stage('Unit Test'){
            steps {
                dir ("${WORK_DIR}") {
                    sh 'mvn test'
                }
            }
            post {
                failure {
                    echo 'Testes unitários falharam. Necessário revisar o código.'
                }
            }
        }

        stage('Build') {
            steps {
                dir ("${WORK_DIR}"){
                   sh 'mvn package'
                }
            }
        }

        stage ('App Test') {
            steps {
                dir("${WORK_DIR}"){
                    sh 'java -cp ./target/*.jar com.quodemaven.App'
                }
            }
        }
    }

    post {
        always {
            mail to: "${EMAIL_REPORT}",
            subject: "Status da pipeline: ${currentBuild.fullDisplayName}",
            body: "${env.BUILD_URL} resultou em ${currentBuild.result}"
        }
        success {
            archiveArtifacts artifacts: "${WORK_DIR}/target/*.jar"
        }
    }
}
