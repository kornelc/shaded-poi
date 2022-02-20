# shaded-poi

POI is used to read and edit Microsoft files. It is a huge library which links in many other Apache artifacts as dependencies. When trying to combine POI with other large libraries such as Spark, some of their dependencies may conflict with each other.

Shading is a reliable way to deal with this problem. When creating a large uber jar, we can use SBT Assembly's shading mechanism to rename the clashing symbols before creating the uber jar. Some artifacts can simply be discarded, when they are clearly not needed in the environment. This will simplify the shading rules. For example I have been discarding module-info.class throughout.

This build allowed me to link POI with Spark/Scala and work with it reliably in the Almond notebook environment. 

If we have additional libraries, we simply need to add additional <code>ShadeRule</code>-s and <code>MergeStrategie</code>-s to build.sbt.
