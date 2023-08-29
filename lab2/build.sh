rm -rf compiledlabs
mkdir -p compiledlabs
javac -d compiledlabs -cp "Pokemon.jar" -sourcepath . Poke.java

jar cfm proglab2.jar META-INF/MANIFEST.MF -C compiledlabs .

