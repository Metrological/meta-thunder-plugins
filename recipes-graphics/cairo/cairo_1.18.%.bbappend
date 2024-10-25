FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://0001-Revert-Drop-cairo-gl.patch \
    file://0006-add-egl-device-create.patch \
    file://0009-error-check-just-in-debug.patch \
    file://0001-add-noaa-compositor-for-meson.patch \
"

PACKAGECONFIG:append = " egl"

PACKAGECONFIG[egl] = "-Dgl-backend=glesv2 -Dglesv2=enabled,,virtual/egl virtual/libgles2"
