require wpewebkit.inc

PV = "2.38+git${SRCPV}"
PR = "r0"
RECIPE_BRANCH ?= "wpe-2.38"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=${RECIPE_BRANCH};protocol=https \
           file://0001-Fix-for-missing-heap-vm-main.patch \
           "
SRCREV ?= "3c84c7ab9b4a9f096f5d183e17cc52e73fc758d5"

RCONFLICTS:${PN} = "libwpe (< 1.4) wpebackend-fdo (< 1.6)"

