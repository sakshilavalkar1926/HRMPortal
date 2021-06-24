pipeline {
    agent any

    tools{
        maven "Maven3"
        jdk "Java8"
    }
    environment{
        PATH = "/opt/Maven3/bin:$PATH"
    }
    stages {
        stage ('Compile Stage') {

            steps {
                
                    bat 'mvn compile'
                
            }
        }

        stage ('Testing Stage') {

            steps {
                
                    bat 'mvn test'
                
            }
        }
       
  
      stage ('Packaging stage') {
        
        steps{
          bat 'mvn package'
        }
        
      }
        
        stage ('Deployment Stage') {
            steps {
                
                    bat 'mvn install tomcat7:deploy -Djava.net.preferIPv4Stack=true'
                
            }
        }
    }
}
