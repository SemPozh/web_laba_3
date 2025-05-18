import org.gradle.api.tasks.Delete
import org.gradle.internal.os.OperatingSystem
import java.io.ByteArrayOutputStream
import java.io.File

plugins {
    war
    java
    base
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("jakarta.faces:jakarta.faces-api:4.1.2")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("org.eclipse.persistence:eclipselink:5.0.0-B04")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.1.0")
    testImplementation("junit:junit:4.13.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}



tasks.register("history") {
    description="history compile"
    group = "custom"

    doLast{
        logger.lifecycle("РЅР°С‡Р°Р»Рѕ РІС‹РїРѕР»РЅРµРЅРёСЏ")
        var revision = getCurrentRevision()
        var success = false

        while (!success && revision >= 0) {
            logger.lifecycle("РєРѕРјРїРёР»РёСЂСѓРµРј РїСЂРѕРµРєС‚ РІРµСЂСЃРёРё $revision")
            success = try {
                exec {commandLine("svn", "update", "src/", "-r", revision.toString())}
                exec {commandLine("./gradlew", "clean", "build")}

                true
            }
            catch (e: Exception) {
                false
            }
            if (!success) {
                revision--
            }
            else {
                exec {commandLine("svn", "update", "src_diff/")}

                val output = ByteArrayOutputStream()
                exec {
                    commandLine("diff -rw src/main src_diff/main".split(" "))
                    isIgnoreExitValue = true
                    standardOutput = output
                }
                val info = output.toString()
                val file = File("diff.txt")
                file.writeText(info)

            }

        }
    }
}


tasks {
    val compile by registering {
        dependsOn(compileJava)
        description = "РљРѕРјРїРёР»СЏС†РёСЏ РёСЃС…РѕРґРЅРѕРіРѕ РєРѕРґР°"
    }

    named("build") {
        dependsOn(war)
        description = "РЎР±РѕСЂРєР° WAR-Р°СЂС…РёРІР°"
    }


    named("test"){
        description = "junit С‚РµСЃС‚С‹"
    }

    named<Delete>("clean") {
        description = "РЈРґР°Р»СЏРµС‚ РІСЃРµ СЃРєРѕРјРїРёР»РёСЂРѕРІР°РЅРЅС‹Рµ С„Р°Р№Р»С‹ Рё РІСЂРµРјРµРЅРЅС‹Рµ Р°СЂС‚РµС„Р°РєС‚С‹"
        delete(
            fileTree("build"),
            fileTree("out"),
            fileTree("target")
        )

        doLast {
            logger.lifecycle("вњ… РћС‡РёСЃС‚РєР° РІС‹РїРѕР»РЅРµРЅР° СѓСЃРїРµС€РЅРѕ")
            logger.lifecycle("РЈРґР°Р»РµРЅРЅС‹Рµ РґРёСЂРµРєС‚РѕСЂРёРё:\n- build\n- out\n- target")
        }
    }

    register("music") {
        description = "Р’РѕСЃРїСЂРѕРёР·РІРѕРґРёС‚ РјСѓР·С‹РєСѓ РїРѕСЃР»Рµ СѓСЃРїРµС€РЅРѕР№ СЃР±РѕСЂРєРё"
        dependsOn("build")
        group = "custom"

        doLast {
            val musicFile = project.file("191.mp3").takeIf { it.exists() }
                ?: run {
                    logger.warn("РњСѓР·С‹РєР°Р»СЊРЅС‹Р№ С„Р°Р№Р» РЅРµ РЅР°Р№РґРµРЅ! РЎРѕР·РґР°Р№С‚Рµ С„Р°Р№Р» music.mp3 РІ РєРѕСЂРЅРµ РїСЂРѕРµРєС‚Р°")
                    return@doLast
                }

            when (OperatingSystem.current()) {
                OperatingSystem.WINDOWS -> exec {
                    commandLine("cmd", "/c", "start", "wmplayer", musicFile.absolutePath)
                }
                OperatingSystem.LINUX -> exec {
                    commandLine("xdg-open", musicFile.absolutePath)
                }
                OperatingSystem.MAC_OS -> exec {
                    commandLine("afplay", musicFile.absolutePath)
                }
                else -> logger.warn("РќРµРїРѕРґРґРµСЂР¶РёРІР°РµРјР°СЏ РћРЎ РґР»СЏ РІРѕСЃРїСЂРѕРёР·РІРµРґРµРЅРёСЏ РјСѓР·С‹РєРё")
            }
            logger.lifecycle("в™« РњСѓР·С‹РєР° Р·Р°РїСѓС‰РµРЅР°: ${musicFile.name}")
        }
    }

    withType<War>().configureEach {
        archiveFileName.set("lab3.war")
        manifest {
            attributes(
                mapOf(
                    "Implementation-Version" to "1.0-SNAPSHOT",
                    "Created-By" to "Gradle ${gradle.gradleVersion}"
                )
            )
        }
    }
}

fun getCurrentRevision(): Int {
    val output = ByteArrayOutputStream()

    project.exec{commandLine("svn","update", "src/")}
    project.exec {
        commandLine("svn","info", "src/")
        standardOutput = output
    }

    val info = output.toString()
    val revLine = info.lines().find { it.startsWith("Revision:")}
    return revLine?.split(":")?.get(1)?.trim()?.toInt() ?: 0

}

