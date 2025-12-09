require include/wpeframework-ocdm-playready.inc

require ../include/version.inc

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH}"
SRC_REV ?= "bc30841b599e4eca0d69f38eb2fbeff4c7f51fd2"

TARGET_CFLAGS += "-fpermissive"

RDEPENDS:${PN}:append = " playready"
