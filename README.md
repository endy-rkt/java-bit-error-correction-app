# CodageApplication

JavaFX desktop app for encoding/decoding binary messages with classic
error-detection/correction codes: Hamming, repetition, interlaced
parity, and simple parity.

Package: `codageapplication` · Entry point: `Main.java`

## Requirements

- JDK 17+ (JDK 21 recommended)
- OpenJFX (JavaFX) 11+

## Install dependencies

```
make deps
```

Runs `apt-get install openjdk-21-jdk-headless openjfx` (sudo if not root).

If `openjdk-21-jdk-headless` isn't in your repos, find what your
release offers and edit the version in the Makefile's `deps` target
(nothing else needs to change — the code has no version-specific
syntax):

```
apt-cache search openjdk | grep jdk-headless
```

## Build & run

```
make build   # compiles to build/, copies style.css alongside classes
make run     # build + launch
make clean
```

`make run` opens the "rakoto codage" window: pick a coding type
(Hamming / répétition / parité entrelacée / parité simple), Coder or
Décoder, enter a binary message, click **Lancer**.

## Project layout

```
Main.java               # Application entry point, GridPane UI
Codage.java              # Input validation (isBinary, isNumber)
CodageHamming.java        # Hamming encode
DecodageHamming.java      # Hamming decode
HammingCode.java           # Hamming UI wiring
RepetitionCode.java        # Repetition code (encode/decode + UI)
EntrelacePariteCode.java   # Interlaced parity code (encode/decode + UI)
SimplePariteCode.java      # Simple parity code (encode/decode + UI)
AlertBox.java               # Error dialog helper
style.css                   # UI theming
```

## Troubleshooting

- **`javac: not found`** → `make deps` wasn't run or failed; see
  Install dependencies above.
- **`JavaFX runtime components are missing`** → you're running with
  plain `-cp` instead of `--module-path`; use `make run`, don't
  invoke `java` directly.
- **Module conflict on `--module-path`** → point it at the individual
  JavaFX jars, not a whole directory like `/usr/share/java` (other
  packages there, e.g. LibreOffice, ship colliding modules). The
  Makefile already does this correctly.

## License

MIT — see `LICENSE`.