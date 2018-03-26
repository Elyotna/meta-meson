require recipes-bsp/u-boot/u-boot.inc
require u-boot-khadas-vim-common_${PV}.inc

DEPENDS += "bc-native dtc-native"

PROVIDES =+ "u-boot"

do_deploy_append () {
    install ${S}/fip/u-boot.bin.sd.bin ${DEPLOYDIR}/u-boot.bin.sd.bin
}

