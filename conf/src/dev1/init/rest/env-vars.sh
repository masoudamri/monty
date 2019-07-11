#!/bin/bash
ORS_ENV=dev1
ORS_BUILD_BASE="$( readlink -f $(  cd "$( dirname "$0" )/../../../.." && pwd ))"
ORS_ARTIFACTS_BASE="${ORS_JAR_BASE_OVERRIDE:-$ORS_BUILD_BASE}"
ORS_CONF_BASE="${ORS_BUILD_BASE}"
ORS_JAVA_OPTS="-Dlog4j.configurationFile=$ORS_CONF_BASE/conf/$ORS_ENV/boot/rest/config/log4j2.xml"
ORS_CLASS_PATH="$ORS_ARTIFACTS_BASE/fyaat/lib/fyaat-rest-pathing-local-runtime.jar"
ORS_JAVA_MAIN_CLASS="com.ors.finance.fyaat.boot.BootApp"
ORS_ARGS="file:$ORS_CONF_BASE/conf/$ORS_ENV/boot/rest/"
ORS_RUN_CMD="$ORS_CONF_BASE/conf/$ORS_ENV/bin/exec-java.sh"

for var in $(compgen -v -s ORS);
do
	echo "export $var=${!var}"
done