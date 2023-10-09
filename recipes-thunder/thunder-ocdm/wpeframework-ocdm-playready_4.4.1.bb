require include/wpeframework-ocdm-playready.inc

require ../include/version.inc

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH}"

TARGET_CFLAGS += "-fpermissive"

RDEPENDS:${PN}:append = " playready"
