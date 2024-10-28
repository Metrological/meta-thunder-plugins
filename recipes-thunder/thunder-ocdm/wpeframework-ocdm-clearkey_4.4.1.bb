SUMMARY = "WPE Framework OpenCDMi - clearkey"
DESCRIPTION = "WPE Framework OpenCDMi module for clearkey"
HOMEPAGE = "https://github.com/rdkcentral/OCDM-Clearkey"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef252d84bf4d7b45388c93f6e5691f3d"

require ../include/version.inc
require ../include/wpeframework-plugins.inc

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Clearkey.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "8d1e41d36c349a6be89b911cc8404541ba29c599"

FILES:${PN} = " ${datadir}/WPEFramework/OCDM/*.drm"
FILES:${PN}:append = " ${datadir}/Thunder/OCDM/*.drm"
