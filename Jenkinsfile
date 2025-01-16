pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub_id')
        BUILD_ID = "${env.BUILD_NUMBER}" // Identifiant unique pour chaque build
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Arij-Abid/book_app.git'
            }
        }
        stage('Docker Login') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Build Frontend') {
            steps {
                dir('book-network-ui') {
                    script {
                        if (fileExists('Dockerfile')) {
                            sh '''
                            docker build -t arijabid/frontend:$BUILD_ID .
                            docker push arijabid/frontend:$BUILD_ID
                            docker rmi arijabid/frontend:$BUILD_ID
                            '''
                        } else {
                            error "Dockerfile is missing in the book-network-ui directory"
                        }
                    }
                }
            }
        }
        stage('Build Backend') {
            steps {
                dir('book-network') {
                    script {
                        if (fileExists('Dockerfile')) {
                            sh '''
                            docker build -t arijabid/backend:$BUILD_ID .
                            docker push arijabid/backend:$BUILD_ID
                            docker rmi arijabid/backend:$BUILD_ID
                            '''
                        } else {
                            error "Dockerfile is missing in the book-network directory"
                        }
                    }
                }
            }
        }
        stage('Deploy with Docker Compose') {
            steps {
                writeFile file: 'docker-compose.override.yml', text: '''
                version: '3.8'
                services:
                  book-network-ui:
                    image: arijabid/frontend:$BUILD_ID
                    ports:
                      - "4200:80"
                  book-network:
                    image: arijabid/backend:$BUILD_ID
                    environment:
                      SPRING_PROFILES_ACTIVE: prod
                '''
                sh '''
                docker-compose -f docker-compose.yml -f docker-compose.override.yml down
                docker-compose -f docker-compose.yml -f docker-compose.override.yml up -d
                '''
            }
        }
        stage('Cleanup') {
            steps {
                sh 'docker logout'
            }
        }
    }
    post {
        success {
            echo "Build and deployment succeeded."
        }
        failure {
            echo "Build or deployment failed. Check logs for details."
        }
        always {
            sh 'docker system prune -f'
        }
    }
}
