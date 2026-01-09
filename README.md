# ️ Engineer's Password Generator

A Light-Weight, cryptographically secure password generation tool built with **Zulu Java 25** and **JavaFX**. This application provides brute-force estimations based on RTX 4090 Hashrate performance.

![Application Preview](https://github.com/AnasDevO/JavaFX_Password_Generator/blob/master/ScreenShot.PNG?raw=true)

## Key Features

* **Cryptographically Secure**: Utilizes `java.security.SecureRandom` for non-deterministic, high-entropy character selection.
* **Modern Material UI**: Integrated with **MaterialFX** for a fluid "Material Design" aesthetic, including animated sliders and toggle buttons.
* **Strength Analysis**: Complexity calculation estimating the "Time to Crack" against an NVIDIA RTX 4090 (estimated at 164 Billion hashes/sec).

## Technical Stack

* **Language:** Java 25+
* **Framework:** JavaFX
* **UI Library:** MaterialFX
* **Security:** CSPRNG (Secure-Package Random Number Generator)
* **Architecture:** MVC (Model-View-Controller) pattern

## Complexity Logic

The application calculates the total entropy of the password pool and compares it against the documented performance of high-end hardware.

### The Formula
The entropy is calculated by determining the bit-strength of the password based on pool size ($P$) and length ($L$):

$$Complexity = \log_2(P^L)$$

The time to crack ($T$) in hours is derived using the hash rate of a modern GPU:

$$T = \frac{2^{Complexity}}{164 \times 10^9 \times 3600}$$




##  Getting Started

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/AnasDevO/JavaFX_Password_Generator.git](https://github.com/AnasDevO/JavaFX_Password_Generator.git)
    ```
2.  **Build the project:**
    Ensure you have JavaFX compatible distro of Java 25+ installed (Zulu preferred) and run:
    ```bash
    ./gradlew run
    ```


---
*Developed as Proof Of Concept.*