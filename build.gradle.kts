import net.fabricmc.loom.task.RemapSourcesJarTask;

plugins {
    id("net.fabricmc.fabric-loom-remap") version "1.18-SNAPSHOT"
    id("me.modmuss50.mod-publish-plugin") version "2.2.1"
    id("maven-publish")
}

version = project.findProperty("mod.version") as String
group = project.findProperty("mod.maven_group") as String

base {
    archivesName = project.findProperty("mod.archives_base_name") as String
}

publishMods {
    changelog = rootProject.file("CHANGELOG.md").readText()
    type = STABLE
    file = tasks.remapJar.map { it.archiveFile.get() }
    additionalFiles.from(tasks.named<RemapSourcesJarTask>("remapSourcesJar").map { it.archiveFile.get() })
    version = "${findProperty("mod.version")}+${property("mod.dep.mc")}-fabric"
    modLoaders.add("fabric")

    github {
        accessToken = providers.environmentVariable("GITHUB_TOKEN")
        repository = "Crazy10061/meow"
        commitish = "master"
    }
}

loom {
    useIntermediateMappings = true
    intermediaryUrl =
        $$"https://repo.codemc.io/repository/relativitymc/org/relativitymc/intermediary/%1$s/intermediary-%1$s-v2.jar"

    // FIIIINE you win this time
    // I'LL CALL IT A CLASSTWEAKER
    // EUGH
    accessWidenerPath = rootProject.file("src/main/resources/meow.classtweaker")
}

fabricApi {
    configureDataGeneration {
        client = true
    }
}

repositories {
    // Add repositories to retrieve artifacts from in hereg
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
    maven {
        url = uri("https://repo.codemc.io/repository/relativitymc/")
    }

    maven {
        url = uri("https://repo.sleeping.town/")
    }

    maven {
        url = uri("https://maven.isxander.dev/releases")
    }

    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = uri("https://api.modrinth.com/maven")
            }
        }
        filter {
            includeGroup("maven.modrinth")
        }
    }
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${project.findProperty("mod.dep.mc")}")
    mappings("org.relativitymc:modern-yarn:${project.findProperty("mod.dep.yarn")}:v2")

    modImplementation("net.fabricmc:fabric-loader:${project.findProperty("mod.dep.loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.findProperty("mod.dep.fabric")}")

    modImplementation("folk.sisby:kaleido-config:0.3.3+1.3.2")

    // YACL, required by McQoy
    modImplementation("dev.isxander:yet-another-config-lib:${project.findProperty("mod.dep.yacl")}")
}

tasks.named<ProcessResources>("processResources") {
    inputs.property("version", project.version)
    inputs.property("minecraft_version", project.findProperty("mod.dep.mc"))
    inputs.property("loader_version", project.findProperty("mod.dep.loader"))
    filteringCharset = "UTF-8"

    filesMatching("fabric.mod.json") {
        expand(
            "version" to project.version,
            "minecraft_version" to project.findProperty("mod.dep.mc") as String,
            "loader_version" to project.findProperty("mod.dep.loader") as String
        )
    }
}

val targetJavaVersion = 25
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"

    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }

    withSourcesJar()
}

tasks.named<Jar>("jar") {
    from("LICENSE") {
        rename { "${it}_${project.findProperty("mod.archives_base_name")}" }
    }
}

// configure the maven publication
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = project.findProperty("mod.archives_base_name") as String
            from(components["java"])
        }
    }

    repositories {
    }
}
