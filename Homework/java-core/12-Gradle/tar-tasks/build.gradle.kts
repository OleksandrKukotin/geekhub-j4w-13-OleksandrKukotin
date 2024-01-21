plugins {
    id("java")
}

tasks.register<Tar>("archiveTask") {
    archiveBaseName = "archive"
    compression = Compression.GZIP

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

