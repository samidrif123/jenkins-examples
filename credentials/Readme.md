## Jenkins Plugin
- [Credentials Plugin](https://wiki.jenkins.io/display/JENKINS/Credentials+Plugin)
- [Credentials Binding Plugin](https://wiki.jenkins.io/display/JENKINS/Credentials+Binding+Plugin)
- [Plain Credentials Plugin](https://wiki.jenkins.io/display/JENKINS/Plain+Credentials+Plugin)

## Usage
```
withCredentials([usernamePassword(credentialsId: 'amazon', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
  // available as an env variable, but will be masked if you try to print it out any which way
  sh 'echo $PASSWORD'
  // also available as a Groovy variable—note double quotes for string interpolation
  echo "$USERNAME"
}
```

```
// Basic example
withCredentials([usernamePassword(credentialsId: 'amazon',
                     usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
    //available as an env variable, but will be masked if you try to print it out any which way
    sh 'echo $PASSWORD'
    echo "${env.USERNAME}"
}
 
// You can also request multiple credentials in a single call
withCredentials([usernamePassword(credentialsId: 'amazon',
                     usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD'),
                 string(credentialsId: 'slack-url',
                     variable: 'SLACK_URL'),]) {
    sh 'echo $PASSWORD'
    echo "${env.SLACK_URL}"
}
 
// Older code might not use the new syntax (usernamePassword, string, ...) yet, and directly call the class:
withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'amazon',
                  usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
    //available as an env variable, but will be masked if you try to print it out any which way
    sh 'echo $PASSWORD'
    echo "${env.USERNAME}"
}
```