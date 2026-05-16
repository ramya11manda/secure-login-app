pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        // Uses your existing Jenkins Secret Text credential ID
        SONAR_TOKEN = credentials('sonarqube-token')
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
                        -Dsonar.token=$SONAR_TOKEN
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
            script {
                if (fileExists('target/surefire-reports')) {
                    junit 'target/surefire-reports/*.xml'
                }

                if (fileExists('target/jacoco.exec')) {
                    jacoco execPattern: 'target/jacoco.exec'
                }
            }
        }
    }
}