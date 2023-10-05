# Sets a default version for thunder
RECIPE_BRANCH ?= "R4"
SRCREV ?= "R4.4.0"

PV = "${RECIPE_BRANCH}+gitr${SRCPV}"
