TARGETS = console-setup resolvconf mountkernfs.sh ufw hostname.sh plymouth-log screen-cleanup apparmor udev mountdevsubfs.sh procps cryptdisks cryptdisks-early networking hwclock.sh checkroot.sh lvm2 checkfs.sh urandom iscsid open-iscsi mountall.sh checkroot-bootclean.sh bootmisc.sh mountall-bootclean.sh kmod mountnfs.sh mountnfs-bootclean.sh
INTERACTIVE = console-setup udev cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: resolvconf mountkernfs.sh urandom procps
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
urandom: hwclock.sh
iscsid: networking
open-iscsi: networking iscsid
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountall-bootclean.sh mountnfs-bootclean.sh udev
mountall-bootclean.sh: mountall.sh
kmod: checkroot.sh
mountnfs.sh: networking
mountnfs-bootclean.sh: mountnfs.sh
