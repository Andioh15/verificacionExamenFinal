pipeline {
    agent any
    tools {
        maven 'maven-3' // El nombre que pusiste en el paso 2
    }
    stages {
        stage('1. Clonar') {
            steps { checkout scm }
        }
        stage('2. Compilar y Test') {
            steps { sh 'mvn clean verify' }
        }
        stage('3. Analizar Sonar') {
            steps {
                withSonarQubeEnv('SonarLocal') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=ExamenFinal'
                }
            }
        }
    }
}
