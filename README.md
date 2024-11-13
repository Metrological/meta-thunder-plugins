# meta-thunder-plugins

## Introduction

Welcome to the `meta-thunder-plugins` repository! This project serves as a Yocto Project layer, enabling you to include [Thunder](https://rdkcentral.github.io/Thunder/) plugins into your Open Embedded/Yocto based embedded Linux systems. `Thunder` provides a comprehensive framework for developing and deploying web-based applications, bringing rich, interactive experiences to embedded devices. Thunders functionality is extendable by adding [plugins](https://github.com/rdkcentral/ThunderNanoServices). Whether you're developing smart appliances, digital signage, or any other embedded web application, this layer equips you with the essential tools and recipes to seamlessly integrate Thunder plugins into your Yocto-based project.

### Prerequisites

Before you begin, make sure you have the following prerequisites installed on your development machine:

- [Yocto Project](https://www.yoctoproject.org/) - Ensure that you have Yocto Project installed and configured on your system.
- [Git](https://git-scm.com/) - You'll need Git for version control.
- A compatible embedded Linux target platform or emulator.

#### Summary of commands on Ubuntu
   ``` shell
   sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev python3-subunit mesa-common-dev zstd liblz4-tool file locales
   ``` 
   ``` shell
   sudo locale-gen en_US.UTF-8
   ```  
## Quick Setup for RaspberryPi

To get started with `meta-thunder-plugins` and begin building thunder based applications for your embedded Linux project, follow these quick setup instructions:

1. Create a workspace:
   ``` shell
   mkdir ${HOME}/thunder-yocto/{build-rpi,download,source}
   ```

1. Enter the ```source``` folder
1. Get generic layers
   ``` shell
   git clone -b scarthgap git://git.yoctoproject.org/poky
   git clone -b scarthgap git://git.openembedded.org/openembedded-core
   git clone -b scarthgap git://git.openembedded.org/meta-openembedded
   ``` 
1. Get our meta layers
   ``` shell
   git clone -b main git@github.com:Metrological/meta-thunder-framework.git
   git clone -b main git@github.com:Metrological/meta-thunder-plugins.git
   ```

1. Get a BSP layer for RaspberryPi
    ``` shell
    git clone -b scarthgap git://git.yoctoproject.org/meta-raspberrypi
    ```
1. From your work dir run 
   ``` shell 
   source source/poky/oe-init-build-env ./build-rpi
   ```
1. Add these layers to build
    ``` shell
    bitbake-layers add-layer ../source/meta-openembedded/meta-oe
    bitbake-layers add-layer ../source/meta-openembedded/meta-python
    bitbake-layers add-layer ../source/meta-openembedded/meta-multimedia
    bitbake-layers add-layer ../source/meta-openembedded/meta-networking
    bitbake-layers add-layer ../source/meta-raspberrypi
    bitbake-layers add-layer ../source/meta-thunder-framework
    bitbake-layers add-layer ../source/meta-thunder-plugins
    ```

1. Add some needed config to ```<build-dir>/conf/local.conf```
   ```
   # define you machine accordingly 
   MACHINE = "raspberrypi3-64"

   # set download location up so we can reuse sources for other builds  
   DL_DIR = "${TOPDIR}/../downloads"

   # Remove X11
   DISTRO_FEATURES:remove = "x11"

   # Force to include Thunder DeviceInfo plugin 
   PACKAGECONFIG:append:pn-thunder-clientlibraries = " deviceinfo"

   # Use weston as a compositor
   DISTRO_FEATURES:append = " weston"

   # needed for compiling R4.X
   # DISTRO_FEATURES:append =  " wpeframework"

   # Enable OCDM
   DISTRO_FEATURES:append =  " opencdm"
   
   # Enable DRM modules
   # DISTRO_FEATURES:append =  " clearkey"
   # DISTRO_FEATURES:append =  " widevine"
   # DISTRO_FEATURES:append =  " playready"
   ```

1. Check your config
   ``` shell
   bitbake-layers show-layers
   bitbake core-image-thunder -n
   ```

1. If you get: ERROR: PermissionError: [Errno 1] Operation not permitted
   ``` shell
   sudo apparmor_parser -R /etc/apparmor.d/unprivileged_userns
   ```

1. Download packages 
   ``` shell 
   bitbake core-image-thunder --runonly=fetch
   ```

1. To build the image
   ``` shell 
   bitbake core-image-thunder
   ```

1. Flash image file
    
    The raw disk image is ```core-image-thunder-raspberrypi3-64.wic.bz2``` and located in ```tmp/deploy/images/raspberrypi3-64/```.
    
    Using [Etcher](https://etcher.balena.io/) you can write the ```wic.bz``` image directly to a SD-card. 
    
    Another way is using ```dd```
    1. Extract the WIC file:
        ``` shell
        cd tmp/deploy/images/raspberrypi3-64
        bzip2 -d -f core-image-thunder-raspberrypi3-64.wic.bz2
        ```

    2. Dump image to the SD Card at /dev/sdd
       ``` shell
       sudo dd bs=4M if=core-image-thunder-raspberrypi3-64.wic of=/dev/sdd status=progress conv=fsync 
       ```
   
## Documentation

For detailed documentation, including advanced configuration options and usage examples, please refer to the [official Thunder documentation](https://rdkcentral.github.io/Thunder/)

## Issues and Contributions

If you encounter issues or have suggestions for improvements, please open an issue on the [GitHub issue tracker](https://github.com/rdkcentral/thunder/issues). Contributions, such as bug fixes or new features, are always welcome. Please review our contribution guidelines before submitting a pull request.

## License

This project is licensed under the [MIT License](COPYING.MIT). Please review the LICENSE file for more details.
