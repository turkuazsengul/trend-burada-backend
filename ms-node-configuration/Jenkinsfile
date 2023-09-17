pipeline {
  agent any

  environment {
    imagename = "turkuazsengul/ms-node-configuration:latest"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  stages {
    stage('Cloning Git') {
      steps {
        git([url: 'https://github.com/turkuazsengul/ms-node-configuration.git', branch: 'master', credentialsId: 'github-password'])
      }
    }
    stage('Create Jar') {
        steps{
            bat 'mvn -Dmaven.test.skip install'   //windows
        }
    }
    stage('Docker Build') {
      steps{
        script {
          dockerImage = docker.build imagename
        }
      }
    }
    stage('Docker Push') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push("0.0.$BUILD_NUMBER")
            dockerImage.push('latest')
          }
        }
      }
    }
  }
}
