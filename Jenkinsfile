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
        sh "docker build -t 192.168.1.2:5000/calculator ."
      }
    }
    stage("Docker push") {
      steps {
        sh "docker push 192.168.1.2:5000/calculator"
      }
    }
    stage("Acceptance test") {
      steps {
        sh "docker run -d -p 8765:8080 --name calculator 192.168.1.2:5000/calculator"
        sleep 60
        sh "./acceptance_test.sh"
        sh "docker stop calculator"
        sh "docker rm calculator"
      }
    }
    stage("Release") {
      steps {
        sh "docker -H 192.168.1.4:2375 stop calculator | true"
        sh "docker -H 192.168.1.4:2375 rm calculator | true"
        sh "docker -H 192.168.1.4:2375 run -d -p 8080:8080 --name calculator 192.168.1.2:5000/calculator"
      }
    }
    stage("Smoke test") {
      steps {
        sleep 60
        sh "./smoke_test.sh"
      }
    }
  }
}
