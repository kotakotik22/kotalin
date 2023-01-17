package com.kotakotik.kotalin.build

import com.kotakotik.kotalin.build.gen.genIterators
import com.kotakotik.kotalin.build.gen.genPeekingIterators
import org.gradle.api.Plugin
import org.gradle.api.Project

internal val Project.genDir get() = "$projectDir/gen/com/kotakotik/kotalin/"

class KotalinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.task("generatePeekingIters") {
            it.group = "gen"
            it.inputs.file("${target.projectDir}/buildSrc/src/main/resources/primitives.json")
            for (primitive in primitives)
                it.outputs.file("${target.genDir}/peeking/Peeking${primitive.name}Iterator.kt")
            it.doLast {
                target.genPeekingIterators()
            }
        }
        target.task("generateIterators") {
            it.group = "gen"
            it.inputs.file("${target.projectDir}/buildSrc/src/main/resources/primitives.json")
            for (primitive in primitives)
                if (primitive.customIterator)
                    it.outputs.file("${target.genDir}/iterator/${primitive.name}Iterator.kt")
            it.doLast {
                target.genIterators()
            }
        }
        target.task("generateAll") {
            it.group = "gen"
            it.dependsOn("generateIterators", "generatePeekingIters")
        }
        target.tasks.getByName("build").dependsOn("generateAll")
    }
}