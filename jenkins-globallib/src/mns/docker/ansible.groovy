package mns.docker
class Ansible implements Serializable {
  def steps
  Ansible(steps) {this.steps = steps}
  def run(playbook, inventory) {
    steps.sh """
              docker run -u $(id -u) --rm \
                -v ${HOME}/.ssh:${HOME}/.ssh \
                -v ${WORKSPACE}:${WORKSPACE} \
                -e HOME=${HOME} \
                -w ${WORKSPACE} \
                sbeliakou/ansible:2.3.1 \
                ansible-playbook ${playbook} -i ${inventory} -vv
                """
  }
}
//playbook prometheus/dotcom-migration/configure.yml
//inventory inventory/dotcom-migration