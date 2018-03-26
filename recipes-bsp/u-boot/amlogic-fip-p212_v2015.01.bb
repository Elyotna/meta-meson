SUMMARY = "Amlogic FIP Binaries distributed in U-Boot"
PROVIDES = "amlogic-fip"

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-amlogic:"

B = "${WORKDIR}/build"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

COMPATIBLE_MACHINE_amlogic-p212 = "amlogic-p212"

PACKAGECONFIG ??= "openssl"
PACKAGECONFIG[openssl] = ",,openssl-native"

require u-boot-amlogic-common_${PV}.inc

DEPENDS += "bc-native dtc-native"

do_compile () {
	oe_runmake -C ${S} O=${B} gxl_p212_v1_config
	oe_runmake -C ${S} O=${B}
}

do_deploy () {
    mkdir -p ${DEPLOYDIR}/fip/
    install ${S}/fip/gxl/bl2.bin ${DEPLOYDIR}/fip/bl2.bin
    install ${S}/fip/gxl/acs.bin ${DEPLOYDIR}/fip/acs.bin
    install ${S}/fip/gxl/bl21.bin ${DEPLOYDIR}/fip/bl21.bin
    install ${S}/fip/gxl/bl30.bin ${DEPLOYDIR}/fip/bl30.bin
    install ${S}/fip/gxl/bl301.bin ${DEPLOYDIR}/fip/bl301.bin
    install ${S}/fip/gxl/bl31.img ${DEPLOYDIR}/fip/bl31.img
    install ${S}/fip/blx_fix.sh ${DEPLOYDIR}/fip/blx_fix.sh
    install ${S}/fip/acs_tool.pyc ${DEPLOYDIR}/fip/acs_tool.pyc
    install ${S}/fip/gxl/aml_encrypt_gxl ${DEPLOYDIR}/fip/aml_encrypt_gxl
}

addtask deploy before do_build after do_compile
