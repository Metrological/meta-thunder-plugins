SUMMARY = "Thunder Framework plugins Packagegroup"
DESCRIPTION = "Thunder Plugins Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-thunder-plugins \
"

RDEPENDS_packagegroup-thunder-plugins = "\
    thunder-plugins \
    thunder-plugins-rdk \
"

PROVIDES += "packagegroup-wpeframework-plugins"