plugins {
	java
	id("org.springframework.boot") version "3.5.12-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.fantasmaDux"
version = "0.0.1-SNAPSHOT"
description = "Simple task manager for small teams with export and report "

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://jaspersoft.jfrog.io/jaspersoft/third-party-ce-artifacts/") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// GWT Integration
	implementation ("org.jadice.gwt.spring:gwt-spring-boot-starter:2.2.6")

	// Apache POI
	implementation ("org.apache.poi:poi:5.3.0")
	implementation ("org.apache.poi:poi-ooxml:5.3.0")

	// JasperReports
	implementation ("net.sf.jasperreports:jasperreports:6.20.0")
	implementation ("net.sf.jasperreports:jasperreports-fonts:6.20.0")

	// UUIDv7 with created time sorting
	implementation("com.github.f4b6a3:uuid-creator:5.3.6")

	// For DTO <-> Entity mapping
	implementation ("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor ("org.mapstruct:mapstruct-processor:1.5.5.Final")
	annotationProcessor ("org.projectlombok:lombok-mapstruct-binding:0.2.0")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
