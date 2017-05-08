pipeline {
  agent any

  triggers {
    pollSCM('* * * * *')
  }

  stages {
    stage("Compile") {
      steps {
        sh "./gradlew compileJava"
      }
    }

    stage("Unit test") {
      steps {
        sh "./gradlew test"
      }
    }

    stage("Build") {
      steps {
        sh "./gradlew build"
      }
    }
    stage("Docker build") {
      steps {
        sh "docker build -t localhost:5000/calculator ."
      }
    }
    stage("Docker push") {
      steps {
        sh "docker push localhost:5000/calculator"
      }
    }
    stage("Acceptance test") {
      steps {
        sh "docker run -d -p 8765:8080 --name calculator localhost:5000/calculator"
        sleep 60
        sh "./acceptance_test.sh"
        sh "docker stop calculator"
      }
    }
  }
}
