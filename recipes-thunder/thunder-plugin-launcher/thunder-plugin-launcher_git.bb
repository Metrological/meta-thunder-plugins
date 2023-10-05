SUMMARY = "WPE Framework Launcher Plugin"
DESCRIPTION = "WPE Framework Launcher Plugin. Plugin to launch external linux applications and scripts"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/WPEPluginLauncher"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

require ../include/version.inc
require ../include/wpeframework-plugins.inc

PROVIDES += "wpeframework-plugin-launcher"
RPROVIDES:${PN} += "wpeframework-plugin-launcher"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEPluginLauncher.git;branch=${RECIPE_BRANCH};protocol=https"
