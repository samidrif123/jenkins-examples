def call(Closure body) {
    
    //init
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    //vars    
    recipients = config.recipients
    templates = [
        'deploy' : "<h1>i wanna be html template for message</h1>"
    emailext attachLog: true, mimeType: 'text/html', body: templates[config.type], subject: env.JOB_NAME+" Result: "+currentBuild.result , to: recipients
}
