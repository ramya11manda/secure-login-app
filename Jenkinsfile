pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        // Jenkins Secret Text credential ID
        SONAR_TOKEN = credentials('sonar-token')
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        mvn sonar:sonar \
                          -Dsonar.projectKey=secure-login-app \
                          -Dsonar.host.url=http://host.docker.internal:9000 \
                          -Dsonar.login=$SONAR_TOKEN
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Deploy to Staging') {
            steps {
                sh 'echo "Application deployed to staging environment"'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
            jacoco execPattern: 'target/jacoco.exec'
        }
    }
}