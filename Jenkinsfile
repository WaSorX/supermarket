pipeline{
    agent any

    stages{
        stage("Build") {
            steps {
                sh "mvn -version"
                sh "mvn clean install -DskipTests=false"
            }
        }
        stage("Run") {
            steps{
            sh "java -jar target/supermarket-0.0.1-SNAPSHOT.jar"
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }

}