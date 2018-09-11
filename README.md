# graalvm-bug-noclassdeffounderror

Steps to reproduce :

```bash
mvn clean install -Dmaven.test.skip=true
mvn dependency:copy-dependencies
export CLASSPATH=$(ls target/dependency/*.jar | tr "\n" ":")"target/graalvm-bug-noclassdeffounderror-1.0.0-SNAPSHOT.jar"
native-image \
    -ea \
    --no-server \
    --report-unsupported-elements-at-runtime \
    --class-path $CLASSPATH \
    -Djava.net.preferIPv4Stack=true \
    -jar "target/graalvm-bug-noclassdeffounderror-1.0.0-SNAPSHOT.jar"
```

Output : 

```
[graalvm-bug-noclassdeffounderror-1.0.0-SNAPSHOT:47]    classlist:   3,566.45 ms
[graalvm-bug-noclassdeffounderror-1.0.0-SNAPSHOT:47]        (cap):   2,145.87 ms
[graalvm-bug-noclassdeffounderror-1.0.0-SNAPSHOT:47]        setup:   4,320.70 ms
[graalvm-bug-noclassdeffounderror-1.0.0-SNAPSHOT:47]     analysis:   4,048.34 ms
fatal error: java.lang.NoClassDefFoundError
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:598)
	at java.util.concurrent.ForkJoinTask.get(ForkJoinTask.java:1005)
	at com.oracle.svm.hosted.NativeImageGenerator.run(NativeImageGenerator.java:418)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.buildImage(NativeImageGeneratorRunner.java:278)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.build(NativeImageGeneratorRunner.java:375)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.main(NativeImageGeneratorRunner.java:104)
Caused by: java.lang.NoClassDefFoundError
	at jdk.vm.ci.hotspot.HotSpotJVMCIRuntime.lookupType(HotSpotJVMCIRuntime.java:464)
	at jdk.vm.ci.hotspot.HotSpotResolvedObjectTypeImpl.lookupType(HotSpotResolvedObjectTypeImpl.java:957)
	at jdk.vm.ci.meta.UnresolvedJavaType.resolve(UnresolvedJavaType.java:92)
	at jdk.vm.ci.hotspot.HotSpotResolvedJavaFieldImpl.getType(HotSpotResolvedJavaFieldImpl.java:126)
	at com.oracle.graal.pointsto.meta.AnalysisType.<init>(AnalysisType.java:185)
	at com.oracle.graal.pointsto.meta.AnalysisUniverse.createType(AnalysisUniverse.java:251)
	at com.oracle.graal.pointsto.meta.AnalysisUniverse.lookupAllowUnresolved(AnalysisUniverse.java:193)
	at com.oracle.graal.pointsto.meta.AnalysisUniverse.lookup(AnalysisUniverse.java:170)
	at com.oracle.graal.pointsto.meta.AnalysisField.<init>(AnalysisField.java:104)
	at com.oracle.graal.pointsto.meta.AnalysisUniverse.createField(AnalysisUniverse.java:353)
	at com.oracle.graal.pointsto.meta.AnalysisUniverse.lookupAllowUnresolved(AnalysisUniverse.java:341)
	at com.oracle.graal.pointsto.meta.AnalysisUniverse.lookup(AnalysisUniverse.java:308)
	at com.oracle.graal.pointsto.meta.AnalysisType.convertFields(AnalysisType.java:875)
	at com.oracle.graal.pointsto.meta.AnalysisType.convertInstanceFields(AnalysisType.java:868)
	at com.oracle.graal.pointsto.meta.AnalysisType.getInstanceFields(AnalysisType.java:859)
	at com.oracle.graal.pointsto.meta.AnalysisType.convertInstanceFields(AnalysisType.java:866)
	at com.oracle.graal.pointsto.meta.AnalysisType.getInstanceFields(AnalysisType.java:859)
	at com.oracle.graal.pointsto.ObjectScanner.doScan(ObjectScanner.java:268)
	at com.oracle.graal.pointsto.ObjectScanner.finish(ObjectScanner.java:312)
	at com.oracle.graal.pointsto.ObjectScanner.scanBootImageHeapRoots(ObjectScanner.java:81)
	at com.oracle.graal.pointsto.ObjectScanner.scanBootImageHeapRoots(ObjectScanner.java:63)
	at com.oracle.graal.pointsto.BigBang.checkObjectGraph(BigBang.java:591)
	at com.oracle.graal.pointsto.BigBang.finish(BigBang.java:562)
	at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:690)
	at com.oracle.svm.hosted.NativeImageGenerator.lambda$run$0(NativeImageGenerator.java:401)
	at java.util.concurrent.ForkJoinTask$AdaptedRunnableAction.exec(ForkJoinTask.java:1386)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
Caused by: java.lang.ClassNotFoundException: com.lmax.disruptor.dsl.Disruptor
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at jdk.vm.ci.hotspot.CompilerToVM.lookupType(Native Method)
	at jdk.vm.ci.hotspot.HotSpotJVMCIRuntime.lookupType(HotSpotJVMCIRuntime.java:456)
	... 29 more
Error: Image building with exit status 1
```
