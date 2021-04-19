#!/bin/fish
rm -r jars

mkdir out jars

# first.jar (includes package1.Foo class) is a pre module jar file
javac -d out (find firstModule/ -name '*.java')
jar -c -f jars/first.jar -C out package1/Foo.class

rm -r out

# second.jar (includes package2.Bar that depends on package1.Foo(thus has a dependency on first.jar))
# first.jar depends on com.sun.net.httpserver.HttpServer, but since is a regular(pre-module) jar file
# we must require jdk.httpserver module in package 2 to include the module
javac -p jars/first.jar -d out (find secondModule/ -name '*.java')
jar -c -f jars/second.jar -C out module-info.class out/package2/Bar.class

rm -r out

echo "Running: java -cp jars/first.jar package1.Foo"
java -cp jars/first.jar package1.Foo
echo ""
echo ""
echo "java -cp jars/second.jar:jars/first.jar package2.Bar"
java -cp jars/second.jar:jars/first.jar package2.Bar
echo ""
echo ""
echo "java -p jars/first.jar:jars/second.jar -m secondModule/package2.Bar"
java -p jars/first.jar:jars/second.jar -m secondModule/package2.Bar



