inherit useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "-r ${THUNDER_OCDM_GROUP}"

USERADD_PARAM:${PN} = "--system --no-create-home --shell /bin/false"
USERADD_PARAM:${PN} += "-g ${THUNDER_OCDM_GROUP}"
USERADD_PARAM:${PN} += "-G root,${THUNDER_GROUP}"
USERADD_PARAM:${PN} += "${WPE_OPENCDMI_USER}"

