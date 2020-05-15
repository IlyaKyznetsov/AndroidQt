QT       += widgets

CONFIG += c++11

# The following define makes your compiler emit warnings if you use
# any Qt feature that has been marked deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    Global.cpp \
    main.cpp

HEADERS += \
    Global.h

# Default rules for deployment.
unix:!android: {
    QT += core
    target.path = /usr/lib
    !isEmpty(target.path): INSTALLS += target
} else: android {
    QT += androidextras

    ANDROID_PACKAGE_SOURCE_DIR = $$TOP_SRC_DIR/ANDROID_PACKAGE_SOURCE_DIR
    # Gradle settings
    OTHER_FILES += \
        $$ANDROID_PACKAGE_SOURCE_DIR/build.gradle \
        $$ANDROID_PACKAGE_SOURCE_DIR/gradle/wrapper/gradle-wrapper.jar \
        $$ANDROID_PACKAGE_SOURCE_DIR/gradle/wrapper/gradle-wrapper.properties \
        $$ANDROID_PACKAGE_SOURCE_DIR/gradlew \
        $$ANDROID_PACKAGE_SOURCE_DIR/gradlew.bat \
        $$ANDROID_PACKAGE_SOURCE_DIR/gradle.properties \
        $$ANDROID_PACKAGE_SOURCE_DIR/local.properties \
        $$ANDROID_PACKAGE_SOURCE_DIR/settings.gradle

    # Android Settings
    OTHER_FILES += \
        $$ANDROID_PACKAGE_SOURCE_DIR/AndroidManifest.xml \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/values/libs.xml

    # Android resources
    OTHER_FILES += \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/drawable-v24/ic_launcher_foreground.xml \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-xxxhdpi/ic_launcher.png \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-xxxhdpi/ic_launcher_round.png \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/layout/activity_launcher.xml \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/layout/activity_main.xml \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/layout/activity_main_qt.xml \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-xxhdpi/ic_launcher.png \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-xxhdpi/ic_launcher_round.png \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-xhdpi/ic_launcher.png \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-xhdpi/ic_launcher_round.png \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-hdpi/ic_launcher.png \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-hdpi/ic_launcher_round.png \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-anydpi-v26/ic_launcher.xml \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-anydpi-v26/ic_launcher_round.xml \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-mdpi/ic_launcher.png \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/mipmap-mdpi/ic_launcher_round.png \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/values/colors.xml \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/values/strings.xml \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/values/styles.xml \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/drawable \
        $$ANDROID_PACKAGE_SOURCE_DIR/res/drawable/ic_launcher_background.xml

    PACKAGE=org/qtproject/example
    # On Linux ##########################################################
    ANDROID_JAVA_SOURCES.path = /java/$${PACKAGE}
    ANDROID_JAVA_SOURCES.files = $$files($$TOP_SRC_DIR/java/$${PACKAGE}/*.java)
    OTHER_FILES += $$ANDROID_JAVA_SOURCES.files
    INSTALLS += ANDROID_JAVA_SOURCES
    #####################################################################

    # On Windows ########################################################
#    ANDROID_JAVA_DIR = $$TOP_SRC_DIR/java/$${PACKAGE}
#    OTHER_FILES += \
#        $$ANDROID_JAVA_DIR/LauncherActivity.java \
#        $$ANDROID_JAVA_DIR/CoreAgentService.java \
#        $$ANDROID_JAVA_DIR/DeliveryAgentService.java \
#        $$ANDROID_JAVA_DIR/ApartService.java
    #message( :::: ANDROID_JAVA_DIR:  $$ANDROID_JAVA_DIR)
#    ANDROID_JAVA_SOURCES.path = /java/$${PACKAGE}
#    ANDROID_JAVA_SOURCES.files = $$files($$TOP_SRC_DIR/java/$${PACKAGE}/*.java)
    #####################################################################

#    INSTALLS += ANDROID_JAVA_SOURCES

    OTHER_FILES += $$TOP_BUILD_DIR/android-build/AndroidManifest.xml
}