# Common for WPE Framework plugins
require wpeframework.inc

DEPENDS:append = " wpeframework-tools-native wpeframework wpeframework-interfaces wpeframework-clientlibraries"

FILES_SOLIBSDEV = ""

FILES:${PN} += "\
    ${libdir}/wpeframework/plugins/*.so \
    ${datadir}/WPEFramework \
"

FILES:${PN} += "\
    ${libdir}/thunder/plugins/*.so \
    ${datadir}/Thunder \
"


