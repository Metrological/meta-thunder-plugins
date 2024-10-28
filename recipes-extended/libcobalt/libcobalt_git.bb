SUMMARY = "Lighweigh application container compatible with W3C HTML5 spec"
DESCRIPTION = "\
    Cobalt is a lightweight application container \
    (i.e. an application runtime, like a JVM or the Flash Player) \
    that is compatible with a subset of the W3C HTML5 specifications \
"
HOMEPAGE = "https://github.com/Metrological/Cobalt"
LICENSE = "BSD-3-Clause & BSD-2-Clause & Apache-2.0 & MIT & ISC & OpenSSL & CC0-1.0 & LGPL-2.0 & LGPL-2.1 & PD & Zlib & MPL-2.0"
LIC_FILES_CHKSUM = "\
    file://LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d \
"

DEPENDS:append = " \
    bison-native \
    gstreamer1.0 \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    wpeframework-clientlibraries \
    ccache-native \
    gn-native \
    ninja-native \
    python3-native \
    python3-six-native \
"


GCC_MAJOR_VERSION = "${@oe.utils.trim_version("${GCCVERSION}", 1)}"
GCC_9_PATCHLIST = "file://0001-changes-for-gcc-9.patch"

RECIPE_BRANCH ?= "cobalt-23"
SRC_URI = "git://git@github.com/Metrological/Cobalt.git;protocol=https;branch=${RECIPE_BRANCH} \
           ${@bb.utils.contains('GCC_MAJOR_VERSION', '9', '${GCC_9_PATCHLIST}', '', d)} \
           file://0001-v8-add-missing-include.patch \
           file://0001-Remove-leftover-_env.py.patch \
           file://0002-Stop-using-imp-python-module.-2011.patch \
           file://0003-Fix-suppress-new-GCC-12-13-warnings.patch \
           file://0004-fully-switch-off-OCDM-if-not-avalable.patch \
           "

SRCREV ??= "e7e0af9fabb41f100cd93e8ec04c307a3b3b3a32"
SRCREV:wpeframework = "e3d3eda2d1904eeb2b4e86245660d517e73a271c"

PR = "r1"

PV = "${RECIPE_BRANCH}+gitr${SRCPV}"

S = "${WORKDIR}/git"

inherit features_check pkgconfig python3native

# FIXME: Check wheter necessary
PACKAGES = "${PN}"

LD = "${CXX}"

COBALT_PLATFORM ??= "wpe-arm"
COBALT_PLATFORM:aarch64 ?= "wpe-arm64"

COBALT_VIDEO_OVERLAY ??= "true"
COBALT_BUILD_TYPE ??= "gold"
COBALT_DEPENDENCIES ??= ""

DEPENDS:append = " ${COBALT_DEPENDENCIES}"

COBALT_CPU_ARCH ??= "arm"
COBALT_CPU_ARCH:aarch64 ?= "arm64"

def get_cobalt_data_path(d):
    cobalt_data = d.getVar('WPEFRAMEWORK_INSTALL_PATH')
    if not cobalt_data:
        return "${datadir}/content"
    else:
        return "${WPEFRAMEWORK_INSTALL_PATH}/Cobalt"
COBALT_DATA = "${@get_cobalt_data_path(d)}"

do_configure() {
    export COBALT_EXECUTABLE_TYPE="shared_library"
    export COBALT_HAS_OCDM="${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 1, 0, d)}"
    export COBALT_HAS_PROVISION="${@bb.utils.contains('DISTRO_FEATURES', 'provisioning', 1, 0, d)}"
    export COBALT_HAS_WAYLANDSINK="${@bb.utils.contains('DISTRO_FEATURES', 'weston', 1, 0, d)}"
    export COBALT_VIDEO_OVERLAY=${COBALT_VIDEO_OVERLAY}

    export COBALT_STAGING_DIR="${STAGING_DIR_HOST}/"
    export OPENSSL_NO_ASM=1
    export COBALT_TOOLCHAIN_PREFIX="${STAGING_DIR_NATIVE}${bindir}/${TARGET_SYS}/${TARGET_PREFIX}"
    export PYTHONPATH="${S}:${PYTHONPATH}"
    export COBALT_DATA_PATH="${COBALT_DATA}/data"

    cd "${S}" && "${STAGING_BINDIR_NATIVE}"/gn gen out/wpe --script-executable=python3 --args='target_platform="${COBALT_PLATFORM}" build_type="${COBALT_BUILD_TYPE}" target_cpu="${COBALT_CPU_ARCH}" is_clang=false sb_install_content_subdir="${COBALT_DATA}/data" is_video_overlay=${COBALT_VIDEO_OVERLAY}'
}
do_compile() {
    export PYTHONPATH="${S}:${PYTHONPATH}"
    "${STAGING_BINDIR_NATIVE}/ninja" -C "${S}/out/wpe" cobalt_install
}

do_install() {
    install -d "${D}${libdir}"
    install -m 0755 "${S}/out/wpe/install/lib/libcobalt.so" "${D}${libdir}"
    install -d "${D}${includedir}/starboard"
    cp -prf "${S}/starboard/"*.h "${D}${includedir}/starboard/"
    install -d "${D}${includedir}/third_party/starboard/wpe/shared"
    cp -prf "${S}/third_party/starboard/wpe/shared/"*.h "${D}/${includedir}/third_party/starboard/wpe/shared/"

    install -d "${D}${COBALT_DATA}"
    cp -arv --no-preserve=ownership "${S}/out/wpe/install/${COBALT_DATA}/data" "${D}${COBALT_DATA}"
}

COBALT_PACKAGE = "\
    ${libdir} \
    ${COBALT_DATA} \
    ${includedir} \
"

FILES:${PN} += "${COBALT_PACKAGE}"
FILES:${PN}-dev += "${COBALT_PACKAGE}"

RDEPENDS:${PN} += "\
    ${COBALT_DEPENDENCIES} \
    gstreamer1.0 \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    wpeframework \
"

INSANE_SKIP:${PN} = "ldflags"
INSANE_SKIP:${PN}-dev = "ldflags"
INSANE_SKIP:${PN} += "dev-deps"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

