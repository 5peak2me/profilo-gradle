# Profilo-Gradle: an Android performance library

## Introduction
Profilo is an Android library for collecting performance *traces* from production builds of an app.
> This is Gradle Version of [profilo](https://github.com/facebookincubator/profilo)

- based on [531dd15](https://github.com/facebookincubator/profilo/commit/531dd1582f9f1f0562829212f5c2cae274718e36)
- the artifacts in the [dist](dist) dir based on [release-fe5d8d61](https://github.com/facebookincubator/profilo/releases/tag/release-fe5d8d61)
- all dynamic libraries in profilo module's [jniLibs](profilo/src/main/jniLibs) dir unziped from the [aar](dist/profilo.aar) of  [release-fe5d8d61](https://github.com/facebookincubator/profilo/releases/tag/release-fe5d8d61)

## TODO
- Gradle native builds