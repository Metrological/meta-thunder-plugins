SUMMARY = "WPE Framework OpenCDMi - Widevine"
DESCRIPTION = "WPE Framework OpenCDMi module for widevine"
HOMEPAGE = "https://github.com/rdkcentral/OCDM-Widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1384d4b11dad572771bc2dad384029a6"

require ../include/version.inc
require ../include/wpeframework-plugins.inc

DEPENDS:append = " widevine"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Widevine.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "375d6242c328e44d6dc42f6f423bb601a15d8276"

FILES:${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
FILES:${PN}:append = " ${datadir}/Thunder/OCDM/*.drm"
