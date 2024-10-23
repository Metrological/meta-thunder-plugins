FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://0006-add-egl-device-create.patch \
    file://0009-error-check-just-in-debug.patch \
    file://0001-add-noaa-compositor-for-v1.16.patch
"

