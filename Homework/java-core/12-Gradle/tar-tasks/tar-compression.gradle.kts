tasks.register<Tar>("archiveTask") {
    archiveBaseName = "archive"
    compression = Compression.GZIP
    destinationDirectory.set(fileTree("build/archive").dir)

    from(fileTree("src/main/resources").files) {
        include("*.text")
        includeEmptyDirs = false
        rename { file ->
            file.replaceFirst(".text", ".txt")
        }
    }

    doLast {
        logger.lifecycle("Files have been archived.")
    }
}

val triggerArchive by tasks.registering {
    dependsOn("archiveTask")
}

val unarchiveTask = tasks.register<Copy>("unarchiveTask") {
    dependsOn(triggerArchive)
    doLast {
        from(tarTree("build/archive/archive.tgz").files) {
            include("*.txt")
            includeEmptyDirs = false
        }
        into(fileTree("src/test/resources").dir)
        logger.lifecycle("Files have been unarchived.")
    }
}

tasks.register<Delete>("cleanup") {
    doLast {
        fileTree("build/archive").files.map { file -> file.delete() }
        fileTree("src/test/resources").files.map { file -> file.delete() }
        logger.lifecycle("Cleanup completed.")
    }
}
