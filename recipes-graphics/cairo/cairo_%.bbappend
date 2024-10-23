FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
# Add egl/gles to config
PACKAGECONFIG:append_class-target = " egl"
PACKAGECONFIG:append_class-target = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'glesv2', d)}"


