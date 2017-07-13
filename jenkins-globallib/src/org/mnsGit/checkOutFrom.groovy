#!/usr/bin/groovy
package org.mnsGit;

def checkOutFrom(repo) {
  git url: "git@github.com:DigitalInnovation/${repo}"
}
