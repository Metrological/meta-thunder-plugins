SUMMARY = "WPE Framework OpenCDMi - Widevine Nexus SVP"
DESCRIPTION = "WPE Framework OpenCDMi module for Widevine Nexus SVP"
HOMEPAGE = "https://code.rdkcentral.com/r/plugins/gitiles/soc/broadcom/components/rdkcentral/OCDM-Widevine-Nexus-SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=feac6454ca1bb4ff09e7bc76d34f57ed"

require ../include/wpeframework-plugins.inc

DEPENDS:append = " broadcom-refsw"

PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "git://code.rdkcentral.com/r/soc/broadcom/components/rdkcentral/OCDM-Widevine-Nexus-SVP.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "26b44b78c3caa5a400dc7b41dd6a421cc94dbad8"

WPE_OPENCDMI_WIDEVINE_CENC_VERSION ??= "15"
PACKAGECONFIG[opencdmi_widevine_cenc_version] = "-DCENC_VERSION=${WPE_OPENCDMI_WIDEVINE_CENC_VERSION}"

FILES:${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
FILES:${PN}:append = " ${datadir}/Thunder/OCDM/*.drm"
