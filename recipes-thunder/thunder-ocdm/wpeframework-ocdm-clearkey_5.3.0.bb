SUMMARY = "WPE Framework OpenCDMi - clearkey"
DESCRIPTION = "WPE Framework OpenCDMi module for clearkey"
HOMEPAGE = "https://github.com/rdkcentral/OCDM-Clearkey"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef252d84bf4d7b45388c93f6e5691f3d"

require ../include/version.inc
require ../include/wpeframework-plugins.inc
require include/wpeframework-ocdm.inc

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Clearkey.git;protocol=https;branch=master"
SRCREV = "7bd4e6f5f8279682bb8d0009081b562b76439c33"

DEPENDS += "openssl"

FILES:${PN} = " ${datadir}/WPEFramework/OCDM/*.drm"
FILES:${PN}:append = " ${datadir}/Thunder/OCDM/*.drm"
