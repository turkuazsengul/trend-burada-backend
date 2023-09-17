pipeline {
  environment {
    imagename = "turkuazsengul/ms-api-product:latest"
    registryCredential = 'dockerhub'
    dockerImage = ''
    maven = tool 'maven'
  }

  agent any

  stages {
    stage('Cloning Git') {
      steps {
        git([url: 'https://github.com/turkuazsengul/ms-api-product.git', branch: 'master', credentialsId: 'github-password'])
      }
    }
    stage('Create Jar') {
        steps{
            bat 'mvn -Dmaven.test.skip install'
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
