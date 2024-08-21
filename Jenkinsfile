pipeline {
    agent any

    environment {
        // Define o nome do repositório e a tag usando o número da build do Jenkins
        DOCKER_IMAGE = "85devs/monteirobank-backend"
        DOCKER_TAG = "v${BUILD_NUMBER}"
        CONTAINER_NAME = "monteirobank-backend"
        SONARQUBE_SERVER = 'sonarqube' // Nome da instalação do SonarQube no Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Realiza o checkout do código-fonte
                checkout scm
            }
        }

        stage('Build Jar') {
            steps {
                script {
                    // Compila o projeto Maven e gera o arquivo .jar
                    echo "Compilando o projeto e gerando o arquivo .jar"
                    sh 'mvn clean package -DskipTests' // Usa -DskipTests para ignorar os testes, caso seja necessário
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Constrói a imagem Docker
                    echo "Construindo a imagem Docker: ${DOCKER_IMAGE}:${DOCKER_TAG}"
                    sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                }
            }
        }

        stage('SonarQube Scan') {
            steps {
                script {
                    // Analisa o código com o SonarQube
                    echo "Executando análise do SonarQube"
                    withSonarQubeEnv(SONARQUBE_SERVER) {
                        sh 'mvn sonar:sonar -Dsonar.projectKey=monteiro-bank -Dsonar.host.url=http://192.168.100.2:9000 -Dsonar.login=${SONAR_KEY}
                    }
                }
            }
        }

        stage('Run') {
            steps {
                script {
                    // Para e remove qualquer contêiner anterior com o mesmo nome
                    echo "Parando e removendo qualquer contêiner existente com o nome: ${CONTAINER_NAME}"
                    sh "docker stop ${CONTAINER_NAME} || true"
                    sh "docker rm ${CONTAINER_NAME} || true"

                    // Executa o novo contêiner
                    echo "Executando o contêiner: ${CONTAINER_NAME}"
                    sh "docker run -d -p 7071:8080 --name ${CONTAINER_NAME} ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }
    }

    post {
        always {
            // Limpa as imagens antigas não usadas para liberar espaço
            script {
                echo "Limpando imagens Docker antigas não usadas"
                sh "docker image prune -f"
            }
        }
        success {
            echo "Pipeline concluído com sucesso!"
        }
        failure {
            echo "Pipeline falhou."
        }
    }
}
