[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/mauriciotogneri/android-publisher/blob/master/LICENSE.md)
[![Download](https://api.bintray.com/packages/mauriciotogneri/maven/androidpublisher/images/download.svg)](https://bintray.com/mauriciotogneri/maven/androidpublisher/_latestVersion)

# Android Publisher
Java tool to automate the publishing of Android applications.

## Installation

Add the following code to your **pom.xml**:

```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <url>https://jcenter.bintray.com</url>
    </repository>
</repositories>
```

and the dependency:

```xml
<dependency>
    <groupId>com.mauriciotogneri</groupId>
    <artifactId>androidpublisher</artifactId>
    <version>1.1.0</version>
</dependency>
```

or if you use Gradle:

```groovy
dependencies
{
    implementation 'com.mauriciotogneri:androidpublisher:1.1.0'
}
```

## Usage

From console:
```bash
java -jar android-publisher.jar -package <PACKAGE_NAME> -email <SERVICE_ACCOUNT_EMAIL> -p12 <SERVICE_ACCOUNT_P12_FILE_PATH> -apk <APK_FILE_PATH> -bundle <BUNDLE_FILE_PATH> -track <TRACK_NAME>
```

From Java:
```
Publisher publisher = new Publisher();

publisher.publishApk(
        "PACKAGE_NAME",
        "SERVICE_ACCOUNT_EMAIL",
        "SERVICE_ACCOUNT_P12_FILE_PATH",
        "APK_FILE_PATH",
        "TRACK_NAME"
);

publisher.publishBundle(
        "PACKAGE_NAME",
        "SERVICE_ACCOUNT_EMAIL",
        "SERVICE_ACCOUNT_P12_FILE_PATH",
        "BUNDLE_FILE_PATH",
        "TRACK_NAME"
);
```