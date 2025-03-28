# /my-jenkins



## [Run Jenkins](https://www.jenkins.io/doc/pipeline/tour/getting-started/)
- [Download Jenkins](https://www.jenkins.io/download/)

- `sudo systemctl start jenkins`
    ```
    $ sudo systemctl status jenkins                                                                                                                                      [17:22:39]
    ● jenkins.service - Jenkins Continuous Integration Server
         Loaded: loaded (/usr/lib/systemd/system/jenkins.service; disabled; preset: disabled)
         Active: active (running) since Fri 2025-03-28 17:22:39 KST; 4s ago
     Invocation: f2735a8b449a4f4fab9c16f3aed24cd7
       Main PID: 416462 (java)
          Tasks: 55 (limit: 37319)
            CPU: 6.726s
         CGroup: /system.slice/jenkins.service
                 └─416462 /usr/bin/java -Djava.awt.headless=true -jar /usr/share/java/jenkins.war --webroot=/var/cache/jenkins/war --httpPort=8080
    ```

- (optional) Change default port
  - Find service file from above (`sudo systemctl status jenkins`)
  - `sudo nano jenkins.service`
  - Edit `Environment="JENKINS_PORT=8080`
  - `systemctl daemon-reload`
  - `sudo systemctl start jenkins`

- http://localhost:8080

- Find secret `sudo nvim /var/lib/jenkins/secrets/initialAdminPassword`

## Add Docker Plugin
Dashboard > Manage Jenkins > Plugins
- Available plugins

Install Docker Pipeline

## [Creating your first pipeline](https://www.jenkins.io/doc/pipeline/tour/hello-world/)
### Create pipeline
Dashboard > New Item
- name: My-Pipeline
- Multibranch Pipeline

### Add Source
Dashboard > My-Pipeline > Configuration
- Branch Sources > Add source

[GitHub authentication](https://github.com/jenkinsci/github-branch-source-plugin/blob/master/docs/github-app.adoc)
- Cannot use localhost

