# /my-jenkins

## 1. Quickstart

### 1.1. [Get Jenkins](https://www.jenkins.io/doc/pipeline/tour/getting-started/)
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

### 1.2. Docker Plugin
Dashboard > Manage Jenkins > Plugins
- Available plugins

Install Docker Pipeline

### 1.3. [Pipeline](https://www.jenkins.io/doc/pipeline/tour/hello-world/)
#### 1.3.1. Create pipeline
Dashboard > New Item
- name: My-Pipeline
- Multibranch Pipeline

#### 1.3.2. Add Source
Dashboard > My-Pipeline > Configuration
- Branch Sources > Add source

[GitHub authentication](https://github.com/jenkinsci/github-branch-source-plugin/blob/master/docs/github-app.adoc)
- (Cannot use localhost)

GitHub token 
- Create token
  - GitHub Settings - Developer Settings - Personal access tokens - Fine-grained tokens
  - Repository access: All repositories
  - Permissions
    - Repository permissions
      - Commit statuses - Read and Write 
      - Contents: Read and Write
      - Metadata: Read-only
      - Pull requests: Read-only
- Jenkins Dashboard > Manage Jenkins > Credentials > System > Global credentials (unrestricted) > New credentials
  - Kind: Username with password
  - Scope: Global
  - Username: GitHub name
  - Password: Token value
  - ID: name on jenkins

#### 1.3.3. Build

Run pipeline.

#### 1.3.e1. GitHub API usage
```
18:19:38 Connecting to https://api.github.com with no credentials, anonymous access
18:19:39 Jenkins-Imposed API Limiter: Current quota for Github API usage has 52 remaining (1 over budget). Next quota of 60 in 59 min. Sleeping for 4 min 50 sec.
18:19:39 Jenkins is attempting to evenly distribute GitHub API requests. To configure a different rate limiting strategy, such as having Jenkins restrict GitHub API requests only when near or above the GitHub rate limit, go to "GitHub API usage" under "Configure System" in the Jenkins settings.
```

#### 1.3.e2. Docker daemon socket permission
```
permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Get "http://%2Fvar%2Frun%2Fdocker.sock/v1.47/containers/gradle:8.13.0-jdk21-alpine/json": dial unix /var/run/docker.sock: connect: permission denied
```

[add jenkins to the group docker](https://stackoverflow.com/a/48450294)
- `sudo usermod -a -G docker jenkins`
```
$ groups jenkins                                                                                                                                     [18:42:41]
jenkins : jenkins docker
```
- `sudo systemctl restart jenkins`


## 2. Jenkinsfile

Currently gradle daemon is not used efficiently.
- `./gradlew test`: downloads Gradle in Jenkins
- `RUN ./gradlew build --no-daemon`: downloads Gradle in Docker
- Solution: test and build in Jenkins, copy only `.jar` in Dockerfile
