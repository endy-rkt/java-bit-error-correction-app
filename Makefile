# Makefile for CodageApplication (JavaFX desktop app)
# Usage: make deps && make run

APP_NAME   := CodageApplication
MAIN_CLASS := codageapplication.Main
PKG_DIR    := codageapplication
SRC        := $(wildcard *.java)
BUILD_DIR  := build

FX_DIR  := /usr/share/java
FX_JARS := $(FX_DIR)/javafx-base.jar:$(FX_DIR)/javafx-controls.jar:$(FX_DIR)/javafx-fxml.jar:$(FX_DIR)/javafx-graphics.jar

JAVAC := javac
JAVA  := java

.PHONY: all deps build run clean

all: build

# Install OpenJDK 21 + OpenJFX (Debian/Ubuntu apt). Run once.
deps:
ifeq ($(shell id -u),0)
	apt-get update -qq
	apt-get install -y openjdk-21-jdk-headless openjfx
else
	sudo apt-get update -qq
	sudo apt-get install -y openjdk-21-jdk-headless openjfx
endif

# Compile sources + drop resource (style.css) next to the compiled classes
build: $(SRC)
	mkdir -p $(BUILD_DIR)
	$(JAVAC) -cp "$(FX_JARS)" -d $(BUILD_DIR) $(SRC)
	cp style.css $(BUILD_DIR)/$(PKG_DIR)/style.css

run: build
	$(JAVA) --module-path "$(FX_JARS)" --add-modules javafx.controls,javafx.fxml \
		-cp $(BUILD_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(BUILD_DIR)