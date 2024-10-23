SUMMARY = "Thunder Framework plugins Packagegroup"
DESCRIPTION = "Thunder Plugins Packagegroup"
LICENSE = "MIT"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PACKAGES = "\
    packagegroup-thunder-plugins \
"

RDEPENDS:${PN} = "\
    thunder-plugins \
    thunder-plugins-rdk \
"

PROVIDES:append = " \
    packagegroup-wpeframework-plugins \
    packagegroup-thunder-plugins \
"
