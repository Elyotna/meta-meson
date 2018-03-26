SUMMARY = "Amlogic FIP Binaries distributed in U-Boot"
PROVIDES = "amlogic-fip"

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-khadas-vim:"

B = "${WORKDIR}/build"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

PACKAGECONFIG ??= "openssl"
PACKAGECONFIG[openssl] = ",,openssl-native"

require u-boot-khadas-vim-common_${PV}.inc

DEPENDS += "bc-native dtc-native"

do_compile () {
	oe_runmake -C ${S} O=${B} gxl_p212_v1_config
	oe_runmake -C ${S} O=${B}
}

do_deploy_append () {
    install ${S}/gxl/bl2.bin ${DEPLOYDIR}/fip/bl2.bin
    install ${S}/gxl/acs.bin ${DEPLOYDIR}/fip/acs.bin
    install ${S}/gxl/bl21.bin ${DEPLOYDIR}/fip/bl21.bin
    install ${S}/gxl/bl30.bin ${DEPLOYDIR}/fip/bl30.bin
    install ${S}/gxl/bl301.bin ${DEPLOYDIR}/fip/bl301.bin
    install ${S}/gxl/bl31.img ${DEPLOYDIR}/fip/bl31.img
    install ${S}/blx_fix.sh ${DEPLOYDIR}/fip/blx_fix.sh
    install ${S}/acs_tool.pyc ${DEPLOYDIR}/fip/acs_tool.pyc
    install ${S}/gxl/aml_encrypt_gxl ${DEPLOYDIR}/fip/aml_encrypt_gxl
}

