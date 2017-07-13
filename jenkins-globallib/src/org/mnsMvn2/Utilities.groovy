package org.mnsMvn2
class Utilities {
  static def mvn(script, args) {
    //script.sh "${script.tool 'Maven'}/bin/mvn -s ${script.env.HOME}/jenkins.xml -o ${args}"
    script.sh "${script.tool 'Maven'}/bin/mvn -o ${args}"
  }
}
