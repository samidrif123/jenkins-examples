def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    // now build, based on the configuration provided
    wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
        withDockerContainer(
            image: 'sbeliakou/ansible:2.3.1',
            args:  '-v ${HOME}/.ssh:${HOME}/.ssh -e HOME=${HOME}'
        ){
            sh 'ansible-playbook ${config.playbook} -i ${config.inventory} -vvvv'
        }
    }
}