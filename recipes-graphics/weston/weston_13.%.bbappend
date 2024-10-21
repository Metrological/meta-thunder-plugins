do_install:append() {
    install -d ${D}${includedir}/libweston-13/weston/shared/
    install -Dm 644 ${S}/libweston/backend.h ${D}${includedir}/libweston-13/libweston/
    install -Dm 644 ${S}/shared/helpers.h ${D}${includedir}/libweston-13/weston/shared/
}
