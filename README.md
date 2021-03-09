[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# CUBA Duration Field Add-on

This add-on provides support for the `java.time.Duration` data type.

## Installation
This add-on's repository is officially linked to the main CUBA repository, so you can install it from the Marketplace
or by adding the repository manually to your project.

The following table shows which version of the add-on is compatible with which version of the platform:

| Platform Version | Add-on Version | Coordinates
| ---------------- | -------------- | ------------
| 7.2.*            | 1.0.0          | gr.netmechanics.cuba.df:nmcdf-global:1.0.0

The latest stable version is: `1.0.0`

### From Marketplace
You can use **CUBA Studio** / **IntelliJ IDEA** to add it to your project: choose the `CUBA -> Marketplace...` menu item,
find the *Duration Field* add-on, then click on the `Install` button.

### Adding the repository manually
Modify the `build.gradle` of your CUBA application. First add the repository `https://dl.bintray.com/netmechanics/cuba-components` 
to the buildscript.
```gradle
buildscript {
    ...
    repositories {
        ...
        maven {
            url 'https://dl.bintray.com/netmechanics/cuba-components'
        }
        ...
    }
    ...
}
...
```
Then select the version of the add-on which is compatible with the platform version of your project 
and add custom application component to your project
```gradle
...
dependencies {
    ...
    appComponent('gr.netmechanics.cuba.df:nmcdf-global:1.0.0')
    ...
}
...
```
