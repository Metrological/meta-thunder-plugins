# Common for thunder and modules which depends thunder
S = "${WORKDIR}/git"

inherit cmake pkgconfig

TOOLCHAIN = "gcc"
EXTRA_OECMAKE += "-DCMAKE_SYSROOT=${STAGING_DIR_HOST}"
EXTRA_OECMAKE += "-DTOOLS_SYSROOT=${STAGING_DIR_NATIVE}"

# Force BFD (to avoid gold)
LDFLAGS:append = " -fuse-ld=bfd"

# Yocto root is under /home/root
THUNDER_PERSISTENT_PATH ??= "/home/root"
THUNDER_VOLATILE_PATH ??= ""
THUNDER_DATA_PATH ??= ""
THUNDER_INSTALL_PATH ??= ""

THUNDER_GROUP ??= ""
THUNDER_OCDM_GROUP ??= ""
THUNDER_SUPER_USER_GROUP ??= "root"
THUNDER_PLATFORM_VIDEO_SUBSYSTEM ??= ""
THUNDER_PLATFORM_VIDEO_DEVICE_GROUP ??= "video"

DEPENDS += "git-replacement-native wpeframework-tools-native"

