# file-service

[![Build Status](https://travis-ci.org/becoming/02-java-file-service.svg?branch=master)](https://travis-ci.org/becoming/02-java-file-service)
[![Maintainability](https://api.codeclimate.com/v1/badges/4deac811aeda56ad3743/maintainability)](https://codeclimate.com/github/becoming/02-java-file-service/maintainability)
[ ![Download](https://api.bintray.com/packages/becoming/m2/file-service/images/download.svg) ](https://bintray.com/becoming/m2/file-service/_latestVersion) 
[![Gitter](https://badges.gitter.im/becoming-tech/community.svg)](https://gitter.im/becoming-tech/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

A service capable of saving and serving files. It exposes a REST interface for interaction.
[Official website](https://becoming.tech)

### Learning and Docs

You can proceed on learning the materials from this repository assuming that you know already [01-java-notification-service](https://github.com/becoming/01-java-notification-service)

Please refer to the [Wiki section](https://github.com/becoming/02-java-file-service/wiki) of this repository for a complete guide on how to build this application step by step and learn the required technologies along the way.

[YouTube Playlist](https://www.youtube.com/playlist?list=PLPkoWZmDIKwBAPN1iBNcRMz_XpruiJ27p)

### Setup

#### System requirements

 - Java 8
 - Maven 3.x - latest

As well, please install [this software list](https://becoming.tech/java/apps-and-software), to be covered.

#### Compile and build the app

```bash
mvn clean package
```

#### Run

```bash
mvn spring-boot:run
```

### Download a build

You can download a build of this service from our [Bintray repository.](https://bintray.com/beta/#/becoming/m2/)

You can use maven as well by using the instructions from **Set me up** button, (_top right_)

## Self testing

In order to test if you learned well the things so far try to do the following apps:

1. Save files on local drive
    - via dedicated route: /file-local-storage/
1. Save files in memory
    - via dedicated route: /file-in-memory/