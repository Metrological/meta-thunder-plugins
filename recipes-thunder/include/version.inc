# Gets the branch name from version number
def GetMajorVersion(v):
    return  v.split('.', 1)[0]

RECIPE_BRANCH ?= "R${@GetMajorVersion('${PV}')}"

# this should point to a git tag
SRCREV ?= "R${PV}"
