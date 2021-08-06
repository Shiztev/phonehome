#!/bin/bash
#
# This is the boot script for Phone Home.
# 

# compile .java files to .class files
javac --module-path ./phonehome/client/assets/javafx-sdk-11.0.2/lib --add-modules javafx.controls ./phonehome/client/PhoneHome.java

# run client PhoneHome using javafx modules
java --module-path ./phonehome/client/assets/javafx-sdk-11.0.2/lib --add-modules javafx.controls phonehome.client.PhoneHome