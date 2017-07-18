## Jenkins Plugin
- [Vault Plugin](https://wiki.jenkins.io/display/JENKINS/HashiCorp+Vault+Plugin)

## Usage

Common: 
```
def configuration = [
    $class: 'VaultConfiguration',
    vaultCredentialId: 'VAULT_TOKEN_ID',
    vaultUrl: 'http://vault_url'
]
```

Cases:


```
// Access some password
def secrets = [
    [
        $class: 'VaultSecret', path: 'secret/epam/password', secretValues: [
            [$class: 'VaultSecretValue', envVar: 'password', vaultKey: 'value']
        ]
    ]
]

wrap([$class: 'VaultBuildWrapper', configuration: configuration, vaultSecrets: secrets]) {
	sh 'echo $password'
}

```

```
// Get file from Vault
def secrets = [
	[
        $class: 'VaultSecret', path 'secret/sp5/secretfile', secretValues: [
            [$class: 'VaultSecretValue', envVar: 'file', vaultKey: 'value']
        ]
    ]
]	
wrap([$class: 'VaultBuildWrapper', configuration: configuration, vaultSecrets: secrets]) {
	sh 'echo $password > myfile'
}
```

```
// More complex Vault objects 
def secrets = [
    [
        $class: 'VaultSecret', path: 'secret/epam/password', secretValues: [
            [$class: 'VaultSecretValue', envVar: 'password', vaultKey: 'value']
        ]
    ],
	[
        $class: 'VaultSecret', path 'secret/sp5/secretfile', secretValues: [
            [$class: 'VaultSecretValue', envVar: 'script_name', vaultKey: 'name'],
            [$class: 'VaultSecretValue', envVar: 'script', vaultKey: 'value'],
            [$class: 'VaultSecretValue', envVar: 'script_args', vaultKey: 'args']
        ]
    ]
]

wrap([$class: 'VaultBuildWrapper', configuration: configuration, vaultSecrets: secrets]) {
	sh """
       curl -u user:$password -X GET http://some_url
       echo $script > $script_name
       /bin/sh $script $script_args
    """ 
}
```

