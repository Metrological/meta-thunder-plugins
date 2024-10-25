SUMMARY = "Thunder Plugins RDK"
DESCRIPTION = "Thunder/Thunder Plugins RDK"
HOMEPAGE = "https://github.com:/WebPlatformForEmbedded/ThunderNanoServicesRDK"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=39fb5e7bc6aded7b6d2a5f5aa553425f"

PROVIDES += "wpeframework-plugins-rdk"
RPROVIDES:${PN} += "wpeframework-plugins-rdk"

require ../include/version.inc

SRC_URI = "git://git@github.com:/WebPlatformForEmbedded/ThunderNanoServicesRDK.git;protocol=ssh;branch=${RECIPE_BRANCH}"

ASNEEDED = ""

# Configuration file to configure Plugins used from RDKServices
require ../include/wpeframework-plugins.inc

# Plugins configs are moved to seperate includes
require include/deviceidentification.inc
require include/deviceinfo.inc
require include/displayinfo.inc
require include/locationsync.inc
require include/messagecontrol.inc
require include/messenger.inc
require include/monitor.inc
require include/ocdm.inc
require include/packager.inc
require include/playerinfo.inc
require include/securityagent.inc
require include/tracecontrol.inc
require include/webkitbrowser.inc

# Added deprecated plugins for backward compatibility
# require plugins_rdk_deprecated.inc

WPE_MESSAGE_LOGGING ??= "true"

PACKAGECONFIG ??= "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'clearkey', 'opencdmi_ck', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'netflix', 'monitor_netflix', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'opencdmi', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'monitor_opencdmi', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready', 'opencdmi_pr', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_nexus', 'opencdmi_prnx', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_nexus_svp', 'opencdmi_prnx_svp', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_vg', 'opencdmi_vgrdm', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'widevine', 'opencdmi_wv', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'security', 'securityagent', '', d)} \
    deviceidentification deviceinfo locationsync monitor monitor_webkit messagecontrol webkitbrowser \
"

PACKAGECONFIG:append = "${@bb.utils.contains('WPE_MESSAGE_LOGGING', 'true', 'messagecontrol', 'tracing', d)}"

# FIXME: these config has to be selected based on system config requirement.
# For the time being these are hardcoded
PACKAGECONFIG:append = "\
    monitor_cobalt monitor_webkit_ux \
    webkitbrowser_apps webkitbrowser_ux \
"

EXTRA_OECMAKE += "\
    -DBUILD_REFERENCE=${SRCREV} \
    -DCOMCAST_CONFIG=OFF \
"
