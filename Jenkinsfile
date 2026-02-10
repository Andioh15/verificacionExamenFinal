pipeline {
    agent any

    tools {
        // Este nombre debe ser el mismo que configuraste en 'Global Tool Configuration'
        maven 'maven-3' 
    }

    stages {
        stage('1. Clonar') {
            steps {
                // Requisito 5.1: Clonación del repositorio
                checkout scm 
            }
        }

        stage('2. Compilar y Test') {
            steps {
                // Requisito 5.2 y 5.3: Compilación y ejecución de pruebas JUnit
                // El comando 'verify' asegura que se generen los reportes XML para las gráficas
                sh 'mvn clean verify'
            }
        }

        stage('3. Analizar Sonar') {
            steps {
                // Requisito 5.4: Análisis con SonarQube
                // 'SonarLocal' debe ser el nombre configurado en Administrar Jenkins > System
                withSonarQubeEnv('SonarLocal') {
                    // Usamos el nombre del contenedor 'sonar' para la red de Docker
                    sh "mvn sonar:sonar -Dsonar.projectKey=ExamenFinal -Dsonar.host.url=http://sonar:9000"
                }
            }
        }
    }

    // GRÁFICAS DE TENDENCIA EN JENKINS
    post {
        always {
            // Publica los resultados de JUnit incluso si las pruebas fallan
            // Esto permite que Jenkins dibuje la gráfica de éxito/error
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Pipeline completado con éxito. Gráficas y Sonar actualizados.'
        }
        failure {
            echo 'El Pipeline falló. Revisa la gráfica de JUnit para ver qué prueba falló.'
        }
    }
}
