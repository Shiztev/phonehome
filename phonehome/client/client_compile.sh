#!/bin/bash

# run client PhoneHome using javafx modules
javac --module-path ./phonehome/client/assets/javafx-sdk-11.0.2/lib --add-modules javafx.controls ./phonehome/client/PhoneHome.java